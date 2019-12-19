package com.odix.fr.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.odix.fr.model.Entreprise;
import com.odix.fr.model.Partenaire;

@Repository
public interface PartenaireRepository extends JpaRepository<Partenaire, UUID> {

	List<Partenaire> findAllByEntreprise(@Param("entreprise") Entreprise entreprise);
	

	
}
