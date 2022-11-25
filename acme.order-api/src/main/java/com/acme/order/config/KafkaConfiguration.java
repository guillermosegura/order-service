package com.acme.order.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import com.acme.order.facade.OrderFacade;

import lombok.extern.slf4j.Slf4j;

/**
 * Kafka configuration class
 * 
 * @author guillermo.segura@axity.com
 */
@Configuration
@Slf4j
public class KafkaConfiguration
{
  @Autowired
  private OrderFacade orderFacade;

  @KafkaListener(id = "listener", topics = "test")
  public void listen( String message )
  {
    try
    {
      this.orderFacade.processMessage( message );
    }
    catch( Exception e )
    {
      log.error( "El mensaje no pudo ser procesado: {}", message );
      log.error( e.getMessage(), e );
    }
  }
}
