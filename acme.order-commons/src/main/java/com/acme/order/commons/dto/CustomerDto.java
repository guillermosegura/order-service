package com.acme.order.commons.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

/**
 * @author guillermo.segura@axity.com
 */
@Getter
@Setter
public class CustomerDto implements Serializable
{
  private static final long serialVersionUID = 3207211427198537826L;

  private Integer id;

  private String firstName;

  private String lastName;

  private String secondLastName;

  private String rfc;
}
