package com.odix.fr.service;

import java.util.UUID;

// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.odix.fr.model.Collaborateur;
import com.odix.fr.repository.CollaborateurRepository;

@Service
public class CollaborateurServiceImpl implements CollaborateurService{
	
	private final CollaborateurRepository collaborateurRepository;
	
	CollaborateurServiceImpl(CollaborateurRepository collaborateurRepository) {
		super();
		this.collaborateurRepository = collaborateurRepository;
	}

	//Ajouter un collaborateur
	public Collaborateur addCollaborateur(Collaborateur collaborateur) {
			return collaborateurRepository.save(collaborateur);
	}

	//Modifier un collaborateur
	public Collaborateur editCollaborateur(Collaborateur collaborateur) {
		
		if(collaborateurRepository.existsById(collaborateur.getId()) )
		{
			return collaborateurRepository.save(collaborateur);
		}
		return null;
	}

	//Supprimer un collaborateur
	public void deleteCollaborateur(UUID id) {
		
		if(collaborateurRepository.existsById(id))
		{
			collaborateurRepository.deleteById(id);
		}
	}

}
