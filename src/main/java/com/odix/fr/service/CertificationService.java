package com.odix.fr.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.odix.fr.model.Certification;

public interface CertificationService {
	
	public List<Certification> getAllCertifications();
	
	public Optional<Certification> getCertification(UUID id);
	
	public Certification addCertification(Certification certification);
	
	public Certification editCertification(Certification certification);
	
	public boolean deleteCertification(UUID id);

}
