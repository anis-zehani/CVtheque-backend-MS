package com.odix.fr.service;

import java.util.UUID;

import com.odix.fr.model.Utilisateur;

public interface UtilisateurService {
	
	public Utilisateur getUtilisateurById(UUID id);
	
	public Utilisateur getUtilisateurByUsername(String username);
	
	public Utilisateur getUtilisateurByEmail(String email);
	
	public Utilisateur getUtilisateurByRole(String role);
	
	public String getUtilisateurRoleByUsername(String username);
	
	public Utilisateur addUtilisateur(Utilisateur utilisateur);
	
	public Utilisateur editUtilisateur(Utilisateur utilisateur);
	
	public void deleteUtilisateur(UUID id);
	
}
