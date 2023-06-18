package com.contact.management.service.contactmanagementsystem.services;

public interface AuthorizationService {

  boolean authorizeUser(String accessToken, Long userId);
}
