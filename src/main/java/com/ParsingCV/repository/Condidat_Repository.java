package com.ParsingCV.repository;

 import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ParsingCV.entities.Condidat;
 
@Repository
public interface Condidat_Repository extends JpaRepository<Condidat, Long>{
    Optional<Condidat> findByEmail(String email);

}