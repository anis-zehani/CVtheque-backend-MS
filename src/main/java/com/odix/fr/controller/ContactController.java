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

import com.odix.fr.model.Contact;
import com.odix.fr.service.ContactService;
import com.odix.fr.util.LocalStorageService;

@CrossOrigin
@RestController
@RequestMapping("/api/contact")
public class ContactController {
	
	@Autowired
	LocalStorageService storageService;
	 
	List<String> files = new ArrayList<String>();
	  
	@Autowired
	private final ContactService contactService;
	
	ContactController(ContactService contactService) {
		this.contactService = contactService;
	}

	//Ajouter un Contact pour un utilisateur : (idUtilisateur existe dans l'objet Utilisateur envoyé à l'intérieur de l'objet Contact)
	@PostMapping()
	public Contact addContact(@Valid @RequestBody Contact contact) {
		return contactService.addContact(contact);
	}

	
	// Modifier un Contact pour un utilisateur : (idUtilisateur existe dans l'objet Utilisateur envoyé à l'intérieur de l'objet Contact)
	@PutMapping()
	public Contact editContact(@Valid @RequestBody Contact contact) {
		return contactService.editContact(contact);
	}
	
	@DeleteMapping("{id}")
	public void deleteContact(@PathVariable UUID id) {
		contactService.deleteContact(id);
	}

}
