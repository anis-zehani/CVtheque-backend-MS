package com.odix.fr.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.odix.fr.model.Ecole;

@Repository
public interface EcoleRepository extends JpaRepository<Ecole, UUID> {
	
	Ecole findByNomEcole(@Param("nomEcole") String nomEcole);

}
