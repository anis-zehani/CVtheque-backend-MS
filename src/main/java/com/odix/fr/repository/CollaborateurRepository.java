package com.odix.fr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.odix.fr.model.Collaborateur;

@Repository
public interface CollaborateurRepository extends JpaRepository<Collaborateur, Long> {
	
	Collaborateur findByIdentite(@Param("identite") String identite);
	
	Collaborateur findByUsername(@Param("username") String username);

}
