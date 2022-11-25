package com.acme.order.commons.dto;

import java.math.BigDecimal;

/**
 * @author guillermo.segura@axity.com
 */
public class OrderDetailDto
{
  private Integer id;

  private int orderId;

  private ItemDto item;

  private int quantity;

  private BigDecimal price;

  private BigDecimal subtotal;
}
