package com.acme.order.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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
import com.acme.order.commons.request.PaginatedRequestDto;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

/**
 * Class OrderServiceTest
 * 
 * @author guillermo.segura@axity.com
 */
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
@Slf4j
class OrderServiceTest
{
  private static final Logger LOG = LoggerFactory.getLogger( OrderServiceTest.class );

  @Autowired
  private OrderService orderService;

  @MockBean
  private KafkaTemplate<Object, Object> template;

  @Test
  void testCreate()
  {

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

    var result = this.orderService.create( order );

    var gson = new GsonBuilder().setPrettyPrinting().create();
    log.info( gson.toJson( result ) );

    assertAll( "Not empty", () -> assertNotNull( result ), () -> assertNotNull( result.getBody() ),
      () -> assertNotNull( result.getBody().getCustomer() ) );

    assertEquals( 1, result.getBody().getCustomer().getId() );
    assertAll( "Amounts", () -> assertEquals( new BigDecimal( "20.00" ), result.getBody().getSubtotal() ),
      () -> assertEquals( new BigDecimal( "3.2000" ), result.getBody().getTax() ),
      () -> assertEquals( new BigDecimal( "23.2000" ), result.getBody().getTotal() ) );

  }

  @ParameterizedTest
  @ValueSource(ints = { 1, 2, 3 })
  void testFind( int id )
  {
    var result = this.orderService.find( id );
    assertNotNull( result );

    var gson = new GsonBuilder().setPrettyPrinting().create();
    log.info( gson.toJson( result ) );
  }

  @Test
  void testFindOrders()
  {
    var request = new PaginatedRequestDto();
    request.setOffset( 0 );
    request.setLimit( 10 );
    var result = this.orderService.findOrders( request );
    assertNotNull( result );

    var gson = new GsonBuilder().setPrettyPrinting().create();
    log.info( gson.toJson( result ) );
  }
}
