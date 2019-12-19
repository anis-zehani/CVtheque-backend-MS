package com.odix.fr.service;

import java.util.UUID;

import com.odix.fr.model.Contact;

public interface ContactService {
	
	public Contact addContact(Contact contact);
	
	public Contact editContact(Contact contact);
	
	public void deleteContact(UUID id);
}
