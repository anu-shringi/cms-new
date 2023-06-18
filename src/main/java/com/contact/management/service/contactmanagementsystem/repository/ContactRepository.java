package com.contact.management.service.contactmanagementsystem.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.contact.management.service.contactmanagementsystem.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

  Contact getTopByPhone(String phone);

  List<Contact> findAllByUserId(Long userId);

  @Query(value = "SELECT * FROM contacts c WHERE user_id = :userId AND" +
      "((LOWER(c.first_name) LIKE LOWER(CONCAT('%', :searchKey, '%'))) OR " +
      "(LOWER(c.last_name) LIKE LOWER(CONCAT('%', :searchKey, '%'))) OR " +
      "(LOWER(c.email) LIKE LOWER(CONCAT('%', :searchKey, '%'))))", nativeQuery = true)
  List<Contact> searchContacts(String searchKey, Long userId);

  Contact getTopByIdAndUserId(Long id, Long userId);

  void deleteByIdAndUserId(Long id, Long userId);

  Contact getTopByPhoneAndUserId(String phone, Long userId);

}
