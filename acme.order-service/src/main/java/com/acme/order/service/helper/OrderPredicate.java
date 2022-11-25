package com.acme.order.service.helper;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import com.acme.order.model.QOrderDO;
import com.querydsl.core.types.Predicate;

/**
 * Class OrderPredicate
 * 
 * @author guillermo.segura@axity.com
 */
public final class OrderPredicate
{
  private OrderPredicate()
  {
  }

  /**
   * @param orderCode
   * @param order
   * @param predicates
   */
  public static void evaluateOrderId( Integer orderId, QOrderDO order, ArrayList<Predicate> predicates )
  {
    if( orderId != null )
    {
      predicates.add( order.id.eq( orderId ) );
    }
  }

}