package com.ParsingCV.services;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ParsingCV.dto.SignupRequest;
import com.ParsingCV.entities.Customer;
import com.ParsingCV.entities.Role;
import com.ParsingCV.repository.CustomerRepository;

@Service
public class AuthServiceImpl implements AuthService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    // Méthode pour créer un nouvel utilisateur (Customer) à partir d'une requête d'inscription (SignupRequest)
    public Customer createCustomer(SignupRequest signupRequest) {
        if (customerRepository.existsByEmail(signupRequest.getEmail())) {
            return null;
        }

        Customer customer = new Customer();
        BeanUtils.copyProperties(signupRequest, customer);
        String hashPassword = passwordEncoder.encode(signupRequest.getPassword());
        customer.setPassword(hashPassword);

        // Assign default role
        customer.setRole(Role.USER);

        return customerRepository.save(customer);
    }
}

