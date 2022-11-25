package com.acme.order.facade;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.acme.order.commons.dto.OrderDto;
import com.acme.order.commons.request.PaginatedRequestDto;
import com.acme.order.commons.request.graphql.OrderQueryDto;
import com.acme.order.commons.response.GenericResponseDto;
import com.acme.order.commons.response.PaginatedResponseDto;
import com.acme.order.commons.response.graphql.OrderGraphQLDto;
import com.acme.order.facade.impl.OrderFacadeImpl;
import com.acme.order.service.OrderService;

import graphql.schema.DataFetchingEnvironment;

/**
 * Class OrderFacadeTest
 * 
 * @author guillermo.segura@axity.com
 */
class OrderFacadeTest
{
  private OrderFacade orderFacade;
  private OrderService orderService;

  @BeforeEach
  public void setUp()
  {
    this.orderFacade = new OrderFacadeImpl();

    this.orderService = mock( OrderService.class );
    ReflectionTestUtils.setField( this.orderFacade, "orderService", this.orderService );
  }

  /**
   * Test method for
   * {@link com.acme.order.facade.impl.OrderFacadeImpl#findOrders(com.acme.order.commons.request.PaginatedRequestDto)}.
   */
  @Test
  void testFindOrders()
  {
    int pageSize = 20;

    var data = new ArrayList<OrderDto>();
    for( int i = 0; i < pageSize; i++ )
    {
      data.add( this.createOrder( i + 1 ) );
    }
    var paginated = new PaginatedResponseDto<OrderDto>( 0, pageSize, 50, data );
    when( this.orderService.findOrders( any( PaginatedRequestDto.class ) ) ).thenReturn( paginated );

    var result = this.orderFacade.findOrders( new PaginatedRequestDto( pageSize, 0 ) );
    assertNotNull( result );
  }

  /**
   * Test method for {@link com.acme.order.facade.impl.OrderFacadeImpl#find(java.lang.String)}.
   */
  @Test
  void testFind()
  {
    var response = new GenericResponseDto<OrderDto>( this.createOrder( 1 ) );
    when( this.orderService.find( anyInt() ) ).thenReturn( response );

    var result = this.orderFacade.find( 1 );
    assertNotNull( result );
  }

  /**
   * Test method for
   * {@link com.acme.order.facade.impl.OrderFacadeImpl#create(com.acme.order.commons.dto.OrderDto)}.
   */
  @Test
  @Disabled("Pendiente ajustar")
  void testCreate()
  {
    var order = this.createOrder( 8 );
    var response = new GenericResponseDto<>( order );
//    when( this.orderService.create( any( OrderDto.class ) ) ).thenReturn( response );
//
//    var result = this.orderFacade.create( order );
//    assertNotNull( result );
  }

  /**
   * Test method for
   * {@link com.acme.order.facade.impl.OrderFacadeImpl#update(com.acme.order.commons.dto.OrderDto)}.
   */
  @Test
  void testUpdate()
  {
    var order = this.createOrder( 1 );

    var response = new GenericResponseDto<>( true );
    when( this.orderService.update( any( OrderDto.class ) ) ).thenReturn( response );
    var result = this.orderFacade.update( order );
    assertNotNull( result );
  }

  /**
   * Test method for {@link com.acme.order.facade.impl.OrderFacadeImpl#delete(java.lang.String)}.
   */
  @Test
  void testDelete()
  {
    var response = new GenericResponseDto<>( true );
    when( this.orderService.delete( anyInt() ) ).thenReturn( response );

    var result = this.orderFacade.delete( 9 );
    assertNotNull( result );
  }

  private OrderDto createOrder( int i )
  {
    var order = new OrderDto();
    order.setId( i );
    return order;
  }

  @Test
  void testFindGraphQL()
  {
    var list = new ArrayList<OrderGraphQLDto>();
    when( this.orderService.findGraphQL( any( OrderQueryDto.class ), any( DataFetchingEnvironment.class ) ) )
        .thenReturn( list );

    var query = new OrderQueryDto();
    var result = this.orderFacade.findGraphQL( query, null );
    assertNotNull( result );
  }

}
