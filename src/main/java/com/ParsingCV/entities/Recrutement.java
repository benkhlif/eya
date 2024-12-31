package com.ParsingCV.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Recrutement")
public class Recrutement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long recrutementId;

    @ManyToOne
    @JoinColumn(name = "offre_id")
    private Master offre;

    @ManyToOne
    @JoinColumn(name = "condidat_id")
    private Condidat condidat;

    private double score;

    private LocalDate dateCandidature;

    private String etatRecrutement;

    

    @Lob
    @NotBlank(message = "le champ ne doit pas etre vide")
    @Column(name = "commentaires", columnDefinition = "LONGTEXT")
    private String commentaires;

    private Long evaluationCandidat;

    // Getters and setters
    public Long getRecrutementId() {
        return recrutementId;
    }

    public void setRecrutementId(Long recrutementId) {
        this.recrutementId = recrutementId;
    }

    public Master getOffre() {
        return offre;
    }

    public void setOffre(Master offre) {
        this.offre = offre;
    }

    public Condidat getCondidat() {
        return condidat;
    }

    public void setCondidat(Condidat condidat) {
        this.condidat = condidat;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public LocalDate getDateCandidature() {
        return dateCandidature;
    }

    public void setDateCandidature(LocalDate dateCandidature) {
        this.dateCandidature = dateCandidature;
    }

    public String getEtatRecrutement() {
        return etatRecrutement;
    }

    public void setEtatRecrutement(String etatRecrutement) {
        this.etatRecrutement = etatRecrutement;
    }

    public String getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }

    public Long getEvaluationCandidat() {
        return evaluationCandidat;
    }

    public void setEvaluationCandidat(Long evaluationCandidat) {
        this.evaluationCandidat = evaluationCandidat;
    }
}


