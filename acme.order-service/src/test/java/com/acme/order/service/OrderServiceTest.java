package com.acme.order.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.acme.order.commons.request.ItemRequestDto;
import com.acme.order.commons.request.OrderRequestDto;

/**
 * Class OrderServiceTest
 * 
 * @author guillermo.segura@axity.com
 */
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
class OrderServiceTest
{
  private static final Logger LOG = LoggerFactory.getLogger( OrderServiceTest.class );

  @Autowired
  private OrderService orderService;

  @MockBean
  private KafkaTemplate<Object, Object> template;

  @Test
  public void testCreate() {
    
    var order = new OrderRequestDto();
    order.setCustomerId( 1 );
    
    var items = new ArrayList<ItemRequestDto>();
    var item1 = new ItemRequestDto();
    item1.setQuantity( 1 );
    item1.setSku( "A0001" );
    
    var item2 = new ItemRequestDto();
    item2.setQuantity( 1 );
    item2.setSku( "A0002" );
    
    items.add( item1 );
    items.add( item2 );
    
    order.setItems( items );
    
    var result = this.orderService.create( order  );
    
    assertNotNull( result );
    
  }
}
