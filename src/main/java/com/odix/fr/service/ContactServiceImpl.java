package com.odix.fr.service;

import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.odix.fr.model.Contact;
import com.odix.fr.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService{
	
	private final ContactRepository contactRepository;

	
	ContactServiceImpl(ContactRepository contactRepository) {
		super();
		this.contactRepository = contactRepository;
	}


	//Ajouter un contact
	public Contact addContact(Contact contact) {
			return contactRepository.save(contact);
	}


	//Modifier un contact
	public Contact editContact(Contact contact) {
		if(contactRepository.existsById(contact.getId())) {
			return contactRepository.save(contact);
		}
		return null;
	}

	//Supprimer un contact
	public void deleteContact(UUID id) {
		
		if(contactRepository.existsById(id))
		{
			try
			{
				contactRepository.deleteById(id);
			}
			catch(NoSuchElementException e) 
			{
				System.out.print("Erreur durant deleteCandidat :"+e);
			}
		}
	}

}
