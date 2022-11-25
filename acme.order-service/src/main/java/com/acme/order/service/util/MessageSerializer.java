package com.acme.order.service.util;

import org.springframework.kafka.support.serializer.JsonSerializer;

import com.acme.order.commons.request.MessageDto;

/**
 * Message Serializer class
 * 
 * @author guillermo.segura@axity.com
 */
public class MessageSerializer extends JsonSerializer<MessageDto>
{

}
