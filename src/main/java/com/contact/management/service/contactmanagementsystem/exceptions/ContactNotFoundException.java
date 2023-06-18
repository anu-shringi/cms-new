package com.contact.management.service.contactmanagementsystem.exceptions;

import org.springframework.http.HttpStatus;
import com.contact.management.service.contactmanagementsystem.common.Constant.ErrorCode;

public class ContactNotFoundException extends RuntimeBaseException {

  public ContactNotFoundException(String message) {
    super(message);
  }

  @Override
  public int getErrorCode() {
    return ErrorCode.CONTACT_NOT_FOUNT;
  }

  @Override
  public HttpStatus getHttpStatus() {
    return HttpStatus.NOT_FOUND;
  }

}
