package com.contact.management.service.contactmanagementsystem.manager;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.contact.management.service.contactmanagementsystem.entity.User;
import com.contact.management.service.contactmanagementsystem.repository.UserRepository;

@Service
public class UserManager {

  @Autowired
  private UserRepository userRepository;

  public User getTopById(Long userId) {
    if (Objects.isNull(userId)) {
      return null;
    }
    return userRepository.getTopById(userId);
  }

}
