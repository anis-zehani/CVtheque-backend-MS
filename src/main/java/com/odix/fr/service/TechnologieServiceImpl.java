package com.odix.fr.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.odix.fr.messaging.TechnologieProducers;
import com.odix.fr.model.Technologie;
import com.odix.fr.repository.TechnologieRepository;

@Service
public class TechnologieServiceImpl implements TechnologieService{
	
	@Autowired
    public final TechnologieProducers technologieProducers;
	
	private final TechnologieRepository technologieRepository;

	TechnologieServiceImpl(TechnologieRepository technologieRepository, TechnologieProducers technologieProducers) {
		super();
		this.technologieRepository = technologieRepository;
		this.technologieProducers = technologieProducers;
	}
	
	public List<Technologie> getAllTechnologies() {
		
		List<Technologie> listeTechnologies = technologieRepository.findAll();

		return listeTechnologies;
	}
	
	public Technologie getTechnologie(UUID id) {
		
		return technologieRepository.getOne(id);
	}
	
	//Ajouter une technologie
	@Transactional
	public Technologie addTechnologie(Technologie technologie) 
	{
		if(technologieRepository.findByNomTechnologie(technologie.getNomTechnologie()) == null)
		{
			Technologie newTechnologie =  technologieRepository.save(technologie);
			
			//Consistency avec les autres MS
			this.technologieProducers.addTechnologieProducer(newTechnologie);
			
			return newTechnologie;
		}
		return null;
	}
	
	//Modifier une technologie
	@Transactional
	public Technologie editTechnologie(Technologie technologie) 
	{
		if(technologieRepository.existsById(technologie.getId()))
		{
			technologieRepository.save(technologie);
			
			//Consistency avec les autres MS
			this.technologieProducers.editTechnologieProducer(technologie);
			
			return technologie;
		}
		return null;
	}
	
	//Supprimer une technologie
	@Transactional
	public boolean deleteTechnologie(UUID id) 
	{
		if(technologieRepository.existsById(id))
		{
			try 
			{
			technologieRepository.deleteById(id);
			
			//Consistency avec les autres MS
			this.technologieProducers.deleteTechnologieProducer(id);

			return true;
			}
			catch(Exception e) 
			{
				System.out.print("Erreur durant deleteTechnologie :"+e);
			}
		}
		return false;
	}
	
	// Statistiques : UPDATE le nombre des Candidats liés et des Opportunités liées à une Technologie
	@Override
	public void updateNombreCandidatsAndNombreOpportunitesStats(UUID idTechnologie, Integer nombreCandidats, Integer nombreOpportunites) {
		
		technologieRepository.updateNombreCandidatsAndNombreOpportunitesStats(idTechnologie, nombreCandidats, nombreOpportunites);
	}
	
	// Retourne la liste des 5 premières technologies ORDER BY le nombre des candidats qu'il y a pour elle
	@Override
	public List<Technologie> candidatsByTechnologie(){
		
		List<Technologie> listeCandidatsByTechnologie = technologieRepository.candidatsByTechnologie();
		
		return listeCandidatsByTechnologie;
	}
	
	// Retourne la liste des 5 premières technologies ORDER BY le nombre des opportunités qu'il y a pour elle
	@Override
	public List<Technologie> opportunitesByTechnologie(){
		
		List<Technologie> listeOpportunitesByTechnologie = technologieRepository.opportunitesByTechnologie();
		
		return listeOpportunitesByTechnologie;
	}
	
	// Retourne la somme des Candidats liés et des opportunités liées pour toutes les technologies
	@Override
	public Map<String, Integer> sumCandiatsAndOpportunitesByTechnologies(){
		
		List<Integer> listeSumsCandidats = technologieRepository.sumCandiatsByTechnologies();
		List<Integer> listeSumsOpportunites = technologieRepository.sumOpportunitesByTechnologies();
		
		Map<String, Integer> map = new HashMap<>();
		
		map.put("sumCandidatsLies", listeSumsCandidats.get(0));
		map.put("sumOpportunitesLiees", listeSumsOpportunites.get(0));
		
		return map;
	}

}
