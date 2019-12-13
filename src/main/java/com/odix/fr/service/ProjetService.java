package com.odix.fr.service;

import java.util.List;

import com.odix.fr.model.Projet;

public interface ProjetService {
	
	public List<Projet> getAllProjets(Long idUtilisateur);
	
	public Projet getProjet(Long id);
	
	public Projet addProjet(Projet projet);
	
	public Projet editProjet(Projet projet);
	
	public void deleteProjet(Long id);

}
