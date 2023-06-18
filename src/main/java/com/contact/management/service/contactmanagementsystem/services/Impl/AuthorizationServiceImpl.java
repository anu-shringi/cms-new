package com.contact.management.service.contactmanagementsystem.services.Impl;

import java.util.Base64;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.contact.management.service.contactmanagementsystem.entity.User;
import com.contact.management.service.contactmanagementsystem.manager.UserManager;
import com.contact.management.service.contactmanagementsystem.services.AuthorizationService;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

  @Autowired
  private UserManager userManager;

  @Override
  public boolean authorizeUser(String accessToken, Long userId) {
    User user = userManager.getTopById(userId);
    if (Objects.isNull(accessToken) || Objects.isNull(user)) {
      return false;
    }
    byte[] decodedAccessToken = Base64.getDecoder().decode(accessToken);
    String decodedToken = new String(decodedAccessToken);
    if (decodedToken.equals(userId.toString())) {
      return true;
    }
    return false;
  }
}
