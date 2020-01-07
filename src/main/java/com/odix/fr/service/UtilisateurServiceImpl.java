package com.odix.fr.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.odix.fr.model.Utilisateur;
import com.odix.fr.repository.UtilisateurRepository;

@Service
public class UtilisateurServiceImpl implements UtilisateurService{
	
	private final UtilisateurRepository utilisateurRepository;

	
	public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
		super();
		this.utilisateurRepository = utilisateurRepository;
	}

	
	public Utilisateur getUtilisateurById(UUID id) {
		return utilisateurRepository.findUtilisateurById(id);
	}

	public Utilisateur getUtilisateurByUsername(String username) {
		return utilisateurRepository.findUtilisateurByUsername(username);
	}
	
	public Utilisateur getUtilisateurByEmail(String email) {
		return utilisateurRepository.findUtilisateurByEmail(email);
	}
	
	@Override
	public Utilisateur getUtilisateurByRole(String role) {
		return utilisateurRepository.findUtilisateurByDtype(role);
	}
	
	public String getUtilisateurRoleByUsername(String username) {
		return utilisateurRepository.findUtilisateurRoleByUsername(username);
	}
	
	//Ajouter un utilisateur
	public Utilisateur addUtilisateur(Utilisateur utilisateur) {
		return utilisateurRepository.save(utilisateur);
	}


	//Modifier un utilisateur
	public Utilisateur editUtilisateur(Utilisateur utilisateur) {
			
		if(utilisateurRepository.existsById(utilisateur.getId()) && 
			  utilisateur.getIdentite() != "" && 
			  utilisateur.getEmail() != "") {

			return utilisateurRepository.save(utilisateur);
		}
		return null;
	}

	//Supprimer un utilisateur
	public void deleteUtilisateur(UUID id) {
			
		if(utilisateurRepository.existsById(id))
		{
			//On supprime la ligne de la base
			utilisateurRepository.deleteById(id);
		}
	}
}
