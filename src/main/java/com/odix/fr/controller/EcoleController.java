package com.odix.fr.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.odix.fr.model.Ecole;
import com.odix.fr.service.EcoleService;


@RestController
@RequestMapping("/api/ecole")
public class EcoleController {
	
	@Autowired
	private final EcoleService écoleService;
	
	EcoleController(EcoleService écoleService) {
		this.écoleService = écoleService;
	}

	@GetMapping()
	public List<Ecole> getAllEcoles() {
	    return écoleService.getAllEcoles();
	}
	
	@GetMapping("{id}")
	public Ecole getEcole(@PathVariable UUID id) {
		return écoleService.getEcole(id);
	}
	
	//Ajouter une Ecole pour un utilisateur : (idUtilisateur existe dans l'objet Utilisateur envoyé à l'intérieur de l'objet Ecole)
	@PostMapping()
	public Ecole addEcole(@Valid @RequestBody Ecole Ecole) {
		return écoleService.addEcole(Ecole);
	}
	
	//Modifier une Ecole pour un utilisateur : (idUtilisateur existe dans l'objet Utilisateur envoyé à l'intérieur de l'objet Ecole)
	@PutMapping()
	public Ecole editEcole(@Valid @RequestBody Ecole Ecole) {
		return écoleService.editEcole(Ecole);
	}
	
	@DeleteMapping("{id}")
	public void deleteEcole(@PathVariable UUID id) {
		écoleService.deleteEcole(id);
	}

}
