package com.contact.management.service.contactmanagementsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.contact.management.service.contactmanagementsystem.entity.Contact;
import com.contact.management.service.contactmanagementsystem.entity.User;
import com.contact.management.service.contactmanagementsystem.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @Operation(summary = "Create a user")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User created successfully",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = Contact.class))}),
      @ApiResponse(responseCode = "500", description = "Internal server error",
          content = @Content)})
  @PostMapping
  public User createUser(@RequestBody User user) {
    return userService.createUser(user);
  }

}
