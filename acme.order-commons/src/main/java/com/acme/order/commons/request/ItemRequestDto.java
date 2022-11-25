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
public class ItemRequestDto implements Serializable
{
  private static final long serialVersionUID = -5278437444146502089L;
  private String sku;
  private int quantity;
}
