package com.ParsingCV.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ParsingCV.entities.Master;
 

@Repository
public interface Master_Repository extends JpaRepository<Master, Long>{
 
}
