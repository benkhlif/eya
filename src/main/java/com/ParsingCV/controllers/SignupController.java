package com.ParsingCV.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ParsingCV.dto.SignupRequest;
import com.ParsingCV.entities.Customer;
import com.ParsingCV.entities.Role;
import com.ParsingCV.services.AuthService;

@RestController
@RequestMapping("/signup")
public class SignupController {
    private final AuthService authService;

    public SignupController(AuthService authService) {
        this.authService = authService;
    }
    // Lorsqu'un utilisateur s'inscrit, les informations sont reçues dans un objet SignupRequest.
    @PostMapping
    public ResponseEntity<?> signupCustomer(@RequestBody SignupRequest signupRequest) {
        Customer createdCustomer = authService.createCustomer(signupRequest);
        if (createdCustomer != null) {
            // Par défaut, l'utilisateur se voit attribuer le rôle USER.
            createdCustomer.setRole(Role.USER);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create customer");
        }
    }
}
