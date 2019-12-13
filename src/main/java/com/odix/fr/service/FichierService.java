package com.odix.fr.service;

import java.util.List;

import com.odix.fr.model.Fichier;

public interface FichierService {
	
	public List<Fichier> getAllFichiers();
	
	public void deleteFichier(String nomFichier);
}
