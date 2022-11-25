package com.acme.order.service.util;

import com.acme.order.commons.response.graphql.OrderGraphQLDto;
import com.acme.order.model.OrderDO;

/**
 * Order GraphQL Dto Transformer class
 * @author guillermo.segura@axity.com
 */
public final class OrderGraphQLDtoTransformer
{
  private OrderGraphQLDtoTransformer()
  {
  }

  /**
   * Utility class for transform a {@link com.acme.order.model.OrderDO} into a
   * {@link com.acme.order.commons.response.graphql.OrderGraphQLDto} class
   * 
   * @param entity
   * @return
   */
  public static OrderGraphQLDto transform( OrderDO entity )
  {
    OrderGraphQLDto order = null;

    if( entity != null )
    {
    }
    return order;
  }
}
