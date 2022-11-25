package com.acme.order.service.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acme.order.commons.dto.CustomerDto;
import com.acme.order.commons.dto.OrderDto;
import com.acme.order.commons.enums.ErrorCode;
import com.acme.order.commons.exception.BusinessException;
import com.acme.order.commons.request.MessageDto;
import com.acme.order.commons.request.OrderRequestDto;
import com.acme.order.commons.request.PaginatedRequestDto;
import com.acme.order.commons.request.graphql.OrderQueryDto;
import com.acme.order.commons.response.GenericResponseDto;
import com.acme.order.commons.response.PaginatedResponseDto;
import com.acme.order.commons.response.graphql.OrderGraphQLDto;
import com.acme.order.model.OrderDO;
import com.acme.order.model.OrderDetailDO;
import com.acme.order.model.QOrderDO;
import com.acme.order.persistence.CustomerPersistence;
import com.acme.order.persistence.ItemPersistence;
import com.acme.order.persistence.OrderDetailPersistence;
import com.acme.order.persistence.OrderPersistence;
import com.acme.order.persistence.graphql.OrderGraphQLRepository;
import com.acme.order.service.OrderService;
import com.acme.order.service.helper.OrderPredicate;
import com.acme.order.service.util.OrderGraphQLDtoTransformer;
import com.github.dozermapper.core.Mapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;

import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;

/**
 * Class OrderServiceImpl
 * 
 * @author guillermo.segura@axity.com
 */
@Service
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService
{
  @Autowired
  private OrderPersistence orderPersistence;

  @Autowired
  private OrderDetailPersistence orderDetailPersistence;

  @Autowired
  private CustomerPersistence customerPersistence;

  @Autowired
  private ItemPersistence itemPersistence;

  @Autowired
  private OrderGraphQLRepository orderGraphQLRepository;

  @Autowired
  private Mapper mapper;

  // @Autowired
  // private KafkaTemplate<Object, Object> template;

  /**
   * {@inheritDoc}
   */
  @Override
  public PaginatedResponseDto<OrderDto> findOrders( PaginatedRequestDto request )
  {
    log.debug( "%s", request );

    int page = request.getOffset() / request.getLimit();
    Pageable pageRequest = PageRequest.of( page, request.getLimit(), Sort.by( "id" ).descending() );

    var paged = this.orderPersistence.findAll( pageRequest );

    var result = new PaginatedResponseDto<OrderDto>( page, request.getLimit(), paged.getTotalElements() );

    paged.stream().forEach( x -> result.getData().add( this.transform( x ) ) );

    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<OrderDto> find( Integer orderId )
  {
    GenericResponseDto<OrderDto> response = null;

    var optional = this.orderPersistence.findById( orderId );
    if( optional.isPresent() )
    {
      response = new GenericResponseDto<>();
      response.setBody( this.transform( optional.get() ) );
    }

    return response;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<OrderDto> create( OrderRequestDto order )
  {

    OrderDO entity = new OrderDO();

    var customer = this.customerPersistence.findById( order.getCustomerId() ).get();
    entity.setCustomer( customer );

    entity.setOrder( Calendar.getInstance().getTime() );

    var subtotal = BigDecimal.ZERO;

    var details = new ArrayList<OrderDetailDO>();
    order.getItems().stream().forEach( i -> {
      var detail = new OrderDetailDO();

      var item = this.itemPersistence.findBySku( i.getSku() );
      detail.setItem( item );

      detail.setOrder( entity );
      detail.setPrice( item.getPrice() );
      detail.setQuantity( i.getQuantity() );
      detail.setSubtotal( item.getPrice().multiply( new BigDecimal( i.getQuantity() ) ) );

      details.add( detail );
    } );

    for( var d : details )
    {
      subtotal = subtotal.add( d.getSubtotal() );
    }

    entity.setSubtotal( subtotal );
    entity.setTax( subtotal.multiply( new BigDecimal( "0.16" ) ) );
    entity.setTotal( subtotal.add( entity.getTax() ) );

    this.orderPersistence.save( entity );

    details.stream().forEach( d -> {
      this.orderDetailPersistence.save( d );
    } );

    entity.setItems( details );
    this.orderPersistence.save( entity );

    var response = new OrderDto();
    response.setId( entity.getId() );
    response.setCustomer( new CustomerDto() );
    response.getCustomer().setId( entity.getCustomer().getId() );
    response.getCustomer().setFirstName( entity.getCustomer().getFirstName() );
    response.getCustomer().setLastName( entity.getCustomer().getLastName() );
    response.getCustomer().setSecondLastName( entity.getCustomer().getSecondLastName() );
    response.getCustomer().setRfc( entity.getCustomer().getRfc() );
    response.setSubtotal( entity.getSubtotal() );
    response.setTax( entity.getTax() );
    response.setTotal( entity.getTotal() );
    
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
    response.setOrder( df.format( entity.getOrder() ) );

    return new GenericResponseDto<>( response );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> update( OrderDto order )
  {

    return new GenericResponseDto<>( true );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> delete( Integer orderId )
  {
    var optional = this.orderPersistence.findById( orderId );
    if( optional.isEmpty() )
    {
      throw new BusinessException( ErrorCode.ORDER_NOT_FOUND );
    }

    var entity = optional.get();
    this.orderPersistence.delete( entity );

    return new GenericResponseDto<>( true );
  }

  @Override
  public List<OrderGraphQLDto> findGraphQL( OrderQueryDto query, DataFetchingEnvironment env )
  {
    var order = QOrderDO.orderDO;
    // var predicates = this.getOrderPredicates( order, query );
    //
    // return this.processPredicate( predicates, query );
    return null;
  }

  /**
   * {@inheritDoc}
   */
  public void processMessage( String message )
  {
    log.info( "-> Mensaje: {}", message );

    Gson gson = new GsonBuilder().create();

    var obj = gson.fromJson( message, MessageDto.class );

    log.info( "{}", obj.getJson() );
    var order = gson.fromJson( obj.getJson(), OrderDto.class );
    log.info( "{}", order );
  }

  private OrderDto transform( OrderDO entity )
  {
    OrderDto dto = null;
    if( entity != null )
    {
      dto = this.mapper.map( entity, OrderDto.class );
    }
    return dto;
  }

  private List<OrderGraphQLDto> processPredicate( List<Predicate> predicates, OrderQueryDto query )
  {
    var orders = new ArrayList<OrderGraphQLDto>();

    Pageable pageable = extractPageable( query );

    if( predicates.isEmpty() )
    {
      var result = this.orderGraphQLRepository.findAll( pageable );
      orders.addAll( result.stream().map( orderDO -> OrderGraphQLDtoTransformer.transform( orderDO ) )
          .collect( Collectors.toList() ) );
    }
    else
    {
      var result = this.orderGraphQLRepository.findAll( ExpressionUtils.allOf( predicates ), pageable );
      result.forEach( orderDO -> orders.add( OrderGraphQLDtoTransformer.transform( orderDO ) ) );
    }

    return orders;
  }

  private Pageable extractPageable( OrderQueryDto query )
  {
    Pageable pageable;
    if( query.getSize() > 0 )
    {
      pageable = PageRequest.of( query.getPage(), query.getSize() );
    }
    else
    {
      pageable = PageRequest.of( 0, 50 );
    }
    return pageable;
  }
}
