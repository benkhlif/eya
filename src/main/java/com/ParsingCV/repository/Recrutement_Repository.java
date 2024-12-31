	package com.ParsingCV.repository;
	
	import java.util.List;
	import java.util.Optional;
	
	import org.springframework.data.jpa.repository.JpaRepository;
	import org.springframework.data.jpa.repository.Query;
	import org.springframework.data.repository.query.Param;
	import org.springframework.stereotype.Repository;
	
	import com.ParsingCV.entities.Condidat;
	import com.ParsingCV.entities.Master;
	import com.ParsingCV.entities.Recrutement;
	@Repository
	
	public interface Recrutement_Repository extends JpaRepository< Recrutement , Long>{
	    Recrutement findByCondidatAndOffre(Condidat condidat, Master offre);
	    @Query("SELECT r FROM Recrutement r WHERE r.condidat.id = :id")
	    List<Recrutement> findByCondidatId(@Param("id") Long id);

	    @Query("SELECT r FROM Recrutement r WHERE r.etatRecrutement = :etatRecrutement")
	    List<Recrutement> findByEtatRecrutement(@Param("etatRecrutement") String etatRecrutement);
	}