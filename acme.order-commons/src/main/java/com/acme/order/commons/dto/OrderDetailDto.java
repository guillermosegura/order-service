package com.acme.order.commons.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * @author guillermo.segura@axity.com
 */
@Getter
@Setter
public class OrderDetailDto
{
  private Integer id;

  private int orderId;

  private ItemDto item;

  private int quantity;

  private BigDecimal price;

  private BigDecimal subtotal;
}
