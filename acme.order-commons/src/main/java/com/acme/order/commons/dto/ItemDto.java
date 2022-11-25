package com.acme.order.commons.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * @author guillermo.segura@axity.com
 */
@Getter
@Setter
public class ItemDto
{
  private Integer id;

  private String sku;

  private String description;

  private BigDecimal price;
}
