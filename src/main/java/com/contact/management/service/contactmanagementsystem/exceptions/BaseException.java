package com.contact.management.service.contactmanagementsystem.exceptions;

import org.springframework.http.HttpStatus;

public abstract class BaseException extends Exception {

  public BaseException(String message) {
    super(message);
  }

  public abstract int getErrorCode();

  public abstract HttpStatus getHttpStatus();

}
