package com.odix.fr.service;

import java.util.List;
import java.util.Optional;

import com.odix.fr.model.Contact;

public interface ContactService {
	
	public List<Contact> getAllContacts(Long idUtilisateur);
	
	public Optional<Contact> getContact(Long id);
	
	public Contact addContact(Contact contact);
	
	public Contact editContact(Contact contact);
	
	public void deleteContact(Long id);

	public Contact addPhotoToContact(Long id, String urlPhoto);

}
