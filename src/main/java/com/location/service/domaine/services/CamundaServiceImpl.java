package com.location.service.domaine.services;

import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.camunda.bpm.engine.task.Task;   


public class CamundaServiceImpl implements CamundaService {
	
	@Autowired
    private TaskService taskService;
	
	@Autowired
    private RuntimeService runtimeService;

	public void demarrerProcessus(String processDefinitionKey, String businessKey) {
		// TODO Auto-generated method 

		if (processDefinitionKey == null || processDefinitionKey.isEmpty()) {
	        processDefinitionKey = "ProccessDemandeLocation";
	    }

	    runtimeService.startProcessInstanceByKey(
	            processDefinitionKey, 
	            businessKey
	    );
		
	}

	@Override
	public Void assignerTache(String idTache, String userId) {
		// TODO Auto-generated method stub
		
		Task task = (Task) taskService.createTaskQuery().taskId(idTache).singleResult();

	    if (task == null) {
	        throw new RuntimeException("Tâche introuvable pour id : " + idTache);
	    }

	    taskService.setAssignee(idTache, userId);

	    System.out.println(" Tâche " + idTache + " assignée à l'utilisateur " + userId);

	    return null;
		
	}

	@Override
	public void completerTache(String idTache, Map<String, Object> variables) {
		// TODO Auto-generated method stub
		
		Task task = (Task) taskService.createTaskQuery().taskId(idTache).singleResult();

	    if (task == null) {
	        throw new RuntimeException("Tâche introuvable pour id : " + idTache);
	    }

	    if (variables != null && !variables.isEmpty()) {
	        taskService.complete(idTache, variables);
	    } else {
	        taskService.complete(idTache);
	    }

	    System.out.println(" Tâche " + idTache + " complétée avec succès.");
	}

	
	@Override
	public List<Task> listeTaches(String userId) {
	    if (userId == null || userId.isEmpty()) {
	        throw new IllegalArgumentException("L'identifiant utilisateur ne peut pas être vide");
	    }
		return null;    
	}

	
	@Override
	public void envoyerMessage(String messageName, String businessKey, Map<String, Object> variables) {
		// TODO Auto-generated method stub
		
		 if (messageName == null || messageName.isEmpty()) {
		        throw new IllegalArgumentException("Le nom du message ne peut pas être vide");
		    }

		    if (businessKey == null || businessKey.isEmpty()) {
		        throw new IllegalArgumentException("La businessKey ne peut pas être vide");
		    }

		    try {
		        if (variables != null && !variables.isEmpty()) {
		            runtimeService.createMessageCorrelation(messageName)
		                          .processInstanceBusinessKey(businessKey)
		                          .setVariables(variables)
		                          .correlate();
		        } else {
		            runtimeService.createMessageCorrelation(messageName)
		                          .processInstanceBusinessKey(businessKey)
		                          .correlate();
		        }

		        System.out.println("✔ Message '" + messageName + "' envoyé à l'instance avec businessKey '" + businessKey + "'");

		    } catch (Exception e) {
		        throw new RuntimeException("Erreur lors de l'envoi du message : " + e.getMessage(), e);
		    }
		}
	}


