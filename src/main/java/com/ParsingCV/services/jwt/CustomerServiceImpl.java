package com.ParsingCV.services.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ParsingCV.entities.Customer;
import com.ParsingCV.repository.CustomerRepository;

import java.util.Collections;
import java.util.Set;

@Service
public class CustomerServiceImpl implements UserDetailsService {

    private final CustomerRepository customerRepository;
    @Autowired

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
//cette methode recherche un utilisateur dans la base de données par son email.
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Customer not found with email: " + email));

        Set<GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority(customer.getRole().name()));

        return new User(customer.getEmail(), customer.getPassword(), authorities);
    }
}
