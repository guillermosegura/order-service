package com.acme.order.commons.request;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author guillermo.segura@axity.com
 */
@Getter
@Setter
public class OrderRequestDto implements Serializable
{
  private static final long serialVersionUID = 2493858377553576616L;
  private int customerId;
  private List<ItemRequestDto> items;
}
