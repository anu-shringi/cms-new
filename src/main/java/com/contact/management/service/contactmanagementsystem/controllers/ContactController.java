package com.contact.management.service.contactmanagementsystem.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.contact.management.service.contactmanagementsystem.auth.AuthorizeUser;
import com.contact.management.service.contactmanagementsystem.entity.Contact;
import com.contact.management.service.contactmanagementsystem.exceptions.ContactNotFoundException;
import com.contact.management.service.contactmanagementsystem.services.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/contacts")
public class ContactController {

  @Autowired
  private ContactService contactService;

  @AuthorizeUser
  @Operation(summary = "Get all contacts")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful response",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = Contact.class))}),
      @ApiResponse(responseCode = "500", description = "Internal server error",
          content = @Content)})
  @GetMapping("/{id}")
  public ResponseEntity<List<Contact>> getAllContacts(
      @Parameter(description = "ID of the user who want all the contacts") @PathVariable("id") Long userId,
      @RequestHeader(value = "access_token") String accessToken) {
    try {
      List<Contact> contacts = contactService.getAllContacts(userId);
      return new ResponseEntity<>(contacts, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @AuthorizeUser
  @Operation(summary = "Search contacts")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Contact found successfully",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = Contact.class))}),
      @ApiResponse(responseCode = "500", description = "Internal server error",
          content = @Content)})
  @GetMapping("/search")
  public ResponseEntity<List<Contact>> searchContacts(
      @Parameter(description = "ID of the user who want all the contacts") @RequestParam("id") Long userId,
      @Parameter(description = "Keyword to search for in contacts (first name, last name, or email)")
      @RequestParam String key,
      @RequestHeader(value = "access_token") String accessToken) {
    try {
      List<Contact> contacts = contactService.searchContacts(key, userId);
      return new ResponseEntity<>(contacts, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @AuthorizeUser
  @Operation(summary = "Create a new contact")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Contact created successfully",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = Contact.class))}),
      @ApiResponse(responseCode = "500", description = "Internal server error",
          content = @Content),
      @ApiResponse(responseCode = "400", description = "Invalid request payload",
          content = @Content)})
  @PostMapping
  public ResponseEntity<Contact> createContact(
      @Parameter(description = "ID of the user who want all the contacts") @RequestParam("id") Long userId,
      @RequestBody Contact contact,
      @RequestHeader(value = "access_token") String accessToken) {
    try {
      Contact newContact = contactService.createContact(contact, userId);
      return new ResponseEntity<>(newContact, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @AuthorizeUser
  @Operation(summary = "Update a contact")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Contact updated successfully",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = Contact.class))}),
      @ApiResponse(responseCode = "500", description = "Internal server error",
          content = @Content),
      @ApiResponse(responseCode = "400", description = "Invalid request payload",
          content = @Content),
      @ApiResponse(responseCode = "404", description = "Contact not found",
          content = @Content)})
  @PutMapping("/{id}")
  public ResponseEntity<Contact> updateContact(
      @Parameter(description = "ID of the user who want all the contacts") @RequestParam("user_id") Long userId,
      @Parameter(description = "ID of the contact to update") @PathVariable("id") Long id,
      @RequestBody Contact updatedContactDTO,
      @RequestHeader(value = "access_token") String accessToken)
      throws ContactNotFoundException {
    try {
      Contact contact = contactService.updateContact(id, updatedContactDTO, userId);
      return new ResponseEntity<>(contact, HttpStatus.OK);
    } catch (ContactNotFoundException e) {
      return new ResponseEntity<>(null, e.getHttpStatus());
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @AuthorizeUser
  @Operation(summary = "Delete a contact")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Contact deleted successfully",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = Contact.class))}),
      @ApiResponse(responseCode = "204", description = "No contact deleted",
          content = @Content)})
  @DeleteMapping("/{id}")
  public ResponseEntity<Boolean> deleteContact(
      @Parameter(description = "ID of the user who want all the contacts") @RequestParam("user_id") Long userId,
      @Parameter(description = "ID of the contact to delete") @PathVariable("id") Long id,
      @RequestHeader(value = "access_token") String accessToken) {
    try {
      contactService.deleteContact(id, userId);
      return new ResponseEntity<>(true, HttpStatus.OK);
    } catch (Exception e) {
      //log for exception
      return new ResponseEntity<>(false, HttpStatus.NO_CONTENT);
    }

  }

}
