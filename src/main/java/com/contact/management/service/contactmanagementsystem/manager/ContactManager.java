package com.contact.management.service.contactmanagementsystem.manager;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.contact.management.service.contactmanagementsystem.entity.Contact;
import com.contact.management.service.contactmanagementsystem.repository.ContactRepository;

@Service
public class ContactManager {

  @Autowired
  private ContactRepository contactRepository;

  public Contact save(Contact contact) {
    return contactRepository.save(contact);
  }

  public Contact findByPhone(String phone) {
    return contactRepository.getTopByPhone(phone);
  }

  public List<Contact> searchContacts(String searchKey, Long userId) {
    return contactRepository.searchContacts(searchKey, userId);
  }

  public List<Contact> findAllByUserId(Long userId) {
    return contactRepository.findAllByUserId(userId);
  }

  public Contact findByIdAndUserId(Long id, Long userId) {
    return contactRepository.getTopByIdAndUserId(id, userId);
  }

  public void deleteByIdAndUserId(Long id, Long userId) {
    contactRepository.deleteByIdAndUserId(id, userId);
  }

  public Contact findByPhoneAndUserId(String phone, Long userId) {
    return contactRepository.getTopByPhoneAndUserId(phone, userId);
  }
}
