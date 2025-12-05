package com.location.service.domaine.services;

import java.util.List;
import java.util.Map;

public interface CamundaService {

    static void demarrerProcessus(String processDefinitionKey, String businessKey) {
		// TODO Auto-generated method stub
		
	}

    Void assignerTache(String idTache, String userId);

    void completerTache(String idTache, Map<String, Object> variables);

    List<org.camunda.bpm.engine.task.Task> listeTaches(String userId);

    void envoyerMessage(String messageName, String businessKey, Map<String, Object> variables);
}