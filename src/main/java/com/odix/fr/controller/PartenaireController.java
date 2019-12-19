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

import com.odix.fr.model.Partenaire;
import com.odix.fr.service.PartenaireService;
import com.odix.fr.util.LocalStorageService;

@CrossOrigin
@RestController
@RequestMapping("/api/partenaire")
public class PartenaireController {
	
	@Autowired
	LocalStorageService storageService;
	 
	List<String> files = new ArrayList<String>();
	  
	@Autowired
	private final PartenaireService partenaireService;
	
	PartenaireController(PartenaireService partenaireService) {
		this.partenaireService = partenaireService;
	}

	@PostMapping()
	public Partenaire addPartenaire(@Valid @RequestBody Partenaire partenaire) {
		return partenaireService.addPartenaire(partenaire);
	}

	@PutMapping()
	public Partenaire editPartenaire(@Valid @RequestBody Partenaire partenaire) {
		return partenaireService.editPartenaire(partenaire);
	}

	@DeleteMapping("{id}")
	public void deletePartenaire(@PathVariable UUID id) {
		partenaireService.deletePartenaire(id);
	}

}