package com.acme.order.facade;

import java.util.List;

import com.acme.order.commons.dto.OrderDto;
import com.acme.order.commons.request.OrderRequestDto;
import com.acme.order.commons.request.PaginatedRequestDto;
import com.acme.order.commons.request.graphql.OrderQueryDto;
import com.acme.order.commons.response.GenericResponseDto;
import com.acme.order.commons.response.PaginatedResponseDto;
import com.acme.order.commons.response.graphql.OrderGraphQLDto;

import graphql.schema.DataFetchingEnvironment;

/**
 * Interface OrderFacade
 * 
 * @author guillermo.segura@axity.com
 */
public interface OrderFacade
{
  /**
   * Method to get Orders
   * 
   * @param request
   * @return
   */
  PaginatedResponseDto<OrderDto> findOrders( PaginatedRequestDto request );

  /**
   * Method to get Order by id
   * 
   * @param orderId
   * @return
   */
  GenericResponseDto<OrderDto> find( Integer orderId );

  /**
   * Method to create a Order
   * 
   * @param order
   * @return
   */
  GenericResponseDto<OrderDto> create( OrderRequestDto order );

  /**
   * Method to update a Order
   * 
   * @param order
   * @return
   */
  GenericResponseDto<Boolean> update( OrderDto order );

  /**
   * Method to delete a Order
   * 
   * @param orderCode
   * @return
   */
  GenericResponseDto<Boolean> delete( Integer orderId );

  /**
   * Method to query by example
   * 
   * @param query
   * @param env
   * @return
   */
  List<OrderGraphQLDto> findGraphQL( OrderQueryDto query, DataFetchingEnvironment env );

  /**
   * Method to process a message
   * 
   * @param message
   */
  void processMessage( String message );
}
