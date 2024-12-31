package com.ParsingCV.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ParsingCV.entities.Customer;
import com.ParsingCV.entities.Role;
import com.ParsingCV.repository.CustomerRepository;
@Component
public class DataLoader implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataLoader(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }
//Le composant DataLoader initialise le système en créant un utilisateur admin si aucun n'existe déjà. Cet utilisateur admin est utile pour gérer le système avec des droits d'administrateur.


    @Override
    public void run(String... args) throws Exception {
        if (customerRepository.findByEmail("admin@example.com").isEmpty()) {
            // Create an admin user
            Customer admin = new Customer();
            admin.setName("Admin");
            admin.setEmail("admin@example.com");
            admin.setPassword(passwordEncoder.encode("adminpassword"));
            admin.setRole(Role.ADMIN);

            // Save admin user to database
            customerRepository.save(admin);
            System.out.println("Admin user created: " + admin.getEmail());
        }
    }
}
