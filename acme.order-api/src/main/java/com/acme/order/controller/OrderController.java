package com.acme.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acme.order.commons.aspectj.JsonResponseInterceptor;
import com.acme.order.commons.dto.OrderDto;
import com.acme.order.commons.request.OrderRequestDto;
import com.acme.order.commons.request.PaginatedRequestDto;
import com.acme.order.commons.response.GenericResponseDto;
import com.acme.order.commons.response.PaginatedResponseDto;
import com.acme.order.facade.OrderFacade;
import com.acme.order.persistence.redis.StringRedisRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

/**
 * Order controller class
 * 
 * @author guillermo.segura@axity.com
 *
 */
@RestController
@RequestMapping("/api/orders")
@CrossOrigin
@Slf4j
public class OrderController
{
  @Autowired
  private OrderFacade orderFacade;

  @Autowired
  private StringRedisRepository redis;

  /***
   * Method to get Order
   * 
   * @param limit The limit
   * @param offset The offset
   * @return A paginated result of Order
   */
  @JsonResponseInterceptor
  @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Orders", summary = "Method to get Orders paginated")
  public ResponseEntity<PaginatedResponseDto<OrderDto>> findOrders(
      @RequestParam(name = "limit", defaultValue = "50", required = false)
      int limit, @RequestParam(name = "offset", defaultValue = "0", required = false)
      int offset )
  {
    var result = this.orderFacade.findOrders( new PaginatedRequestDto( limit, offset ) );
    return ResponseEntity.ok( result );
  }

  /**
   * Method to get Order by id
   * 
   * @param orderId The order Id
   * @return An registry of order or 204
   */
  @JsonResponseInterceptor
  @GetMapping(path = "/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Orders", summary = "Method to get Order by id")
  public ResponseEntity<GenericResponseDto<OrderDto>> findOrder( @PathVariable("orderId")
  Integer orderId )
  {

    String key = this.getOrderKey( orderId );

    Gson gson = new GsonBuilder().create();
    GenericResponseDto<OrderDto> result = null;
    if( redis.hasKey( key ) )
    {
      var json = this.redis.get( key );

      result = gson.fromJson( json, new TypeToken<GenericResponseDto<OrderDto>>()
      {
      }.getType() );
    }
    else
    {
      result = this.orderFacade.find( orderId );

      String json = gson.toJson( result );
      this.redis.add( key, json );
    }

    HttpStatus status = HttpStatus.OK;
    if( result == null )
    {
      status = HttpStatus.NO_CONTENT;
    }
    return new ResponseEntity<>( result, status );
  }

  private String getOrderKey( Integer orderId )
  {
    String key = new StringBuilder().append( "Order.byOrderId:" ).append( orderId ).toString();
    return key;
  }

  /**
   * Method to create a Order
   * 
   * @param order The Order object
   * @return
   */
  @JsonResponseInterceptor
  @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Orders", summary = "Method to create a Order")
  public ResponseEntity<GenericResponseDto<OrderDto>> create( @RequestBody OrderRequestDto order )
  {
    var result = this.orderFacade.create( order );
    return new ResponseEntity<>( result, HttpStatus.CREATED );
  }

  /**
   * Method to update a Order
   * 
   * @param orderId The Order Id
   * @param order The Order object
   * @return
   */
  @JsonResponseInterceptor
  @PutMapping(path = "/{orderId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Orders", summary = "Method to update a Order")
  public ResponseEntity<GenericResponseDto<Boolean>> update( @PathVariable("orderId") Integer orderId, @RequestBody OrderDto order )
  {
    order.setId( orderId );
    var result = this.orderFacade.update( order );

//    if( result.getBody() )
//    {
//      this.redis.delete( this.getOrderKey( orderId ) );
//    }

    return ResponseEntity.ok( result );
  }

  /**
   * Method to delete a Order
   * 
   * @param orderId The Order Id
   * @return
   */
  @JsonResponseInterceptor
  @DeleteMapping(path = "/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Orders", summary = "Method to delete a Order")
  public ResponseEntity<GenericResponseDto<Boolean>> delete( @PathVariable("orderId") Integer orderId )
  {
    var result = this.orderFacade.delete( orderId );
//    if( result.getBody() )
//    {
//      this.redis.delete( this.getOrderKey( orderId ) );
//    }
    return ResponseEntity.ok( result );
  }

  /**
   * Ping
   * 
   * @return Pong
   */
  @JsonResponseInterceptor
  @GetMapping(path = "ping", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Orders", summary = "Ping")
  public ResponseEntity<GenericResponseDto<String>> ping( )
  {
    return ResponseEntity.ok( new GenericResponseDto<>("pong") );
  }
}
