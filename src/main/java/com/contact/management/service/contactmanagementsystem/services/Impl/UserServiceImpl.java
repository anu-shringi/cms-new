package com.contact.management.service.contactmanagementsystem.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.contact.management.service.contactmanagementsystem.entity.User;
import com.contact.management.service.contactmanagementsystem.repository.UserRepository;
import com.contact.management.service.contactmanagementsystem.services.UserService;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public User createUser(User user) {
    User savedUser = userRepository.save(user);
    return savedUser;
  }

}

