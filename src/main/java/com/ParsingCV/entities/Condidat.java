package com.ParsingCV.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "condidat")
public class Condidat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_condidat;

    @NotBlank(message = "le champ ne doit pas etre vide")
    @Column(name = "nom")
    private String nom;
    
    @NotBlank(message = "le champ ne doit pas etre vide")
    @Column(name = "prenom")
    private String prenom;
    
    @NotBlank(message = "le champ ne doit pas etre vide")
    @Column(name = "adresse")
    private String adresse;
    
    @NotBlank(message = "le champ ne doit pas etre vide")
    @Column(name = "email")
    @Email(message = "L'email doit être valide")
    private String email;
    
    @NotBlank(message = "le champ ne doit pas etre vide")
    @Column(name = "telephone")
    @Pattern(regexp = "\\d+", message = "Le téléphone ne doit contenir que des chiffres")
    @Size(min = 8, max = 10, message = "Le téléphone doit contenir 10 chiffres")
    private String telephone;
    
    @Lob
    @NotBlank(message = "le champ ne doit pas etre vide")
    @Column(name = "diplome", columnDefinition = "LONGTEXT")
    private String diplome;
    @NotBlank(message = "le champ ne doit pas etre vide")
    @Lob
    @Column(name = "langues", columnDefinition = "LONGTEXT")
    private String langues;  
    @NotBlank(message = "le champ ne doit pas etre vide")
    @Lob
    @Column(name = "competences", columnDefinition = "LONGTEXT")
    private String competences; 
    @NotBlank(message = "le champ ne doit pas etre vide")
    @Lob
    @Column(name = "formations", columnDefinition = "LONGTEXT")
    private String formations;  
    @NotBlank(message = "le champ ne doit pas etre vide")
    @Lob
    @Column(name = "experience", columnDefinition = "LONGTEXT")
    private String experience;  
    
    @Lob
    @Column(name = "image", columnDefinition="LONGBLOB")
    private byte[] photo;
    @OneToOne(mappedBy = "condidat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Customer customer;

    public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Condidat() {
        super();
    }

    public Condidat(Long id_condidat, String nom, String prenom, String adresse, String email, String telephone,
            String diplome, String langues, String competences, String formations, String experience) {
        super();
        this.id_condidat = id_condidat;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.telephone = telephone;
        this.diplome = diplome;
        this.langues = langues;
        this.competences = competences;
        this.formations = formations;
        this.experience = experience;
    }

    public Long getId_condidat() {
        return id_condidat;
    }

    public void setId_condidat(Long id_condidat) {
        this.id_condidat = id_condidat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public String getLangues() {
        return langues;
    }

    public void setLangues(String langues) {
        this.langues = langues;
    }

    public String getCompetences() {
        return competences;
    }

    public void setCompetences(String competences) {
        this.competences = competences;
    }

    public String getFormations() {
        return formations;
    }

    public void setFormations(String formations) {
        this.formations = formations;
    }

    public String getExperience() {
        return experience;
    }

    public Condidat(Long id_condidat, @NotBlank(message = "le champ ne doit pas etre vide") String nom,
			@NotBlank(message = "le champ ne doit pas etre vide") String prenom,
			@NotBlank(message = "le champ ne doit pas etre vide") String adresse,
			@NotBlank(message = "le champ ne doit pas etre vide") @Email(message = "L'email doit être valide") String email,
			@NotBlank(message = "le champ ne doit pas etre vide") @Pattern(regexp = "\\d+", message = "Le téléphone ne doit contenir que des chiffres") @Size(min = 8, max = 10, message = "Le téléphone doit contenir 10 chiffres") String telephone,
			@NotBlank(message = "le champ ne doit pas etre vide") String diplome,
			@NotBlank(message = "le champ ne doit pas etre vide") String langues,
			@NotBlank(message = "le champ ne doit pas etre vide") String competences,
			@NotBlank(message = "le champ ne doit pas etre vide") String formations,
			@NotBlank(message = "le champ ne doit pas etre vide") String experience, byte[] photo) {
		super();
		this.id_condidat = id_condidat;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.email = email;
		this.telephone = telephone;
		this.diplome = diplome;
		this.langues = langues;
		this.competences = competences;
		this.formations = formations;
		this.experience = experience;
		this.photo = photo;
	}

	public void setExperience(String experience) {
        this.experience = experience;
    }

    @OneToMany(mappedBy = "condidat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Recrutement> recrutementes;

}