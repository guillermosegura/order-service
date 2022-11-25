package com.acme.order.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acme.order.model.OrderDO;
import com.acme.order.model.OrderDetailDO;

/**
 * Persistence interface of de {@link com.acme.order.model.OrderDetailDO}
 * 
 * @author guillermo.segura@axity.com
 */
@Repository
public interface OrderDetailPersistence extends JpaRepository<OrderDetailDO, Integer>
{

}
