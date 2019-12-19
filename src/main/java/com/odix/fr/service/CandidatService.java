package com.odix.fr.service;

import java.util.UUID;

import com.odix.fr.model.Candidat;

public interface CandidatService {
	
	public Candidat addCandidat(Candidat candidat);

	public Candidat editCandidat(Candidat candidat);
	
	public void deleteCandidat(UUID id);

}
