package com.contact.management.service.contactmanagementsystem.common;

public class Constant {

  public static final String ACCESS_TOKEN = "access_token";

  public static class ErrorCode {
    public static final int CONTACT_NOT_FOUNT = 1;
    public static final int UNAUTHORIZED = 2;
  }

  public static class ExceptionMessage {
    public static final String UNAUTHORIZED_USER = "Unauthorized User";
    public static final String CONTACT_NOT_FOUND = "Contact not found";
  }

}
