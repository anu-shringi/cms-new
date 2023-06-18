package com.contact.management.service.contactmanagementsystem.exceptions;

import org.springframework.http.HttpStatus;
import com.contact.management.service.contactmanagementsystem.common.Constant.ErrorCode;

public class ContactListNotFoundException extends RuntimeBaseException{

  public ContactListNotFoundException(String message) {
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
