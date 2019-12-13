package com.odix.fr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.odix.fr.model.Certification;

@Repository
public interface CertificationRepository extends JpaRepository<Certification, Long> {
	
	Certification findByNomCertification(@Param("nomCertification") String nomCertification);

}
