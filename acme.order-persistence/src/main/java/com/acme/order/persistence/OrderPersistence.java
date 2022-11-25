package com.acme.order.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acme.order.model.OrderDO;

/**
 * Persistence interface of de {@link com.acme.order.model.OrderDO}
 * 
 * @author guillermo.segura@axity.com
 */
@Repository
public interface OrderPersistence extends JpaRepository<OrderDO, Integer>
{

}
