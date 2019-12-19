package com.odix.fr.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.odix.fr.messaging.CertificationProducers;
import com.odix.fr.model.Certification;
import com.odix.fr.repository.CertificationRepository;


@Service
public class CertificationServiceImpl implements CertificationService{
	
	@Autowired
	CertificationProducers certificationProducers;
	
	private final CertificationRepository certificationRepository;

	CertificationServiceImpl(CertificationRepository certificationRepository) {
		super();
		this.certificationRepository = certificationRepository;
	}
	
	public List<Certification> getAllCertifications() {
	    return certificationRepository.findAll();
	}
	
	public Optional<Certification> getCertification(UUID id) {
		return certificationRepository.findById(id);
	}
	
	//Ajouter une certification
	@Transactional
	public Certification addCertification(Certification certification) 
	{
		if(certificationRepository.findByNomCertification(certification.getNomCertification()) == null)
		{
			Certification newCertification = certificationRepository.save(certification);
			
			//Consistency avec les autres MS
			this.certificationProducers.addCertificationProducer(newCertification);
			
			return newCertification;
		}
		return null;
	}
	
	//Modifier une certification
	@Transactional
	public Certification editCertification(Certification certification) 
	{
		if(certificationRepository.existsById(certification.getId()))
		{
			certificationRepository.save(certification);
			
			//Consistency avec les autres MS
			this.certificationProducers.editCertificationProducer(certification);
			
			return certification;
		}
		return null;
	}
	
	//Supprimer une certification
	@Transactional
	public boolean deleteCertification(UUID id) 
	{
		if(certificationRepository.existsById(id))
		{
			try 
			{
			certificationRepository.deleteById(id);
			
			//Consistency avec les autres MS
			this.certificationProducers.deleteCertificationProducer(id);
			
			return true;
			}
			catch(Exception e) 
			{
				System.out.print("Erreur durant deleteCertification :"+e);
			}
		}
		return false;
	}

}