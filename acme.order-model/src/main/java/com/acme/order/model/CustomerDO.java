package com.acme.order.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author guillermo.segura@axity.com
 */
@Entity
@Table(name = "TC_Customer")
@Getter
@Setter
public class CustomerDO implements Serializable
{
  private static final long serialVersionUID = -4615741829346557181L;

  @Id
  @Column(name = "cd_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "nb_firstname", length = 100)
  private String firstName;

  @Column(name = "nb_lastname", length = 100)
  private String lastName;

  @Column(name = "nb_secondlastname", length = 100)
  private String secondLastName;

  @Column(name = "nb_rfc", length = 13)
  private String rfc;

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals( Object object )
  {
    boolean isEquals = false;
    if( this == object )
    {
      isEquals = true;
    }
    else if( object != null && object.getClass().equals( this.getClass() ) )
    {
      CustomerDO that = (CustomerDO) object;

      isEquals = Objects.equals( this.id, that.id );
    }
    return isEquals;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode()
  {
    return Objects.hash( this.id );
  }
}
