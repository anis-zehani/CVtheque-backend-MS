package com.odix.fr.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.odix.fr.model.Candidat;
import com.odix.fr.model.Etat;
import com.odix.fr.repository.CandidatRepository;

@Service
public class CandidatServiceImpl implements CandidatService {
	
	private final CandidatRepository candidatRepository;
	
	@Autowired
	UtilisateurService utilisateurService;
	
	CandidatServiceImpl(CandidatRepository candidatRepository) 
	{
		super();
		this.candidatRepository = candidatRepository;
	}


	public List<Candidat> getAllCandidats(String etatCandidat) {
		
		//On filtre selon l'état : Actif / Inactif
		if(etatCandidat.equals("True"))
		{
			return candidatRepository.findByEtatCandidat(Etat.True);
		}
		else 
		{
			return candidatRepository.findByEtatCandidat(Etat.False);
		}

	}
	
	//INNER JOIN : retourne les candidats par Technologie
	public List<Candidat> getAllCandidatsByTechnologie(UUID idTechnologie){
			
		return candidatRepository.findAllCandidatsByTechnologie(idTechnologie);
	}
	
	//La liste des candidats qui ont une Technologie au moins dans la liste fournie
	public List<Candidat> getAllCandidatsByListTechnologies(ArrayList<UUID> listTechnologies){
		
		List<Candidat> listeCandidats = candidatRepository.findAllCandidatsByListTechnologies(listTechnologies);
		
		return listeCandidats;
	}
		
	//INNER JOIN : retourne les candidats par Certification
	public List<Candidat> getAllCandidatsByCertification(UUID idCertification){
			
		return candidatRepository.findAllCandidatsByCertification(idCertification);
	}
	
	//retourne les candidats par Entreprise
	public List<Candidat> getAllCandidatsByEntreprise(UUID idEntreprise){
			
		return candidatRepository.findAllCandidatsByEntreprise(idEntreprise);
	}
	
	//Supprimer le lien entre un candidat et une technologie
	public void deleteLinkCandidatTechnologie(UUID idCandidat, UUID idTechnologie) {
			 	candidatRepository.deleteLinkCandidatTechnologie(idCandidat, idTechnologie);
	}
	
	//Supprimer le lien entre un candidat et une certification
	public void deleteLinkCandidatCertification(UUID idCandidat, UUID idCertification) {
				candidatRepository.deleteLinkCandidatCertification(idCandidat, idCertification);
	}

	public Candidat getCandidat(UUID id) {
		return candidatRepository.getOne(id);
	}
	
	//Cherche le candidat via son idLinkedin
	public Candidat getCandidatByIdLinkedin(String idLinkedin) {
		return candidatRepository.findByIdLinkedin(idLinkedin);
	}

	//Ajouter un candidat
	public Candidat addCandidat(Candidat candidat) {
		
		if(candidatRepository.findByIdentite(candidat.getIdentite()) == null &&
		   candidatRepository.findByUsername(candidat.getUsername()) == null &&
		   candidatRepository.findByEmail(candidat.getEmail()) == null) {
		//Par défaut, le candidat est activé
		candidat.setEtatCandidat(Etat.True);
		

		//Entreprise : Si le user n'a pas ajouté une Entreprise
		if(candidat.getEntreprise().getIdEntreprise() == null) {
			//Obligatoire pour @ManyToOne
			candidat.setEntreprise(null);
		}
		
		candidat.setDateAjout(LocalDateTime.now());
		
		//Encoder le Password avant de l'insérer dans la base
		candidat.setPassword(candidat.getPassword());
		
		Candidat savedCandidat = candidatRepository.save(candidat);
		
		return  savedCandidat;
		}
	return null;
	}

	//Modifier un candidat par L'administrateur
	public Candidat editCandidat(Candidat candidat) {
		
		//L'Update url photo se fait en haut dans la fonction addPhotoToCandidat
		if(candidatRepository.existsById(candidat.getId()) && 
				candidat.getIdentite() != "" &&
				candidat.getUsername() != "" &&
				candidat.getEmail() != "") {
			
			Candidat candidatToUpdate = candidatRepository.getOne(candidat.getId());
			
			candidatToUpdate.setIdentite(candidat.getIdentite());
			candidatToUpdate.setTelephone(candidat.getTelephone());
			candidatToUpdate.setEmail(candidat.getEmail());
			candidatToUpdate.setPosteOccupe(candidat.getPosteOccupe());
			candidatToUpdate.setDescriptionDetaillee(candidat.getDescriptionDetaillee());
			
			/*
			 * entreprise
			 */
			if(candidat.getEntreprise().getIdEntreprise() != null)
			{
				candidatToUpdate.setEntreprise(candidat.getEntreprise());
			}
			
			/*
			 * listeTechnologies : @ManyToMany
			 */
			if(candidat.getListeTechnologies() != null)
			{
				candidatToUpdate.setListeTechnologies(candidat.getListeTechnologies());
			}
			
			
			/*
			 * listeCertifications : @ManyToMany
			 */
			if(candidat.getListeCertifications() != null)
			{
				candidatToUpdate.setListeCertifications(candidat.getListeCertifications());
			}
			
			//Récupérer le password affiché sur le formulaire
			String passwordFormulaire = candidat.getPassword();
			//Récupérer le password actuel dans la BDD
			String passwordBDD = candidatRepository.findByUsername(candidat.getUsername()).getPassword();
			
			// Si le Password Affiché est différent de celui qui est stocké : on change le password
			if(!passwordFormulaire.equals(passwordBDD))
			{
				candidatToUpdate.setPassword(candidat.getPassword());
			}
			// Sinon on réinsére l'ancien password
			else
			{
				candidatToUpdate.setPassword(passwordBDD);
			}

			return candidatRepository.save(candidatToUpdate);
		}
		return null;
	}
	
	//Modifier l'état d'un Candidat : Actif/Inactif
	public Candidat editEtatCandidat(Candidat candidat) {
		
		if(candidatRepository.existsById(candidat.getId()))
		{
			Candidat candidatToUpdate = candidatRepository.getOne(candidat.getId());

			if(candidatToUpdate.getEtatCandidat().equals(Etat.True))
			{
				candidatToUpdate.setEtatCandidat(Etat.False);
			}
			else 
			{
				candidatToUpdate.setEtatCandidat(Etat.True);
			}
			
			return candidatRepository.save(candidatToUpdate);
		}
			
			return null;
	}
	
	//Update le lien entre un candidat et une entreprise : met entreprise à NULL
	public void updateLinkCandidatEntreprise(UUID idCandidat) {
		
		if(candidatRepository.existsById(idCandidat))
		{
			candidatRepository.updateLinkCandidatEntreprise(idCandidat);
		}
		
	}
	
	//Supprimer un candidat
	public void deleteCandidat(UUID id) {
		
		if(candidatRepository.existsById(id))
		{
			candidatRepository.deleteById(id);
		}
	}
}