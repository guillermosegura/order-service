package com.acme.order.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity class of the table order
 * 
 * @author guillermo.segura@axity.com
 */
@Entity
@Table(name = "TE_Order")
@Getter
@Setter
public class OrderDO implements Serializable
{
  private static final long serialVersionUID = -5035830587965574416L;

  @Id
  @Column(name = "cd_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "cd_customer", referencedColumnName = "cd_id")
  private CustomerDO customer;
  
  @Column(name ="dt_order")
  @Temporal(TemporalType.TIMESTAMP)
  private Date order;
  
  @Column(name = "im_subtotal", precision = 10, scale = 2)
  private BigDecimal subtotal;
  
  @Column(name = "im_tax", precision = 10, scale = 2)
  private BigDecimal tax;
  
  @Column(name = "im_total", precision = 10, scale = 2)
  private BigDecimal total;
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
  private List<OrderDetailDO> items;

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
      OrderDO that = (OrderDO) object;

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
