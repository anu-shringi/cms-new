package com.contact.management.service.contactmanagementsystem.exceptions;

import org.springframework.http.HttpStatus;
import com.contact.management.service.contactmanagementsystem.common.Constant.ErrorCode;

public class UnauthorizedException extends BaseException {

  public UnauthorizedException(String message) {
    super(message);
  }

  @Override
  public int getErrorCode() {
    return ErrorCode.UNAUTHORIZED;
  }

  @Override
  public HttpStatus getHttpStatus() {
    return HttpStatus.UNAUTHORIZED;
  }

}
