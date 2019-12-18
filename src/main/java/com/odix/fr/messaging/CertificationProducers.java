package com.odix.fr.messaging;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.odix.fr.model.Certification;

@Service
public class CertificationProducers {
	
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
    @Autowired
    public KafkaTemplate<String, String> kafkaTemplate;

    public void addCertificationProducer(Certification certification) {
        try{
			String value = OBJECT_MAPPER.writeValueAsString(certification);
			System.out.print(String.format("#### -> addCertificationProducer -> %s", value + "\n"));
			this.kafkaTemplate.send("add-certification-topic", value);
        }catch (Exception e){
            e.printStackTrace();
        }
        
    }
    
    public void editCertificationProducer(Certification certification) {
        try{
			String value = OBJECT_MAPPER.writeValueAsString(certification);
			System.out.print(String.format("#### -> editCertificationProducer -> %s", value + "\n"));
			this.kafkaTemplate.send("edit-certification-topic", value);
        }catch (Exception e){
            e.printStackTrace();
        }
        
    }
    
    public void deleteCertificationProducer(UUID idCertification) {
        try{
			
			System.out.print(String.format("#### -> deleteCertificationProducer -> %s", idCertification + "\n"));
			this.kafkaTemplate.send("delete-certification-topic", idCertification.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        
    }
}
