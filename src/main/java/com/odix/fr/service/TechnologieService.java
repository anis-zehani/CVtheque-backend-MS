package com.odix.fr.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.odix.fr.model.Technologie;

public interface TechnologieService {
	
	public List<Technologie> getAllTechnologies();
	
	public Technologie getTechnologie(UUID id);
	
	public Long getCountTechnologies();
	
	public Technologie addTechnologie(Technologie technologie);
	
	public Technologie editTechnologie(Technologie technologie);
	
	public boolean deleteTechnologie(UUID id);
	
	public void updateNombreCandidatsAndNombreOpportunitesStats(UUID idTechnologie, Integer nombreCandidats, Integer nombreOpportunites);

	public List<Technologie> candidatsByTechnologie();
	
	public List<Technologie> opportunitesByTechnologie();
	
	public Map<String, Integer> sumCandiatsAndOpportunitesByTechnologies();
}
