package com.contact.management.service.contactmanagementsystem.exceptions;

import org.springframework.http.HttpStatus;

public abstract class RuntimeBaseException extends RuntimeException {

  public RuntimeBaseException(String message) {
    super(message);
  }

  public abstract int getErrorCode();

  public abstract HttpStatus getHttpStatus();
}
