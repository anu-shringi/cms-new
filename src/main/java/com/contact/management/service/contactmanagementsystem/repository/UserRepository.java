package com.contact.management.service.contactmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.contact.management.service.contactmanagementsystem.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User getTopById(Long userId);

}
