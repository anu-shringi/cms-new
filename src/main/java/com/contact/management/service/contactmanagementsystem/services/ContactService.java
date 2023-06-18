package com.contact.management.service.contactmanagementsystem.services;

import java.util.List;
import com.contact.management.service.contactmanagementsystem.entity.Contact;
import com.contact.management.service.contactmanagementsystem.exceptions.ContactNotFoundException;

public interface ContactService {

  List<Contact> getAllContacts(Long userId);

  List<Contact> searchContacts(String searchKey, Long userId);

  Contact createContact(Contact contactDTO, Long userId);

  Contact updateContact(Long id, Contact updatedContact, Long userId) throws ContactNotFoundException;

  void deleteContact(Long id, Long userId);
}

