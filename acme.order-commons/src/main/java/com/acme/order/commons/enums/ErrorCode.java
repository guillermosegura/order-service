package com.acme.order.commons.enums;

/**
 * Error code enumeration
 * 
 * @author guillermo.segura@axity.com
 */
public enum ErrorCode
{

  UNKNOWN_ERROR(0), REQUIRED_FIELD(1), EXCEEDS_MAX_LENGTH(2),

  // Validation errors
  ORDER_ALREADY_EXISTS(100), ORDER_NOT_FOUND(101);

  private int code;

  private ErrorCode( int code )
  {
    this.code = code;
  }

  /**
   * @return the code
   */
  public int getCode()
  {
    return code;
  }

}
