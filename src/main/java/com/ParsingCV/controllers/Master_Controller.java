 package com.ParsingCV.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

 import com.ParsingCV.entities.Customer;
 import com.ParsingCV.entities.Master;
 import com.ParsingCV.repository.CustomerRepository;
 import com.ParsingCV.repository.Master_Repository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
@Controller
@RequestMapping("/master") // Chemin principal
public class Master_Controller {

    @Autowired
    private Master_Repository master_repository;

  
    @Autowired
    private CustomerRepository customerRepository; // Pour sauvegarder les clients

    @GetMapping("/ListeMaster")
    @ResponseBody  
    public List<Master> getAllMasters() {
        return (List<Master>) master_repository.findAll(); 
    }

    @PostMapping("/creerMaster")
    @ResponseBody  
    public Master creerMaster(@Valid @RequestBody Master master) {
        // Sauvegarder le master
        Master savedMaster = master_repository.save(master);
        return savedMaster;
    }

    @PutMapping("/{masterId}")
    @ResponseBody  
    public Master updateMaster(@PathVariable Long masterId, @Valid @RequestBody Master masterRequest) {
        return master_repository.findById(masterId).map(master -> { 
            master.setTitre(masterRequest.getTitre());
            master.setDescription(masterRequest.getDescription());
            master.setDuree(masterRequest.getDuree());
            master.setPrerequis(masterRequest.getPrerequis());
            master.setDate_debut(masterRequest.getDate_debut());
            master.setDate_fin(masterRequest.getDate_fin());
            master.setCout(masterRequest.getCout());
            master.setLocalisation(masterRequest.getLocalisation());
            master.setResponsable(masterRequest.getResponsable());
            return master_repository.save(master);
        }).orElseThrow(() -> new IllegalArgumentException("MasterId " + masterId + " not found"));
    }

    @DeleteMapping("/{masterId}")
    @ResponseBody  
    public ResponseEntity<?> deleteMaster(@PathVariable Long masterId) {
        return master_repository.findById(masterId).map(master -> { 
            master_repository.delete(master); 
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException("MasterId " + masterId + " not found"));
    }

    @GetMapping("/{masterId}")
    public ResponseEntity<Master> getMaster(@PathVariable Long masterId) { 
        Optional<Master> master = master_repository.findById(masterId);
        return master.map(value -> ResponseEntity.ok().body(value))
                     .orElse(ResponseEntity.notFound().build());
    }
}
