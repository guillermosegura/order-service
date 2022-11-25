package com.acme.order.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acme.order.model.CustomerDO;

/**
 * Persistence interface of de {@link com.acme.order.model.CustomerDO}
 * 
 * @author guillermo.segura@axity.com
 */
@Repository
public interface CustomerPersistence extends JpaRepository<CustomerDO, Integer>
{

}
