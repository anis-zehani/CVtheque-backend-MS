package com.odix.fr.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.odix.fr.model.Candidat;
import com.odix.fr.repository.CandidatRepository;

@Service
public class CandidatServiceImpl implements CandidatService {
	
	private final CandidatRepository candidatRepository;
	

	CandidatServiceImpl(CandidatRepository candidatRepository) 
	{
		super();
		this.candidatRepository = candidatRepository;
	}



	
	//La liste des candidats qui ont une Technologie au moins dans la liste fournie
	public List<Candidat> getAllCandidatsByListTechnologies(ArrayList<Long> listTechnologies){
		
		List<Candidat> listeCandidats = candidatRepository.findAllCandidatsByListTechnologies(listTechnologies);
		
		return listeCandidats;
	}

	
	//Supprimer le lien entre un candidat et une certification
	public void deleteLinkCandidatCertification(UUID idCandidat, Long idCertification) {
				candidatRepository.deleteLinkCandidatCertification(idCandidat, idCertification);
	}


	//Ajouter un candidat
	public Candidat addCandidat(Candidat candidat) {
		return candidatRepository.save(candidat);
	}

		
	//Modifier un candidat par L'administrateur
	public Candidat editCandidat(Candidat candidat) {
		if(candidatRepository.existsById(candidat.getId())) {
			return candidatRepository.save(candidat);
		}
		return null;
	}
	

	//Supprimer un candidat
	public void deleteCandidat(UUID id) {
		
		if(candidatRepository.existsById(id))
		{
			try
			{
				candidatRepository.deleteById(id);
			}
			catch(Exception e) 
			{
				System.out.print("Erreur durant deleteCandidat :"+e);
			}
			
				
		}
	}
}