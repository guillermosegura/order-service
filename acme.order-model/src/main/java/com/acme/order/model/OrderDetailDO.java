package com.acme.order.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author guillermo.segura@axity.com
 */
@Entity
@Table(name = "TE_OrderDetail")
@Getter
@Setter
public class OrderDetailDO implements Serializable
{
  private static final long serialVersionUID = -7818956022138937125L;

  @Id
  @Column(name = "cd_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "cd_order", referencedColumnName = "cd_id")
  private OrderDO order;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "cd_item", referencedColumnName = "cd_id")
  private ItemDO item;

  @Column(name = "nu_quantity")
  private int quantity;

  @Column(name = "im_price", precision = 10, scale = 2)
  private BigDecimal price;

  @Column(name = "im_subtotal", precision = 10, scale = 2)
  private BigDecimal subtotal;

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
      OrderDetailDO that = (OrderDetailDO) object;

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
