package com.odix.fr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.odix.fr.service.UtilisateurService;

@CrossOrigin
@RestController
@RequestMapping("/api/utilisateur")
public class UtilisateurController {

	@Autowired
	UtilisateurService utilisateurService;
	
	
}
