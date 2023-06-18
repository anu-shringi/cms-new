package com.contact.management.service.contactmanagementsystem.services.Impl;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.contact.management.service.contactmanagementsystem.common.Constant.ExceptionMessage;
import com.contact.management.service.contactmanagementsystem.entity.Contact;
import com.contact.management.service.contactmanagementsystem.exceptions.ContactNotFoundException;
import com.contact.management.service.contactmanagementsystem.manager.ContactManager;
import com.contact.management.service.contactmanagementsystem.services.ContactService;

@Transactional
@Service
public class ContactServiceImpl implements ContactService {

  @Autowired
  private ContactManager contactManager;

  @Override
  public List<Contact> getAllContacts(Long userId) {
    return contactManager.findAllByUserId(userId);
  }

  @Override
  public List<Contact> searchContacts(String searchKey, Long userId) {
    return contactManager.searchContacts(searchKey, userId);
  }

  @Override
  public Contact createContact(Contact contactDTO, Long userId) {
    Contact existingContact = contactManager.findByPhoneAndUserId(contactDTO.getPhone(), userId);
    if (Objects.isNull(existingContact)) {
      Contact contact = new Contact();
      contact.setFirstName(contactDTO.getFirstName());
      contact.setEmail(contactDTO.getEmail());
      contact.setPhone(contactDTO.getPhone());
      contact.setLastName(contactDTO.getLastName());
      contact.setUserId(userId);
      return contactManager.save(contact);
    }
    return existingContact;
  }

  @Override
  public Contact updateContact(Long id, Contact updatedContact, Long userId)
      throws ContactNotFoundException {
    Contact existingContact = contactManager.findByIdAndUserId(id, userId);
    if (Objects.nonNull(existingContact)) {
      existingContact.setFirstName(updatedContact.getFirstName());
      existingContact.setLastName(updatedContact.getLastName());
      existingContact.setEmail(updatedContact.getEmail());
      existingContact.setPhone(updatedContact.getPhone());
      return contactManager.save(existingContact);
    }
    throw new ContactNotFoundException(ExceptionMessage.CONTACT_NOT_FOUND);
  }

  @Override
  public void deleteContact(Long id, Long userId) {
    contactManager.deleteByIdAndUserId(id, userId);
  }

}
