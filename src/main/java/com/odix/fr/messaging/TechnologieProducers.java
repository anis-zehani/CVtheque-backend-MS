package com.odix.fr.messaging;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.odix.fr.model.Technologie;

@Service
public class TechnologieProducers {
	
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
    @Autowired
    public KafkaTemplate<String, String> kafkaTemplate;

    public void addTechnologieProducer(Technologie technologie) {
        try{
			String value = OBJECT_MAPPER.writeValueAsString(technologie);
			System.out.print(String.format("#### -> addTechnologieProducer : BackEnd-MS -> %s", value + "\n"));
			this.kafkaTemplate.send("add-technologie-topic", value);
        }catch (Exception e){
            e.printStackTrace();
        }
        
    }
    
    public void editTechnologieProducer(Technologie technologie) {
        try{
			String value = OBJECT_MAPPER.writeValueAsString(technologie);
			System.out.print(String.format("#### -> editTechnologieProducer : BackEnd-MS -> %s", value + "\n"));
			this.kafkaTemplate.send("edit-technologie-topic", value);
        }catch (Exception e){
            e.printStackTrace();
        }
        
    }
    
    public void deleteTechnologieProducer(UUID idTechnologie) {
        try{
			
			System.out.print(String.format("#### -> deleteTechnologieProducer : BackEnd-MS -> %s", idTechnologie + "\n"));
			this.kafkaTemplate.send("delete-technologie-topic", idTechnologie.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        
    }
}
