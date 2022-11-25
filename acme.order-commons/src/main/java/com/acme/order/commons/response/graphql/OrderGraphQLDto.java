package com.acme.order.commons.response.graphql;

import lombok.Getter;
import lombok.Setter;

/**
 * Order GraphQL Transfer Object class
 * 
 * @author guillermo.segura@axity.com
 */
@Getter
@Setter
public class OrderGraphQLDto
{
  private Integer id;
  private String city;
  private String phone;
  private String addressLine1;
  private String addressLine2;
  private String state;
  private String country;
  private String postalCode;
  private String territory;
}
