package com.odix.fr.service;

import java.util.UUID;

import com.odix.fr.model.Collaborateur;

public interface CollaborateurService {
	
	public Collaborateur addCollaborateur(Collaborateur collaborateur);
	
	public Collaborateur editCollaborateur(Collaborateur collaborateur);
	
	public void deleteCollaborateur(UUID id);

}
