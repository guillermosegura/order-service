package com.acme.order.commons.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.Getter;
import lombok.Setter;

/**
 * Order Transfer Object classe
 * 
 * @author guillermo.segura@axity.com
 */
@Getter
@Setter
public class OrderDto implements Serializable
{
  private static final long serialVersionUID = -1468484645398717478L;

  private Integer id;
  
  private CustomerDto customer;
  
  private String order;
  
  private BigDecimal subtotal;
  
  private BigDecimal tax;
  
  private BigDecimal total;
  
  private List<OrderDetailDto> items;

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString()
  {
    Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    return gson.toJson( this );
  }
}
