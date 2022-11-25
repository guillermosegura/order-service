package com.acme.order.persistence.graphql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.acme.order.model.OrderDO;

/**
 * Interface OrderGraphQLRepository
 * 
 * @author guillermo.segura@axity.com
 */
public interface OrderGraphQLRepository extends JpaRepository<OrderDO, Long>, QuerydslPredicateExecutor<OrderDO>
{

}
