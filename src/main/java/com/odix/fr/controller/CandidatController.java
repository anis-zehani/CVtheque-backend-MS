package com.odix.fr.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.odix.fr.model.Candidat;
import com.odix.fr.service.CandidatService;
import com.odix.fr.util.LocalStorageService;

@CrossOrigin
@RestController
@RequestMapping("/api/candidat")
public class CandidatController {
	
	@Autowired
	LocalStorageService storageService;
	
	@Autowired
	private final CandidatService candidatService;
	 
	List<String> files = new ArrayList<String>();
	  
	CandidatController(CandidatService candidatService) {
		this.candidatService = candidatService;
	}

	//Ajouter un candidat
	@PostMapping()
	public Candidat addCandidat(@Valid @RequestBody Candidat candidat) {
		return candidatService.addCandidat(candidat);
	}

	@PutMapping()
	public Candidat editCandidat(@Valid @RequestBody Candidat candidat) {
		return candidatService.editCandidat(candidat);
	}

	@DeleteMapping("/{id}")
	public void deleteCandidat(@PathVariable UUID id) {
			   candidatService.deleteCandidat(id);
	}
}