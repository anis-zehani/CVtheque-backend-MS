package com.odix.fr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.odix.fr.service.AdministrateurService;

@SpringBootApplication
public class BackendApplication {
	
	@Autowired
	private final AdministrateurService administrateurService;
	
	
	public BackendApplication(AdministrateurService administrateurService) {
		super();
		this.administrateurService = administrateurService;
		//Si SuperAdmin n'existe pas, je le met en place
		this.administrateurService.verifyOrAddAdmin();

	}

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
