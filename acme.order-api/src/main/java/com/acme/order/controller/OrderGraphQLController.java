package com.acme.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.acme.order.commons.request.graphql.OrderQueryDto;
import com.acme.order.commons.response.graphql.OrderGraphQLDto;
import com.acme.order.facade.OrderFacade;

import graphql.schema.DataFetchingEnvironment;

/**
 * Order GraphQL controller class
 * 
 * @author guillermo.segura@axity.com
 *
 */
@Controller
public class OrderGraphQLController
{
  @Autowired
  private OrderFacade orderFacade;

  @QueryMapping(name = "orders")
  public List<OrderGraphQLDto> getAllByExample( @Argument OrderGraphQLDto query, @Argument Integer page,
      @Argument Integer size, DataFetchingEnvironment env )
  {
    var wrapper = new OrderQueryDto();
    wrapper.setQuery( query );

    if( page != null )
    {
      wrapper.setPage( page );
    }

    if( size != null )
    {
      wrapper.setSize( size );
    }

    return this.orderFacade.findGraphQL( wrapper, env );
  }
}
