package com.acme.order.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Query Wrapper Dto class
 * 
 * @author guillermo.segura@axity.com
 */
@Getter
@Setter
public class QueryWrapperDto
{
  private String query;
  private Object variables = new Object();
}