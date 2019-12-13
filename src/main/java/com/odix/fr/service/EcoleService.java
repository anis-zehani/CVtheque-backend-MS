package com.odix.fr.service;

import java.util.List;
import java.util.Optional;

import com.odix.fr.model.Ecole;

public interface EcoleService {
	
	public List<Ecole> getAllEcoles();
	
	public Optional<Ecole> getEcole(Long id);
	
	public Ecole addEcole(Ecole ecole);
	
	public Ecole editEcole(Ecole ecole);
	
	public void deleteEcole(Long id);

}
