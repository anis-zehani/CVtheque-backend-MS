package com.odix.fr.service;

import java.util.List;
import java.util.UUID;

import com.odix.fr.model.Ecole;

public interface EcoleService {
	
	public List<Ecole> getAllEcoles();
	
	public Ecole getEcole(UUID id);
	
	public Ecole addEcole(Ecole ecole);
	
	public Ecole editEcole(Ecole ecole);
	
	public void deleteEcole(UUID id);

}
