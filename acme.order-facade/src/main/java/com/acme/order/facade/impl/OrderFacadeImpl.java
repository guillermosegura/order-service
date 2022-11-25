package com.acme.order.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acme.order.commons.dto.OrderDto;
import com.acme.order.commons.request.OrderRequestDto;
import com.acme.order.commons.request.PaginatedRequestDto;
import com.acme.order.commons.request.graphql.OrderQueryDto;
import com.acme.order.commons.response.GenericResponseDto;
import com.acme.order.commons.response.PaginatedResponseDto;
import com.acme.order.commons.response.graphql.OrderGraphQLDto;
import com.acme.order.facade.OrderFacade;
import com.acme.order.service.OrderService;

import graphql.schema.DataFetchingEnvironment;

/**
 * Class OrderFacadeImpl
 * @author guillermo.segura@axity.com
 */
@Service
@Transactional
public class OrderFacadeImpl implements OrderFacade
{
  @Autowired
  private OrderService orderService;
  /**
   * {@inheritDoc}
   */
  @Override
  public PaginatedResponseDto<OrderDto> findOrders( PaginatedRequestDto request )
  {
    return this.orderService.findOrders( request );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<OrderDto> find( Integer orderId )
  {
    return this.orderService.find( orderId );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<OrderDto> create( OrderRequestDto order )
  {
    return this.orderService.create( order );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> update( OrderDto order )
  {
    return this.orderService.update( order );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> delete( Integer orderId )
  {
    return this.orderService.delete( orderId );
  }

  /**
   * {@inheritDoc}
   */
  public List<OrderGraphQLDto> findGraphQL( OrderQueryDto query, DataFetchingEnvironment env )
  {
    return this.orderService.findGraphQL( query, env );
  }

  /**
   * {@inheritDoc}
   */
  public void processMessage( String message )
  {
    this.orderService.processMessage( message );
  }
}
