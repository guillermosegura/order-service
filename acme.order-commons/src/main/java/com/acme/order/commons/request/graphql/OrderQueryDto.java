package com.acme.order.commons.request.graphql;

import com.acme.order.commons.response.graphql.OrderGraphQLDto;

import lombok.Getter;
import lombok.Setter;

/**
 * Order Graphql Query Transfer object
 * 
 * @author guillermo.segura@axity.com
 */
@Getter
@Setter
public class OrderQueryDto
{
  private OrderGraphQLDto query;
  private int page = 0;
  private int size = 50;
}
