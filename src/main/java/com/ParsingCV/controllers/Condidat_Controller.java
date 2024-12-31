package com.ParsingCV.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
 import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

 import com.ParsingCV.entities.Condidat;
import com.ParsingCV.entities.Master;
import com.ParsingCV.repository.Condidat_Repository;
import com.ParsingCV.repository.Master_Repository;

import org.springframework.security.core.Authentication;


  @Controller
  @RequestMapping("/api")
public class Condidat_Controller {
    @Autowired
    Condidat_Repository condidat_repository;
@Autowired
Master_Repository offre_repository ;

 
    @GetMapping("/ListeCondidats")
    @ResponseBody  
    public List<Condidat> getAllCondidats() {
        return (List<Condidat>) condidat_repository.findAll();
    }
    @PostMapping("/creercondidat")
    @ResponseBody
    public ResponseEntity<?> creerCondidat(@RequestParam("file") MultipartFile file,
                                           @RequestParam("nom") String nom,
                                           @RequestParam("prenom") String prenom,
                                           @RequestParam("adresse") String adresse,
                                           @RequestParam("email") String email,
                                           @RequestParam("telephone") String telephone,
                                           @RequestParam("diplome") String diplome,
                                           @RequestParam("langues") String langues,
                                           @RequestParam("competences") String competences,
                                           @RequestParam("formations") String formations,
                                           @RequestParam("experience") String experience) throws IOException {
        // Vérifier si un candidat avec le même email existe déjà
        Optional<Condidat> candidatExistant = condidat_repository.findByEmail(email);
        
        if (candidatExistant.isPresent()) {
            // Retourner une réponse indiquant que le candidat existe déjà
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Le candidat avec cet email existe déjà.");
        }
        
        // Créer un nouveau candidat
        Condidat condidat = new Condidat();
        if (!file.isEmpty()) {
            byte[] photoBytes = file.getBytes();
            condidat.setPhoto(photoBytes);
        }
        
        condidat.setNom(nom);
        condidat.setPrenom(prenom);
        condidat.setAdresse(adresse);
        condidat.setEmail(email);
        condidat.setTelephone(telephone);
        condidat.setDiplome(diplome);
        condidat.setLangues(langues);
        condidat.setCompetences(competences);
        condidat.setFormations(formations);
        condidat.setExperience(experience);
        
        // Sauvegarder le candidat
        condidat_repository.save(condidat);
        
        // Retourner une réponse indiquant que le candidat a été créé avec succès
        return ResponseEntity.status(HttpStatus.CREATED).body(condidat);
    }

 
    @PutMapping("/{id_condidat}")
    @ResponseBody
    public ResponseEntity<Condidat> updateCondidat(@PathVariable Long id_condidat,
                                                    @RequestParam(value = "file", required = false) MultipartFile file,
                                                    @ModelAttribute Condidat condidatRequest) {
        return condidat_repository.findById(id_condidat).map(condidat -> {
            condidat.setNom(condidatRequest.getNom());
            condidat.setPrenom(condidatRequest.getPrenom());
            condidat.setAdresse(condidatRequest.getAdresse());
            condidat.setEmail(condidatRequest.getEmail());
            condidat.setTelephone(condidatRequest.getTelephone());
            condidat.setDiplome(condidatRequest.getDiplome());
            condidat.setLangues(condidatRequest.getLangues());
            condidat.setCompetences(condidatRequest.getCompetences());
            condidat.setFormations(condidatRequest.getFormations());
            condidat.setExperience(condidatRequest.getExperience());
            if (file != null && !file.isEmpty()) {
                try {
                    condidat.setPhoto(file.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Condidat updatedCondidat = condidat_repository.save(condidat);
            return ResponseEntity.ok(updatedCondidat);
        }).orElse(ResponseEntity.notFound().build());
    }
    

    @DeleteMapping("/{condidatId}")
	public ResponseEntity<?> deleteOffre(@PathVariable Long condidatId)
	{ return condidat_repository.findById(condidatId).map(condidat -> { 
		condidat_repository.delete(condidat); 
	return ResponseEntity.ok().build();
	 }).orElseThrow(() -> new IllegalArgumentException("CondidatId  " + 
			 condidatId + " not found"));
	 } 

    @GetMapping("/{condidatId}")
    public ResponseEntity<Condidat> getCondidatById(@PathVariable Long condidatId) { 
        Optional<Condidat> c = condidat_repository.findById(condidatId);
        return c.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/condidat/me")
    @ResponseBody
    public ResponseEntity<Condidat> getMonCV(Authentication authentication) {
        // Récupérer l'utilisateur connecté à partir de l'objet Authentication
        String username = authentication.getName();
        
        // Récupérer le CV de l'utilisateur connecté depuis la base de données
        Optional<Condidat> condidatOpt = condidat_repository.findByEmail(username);
        
        return condidatOpt.map(ResponseEntity::ok)
                           .orElse(ResponseEntity.notFound().build());
    }
  
}