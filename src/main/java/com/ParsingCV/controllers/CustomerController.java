package com.ParsingCV.controllers;
 
 import com.ParsingCV.entities.Customer;
 import com.ParsingCV.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

  
    @Autowired
    private CustomerRepository customerRepository;

    // Récupérer tous les clients
    @GetMapping("ListeCustomers")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Récupérer un client par ID
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Créer un nouveau client
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    // Mettre à jour un client existant
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customer.setName(customerDetails.getName());
            customer.setPassword(customerDetails.getPassword());
            customer.setEmail(customerDetails.getEmail());
            customer.setRole(customerDetails.getRole());
            customer.setCondidat(customerDetails.getCondidat());
            Customer updatedCustomer = customerRepository.save(customer);
            return ResponseEntity.ok(updatedCustomer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer un client
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // Ajouter une méthode pour rechercher un client par email
    @GetMapping("/searchByEmail")
    public ResponseEntity<Customer> getCustomerByEmail(@RequestParam String email) {
        Optional<Customer> customer = customerRepository.findByEmail(email);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/idByEmail")
    public ResponseEntity<Long> getCustomerIdByEmail(@RequestParam String email) {
        Optional<Customer> customer = customerRepository.findByEmail(email);
        return customer.map(cust -> ResponseEntity.ok(cust.getId()))
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
