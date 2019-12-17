package com.odix.fr.util;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.odix.fr.model.Entreprise;
import com.odix.fr.model.Rappel;
import com.odix.fr.model.Technologie;
import com.odix.fr.repository.CandidatRepository;
import com.odix.fr.repository.EntrepriseRepository;
import com.odix.fr.repository.OpportuniteRepository;
import com.odix.fr.repository.PartenaireRepository;
import com.odix.fr.repository.TechnologieRepository;
import com.odix.fr.service.EntrepriseService;
import com.odix.fr.service.RappelService;
import com.odix.fr.service.TechnologieService;

@Service
public class Crons {
	
	@Autowired
	TechnologieService technologieService;
	
	@Autowired
	EntrepriseService entrepriseService;
	
	@Autowired
	JavaMailSenderService javaMailSenderService;
	
	private final TechnologieRepository technologieRepository;
	private final OpportuniteRepository opportuniteRepository;
	private final CandidatRepository candidatRepository;
	private final EntrepriseRepository entrepriseRepository;
	private final PartenaireRepository partenaireRepository;
	private final RappelService rappelService;
	
	public Crons(TechnologieRepository technologieRepository, OpportuniteRepository opportuniteRepository,
			CandidatRepository candidatRepository, EntrepriseRepository entrepriseRepository, PartenaireRepository partenaireRepository,
			RappelService rappelService) {
		super();
		this.technologieRepository = technologieRepository;
		this.opportuniteRepository = opportuniteRepository;
		this.candidatRepository = candidatRepository;
		this.entrepriseRepository = entrepriseRepository;
		this.partenaireRepository = partenaireRepository;
		this.rappelService = rappelService;
	}
	
	// Statistiques : UPDATE le nombre des Candidats liés et des Opportunités liées à une Technologie : chaque 15 minutes
	@Scheduled(fixedRate = 900000)
	public void cronCandidatsAndOpportunitesByTechnologie() {
		
		Integer nombreCandidats = 0;
		Integer nombreOpportunites = 0;
		
		List<Technologie> listeTechnologies = technologieRepository.findAll();
		
		for(int i=0; i<listeTechnologies.size(); i++) {
			
			UUID idTechnologie = listeTechnologies.get(i).getId();
			
			nombreCandidats = candidatRepository.findAllCandidatsByTechnologie(idTechnologie).size();
			nombreOpportunites = opportuniteRepository.findAllOpportunitesByTechnologie(idTechnologie).size();
			
			technologieService.updateNombreCandidatsAndNombreOpportunitesStats(idTechnologie, nombreCandidats, nombreOpportunites);	
		}
	}
	
	// Statistiques : UPDATE le nombre des Candidats liés et des Partenaires liés à une Entreprise : chaque 15 minutes
	@Scheduled(fixedRate = 900000)
	public void cronCandidatsAndPartenairesByEntreprise() {
		
		Integer nombreCandidats = 0;
		Integer nombrePartenaires = 0;
		
		List<Entreprise> listeEntreprises = entrepriseRepository.findAll();
		
		for(int i=0; i<listeEntreprises.size(); i++) {
			
			Long idEntreprise = listeEntreprises.get(i).getIdEntreprise();
			
			nombreCandidats = candidatRepository.findAllCandidatsByEntreprise(idEntreprise).size();
			nombrePartenaires = partenaireRepository.findAllByEntreprise(listeEntreprises.get(i)).size();
			
			entrepriseService.updateNombreCandidatsAndNombrePartenairesStats(idEntreprise, nombreCandidats, nombrePartenaires);	
		}
	}
	
	//Chaque jour à 7 heure du matin
	@Scheduled(cron="0 0 7 * * *")
	public void cronSendEmailsRappels() {
		
		List<Rappel> allRappelsByTodayAndAllUsers = rappelService.getAllRappelsByTodayAndAllUsers();
		
		for(int i=0; i < allRappelsByTodayAndAllUsers.size(); i++) {
			
			Rappel rappel = allRappelsByTodayAndAllUsers.get(i);
			String emailTo = rappel.getUtilisateur().getEmail();
			
			if(emailTo != null && emailTo.length()>0) {
				String subjectEmail = "Rappel du : " + rappel.getDateEcheance();
				String dateRappel = "Date : " + rappel.getDateEcheance().toString() + "\r\n";
				String prioriteRappel = "Priorité : " + rappel.getPriorite().toString() + "\r\n";
				String detailsRappel = "Détails : " + rappel.getDetailsRappel() + "\r\n";
				
				String textEmail = dateRappel + prioriteRappel + detailsRappel;
				
				javaMailSenderService.sendSimpleMessage(emailTo, subjectEmail, textEmail);
			}
		}
	}

}
