package com.location.service.domaine.services;

import java.util.List;
import java.util.Map;

public interface CamundaService {

    /*static void demarrerProcessus(String processDefinitionKey, String businessKey) {
		// TODO Auto-generated method stub
		
	}

    Void assignerTache(String idTache, String userId);

    void completerTache(String idTache, Map<String, Object> variables);

    List<org.camunda.bpm.engine.task.Task> listeTaches(String userId);

    void envoyerMessage(String messageName, String businessKey, Map<String, Object> variables);*/
	
	String startProcess(Long clientId, Long vehiculeId);
    void assignTask(String taskId, String userId);
    void completeTask(String taskId, Map<String, Object> variables);
    List<TaskDto> listTasks(String userId);
    void sendMessage(String messageName, Map<String, Object> variables, String processInstanceId);
}