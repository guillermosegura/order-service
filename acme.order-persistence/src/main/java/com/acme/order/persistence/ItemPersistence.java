package com.acme.order.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.acme.order.model.ItemDO;

/**
 * Persistence interface of de {@link com.acme.order.model.ItemDO}
 * 
 * @author guillermo.segura@axity.com
 */
@Repository
public interface ItemPersistence extends JpaRepository<ItemDO, Integer>
{

  /**
   * @param sku
   * @return
   */
  @Query("SELECT i FROM ItemDO i where sku = :sku ")
  ItemDO findBySku( @Param("sku") String sku );

}
