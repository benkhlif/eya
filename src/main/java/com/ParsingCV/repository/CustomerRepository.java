package com.ParsingCV.repository;

 import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ParsingCV.entities.Customer;
import com.ParsingCV.entities.Role;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByEmail(String email);
    boolean existsByRole(Role role);

    Optional<Customer> findByEmail(String email);  

    @Query("SELECT u FROM Customer u WHERE u.id <> ?1")
    List<Customer> findAllUsersExceptThisUserId(Long userId);
 }