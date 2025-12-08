package com.location.service.domaine.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.location.service.domaine.mappers.ContratMappers;
import com.location.service.domaine.repositories.*;
import com.location.service.model.ContratDTO;   

@Service
public class CamundaServiceImpl implements CamundaService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private VehiculeRepository vehiculeRepository;
	
	@Autowired
	private ContratMappers contratMappers;
	
	private final RuntimeService runtimeService;
    private final TaskService taskService;
    private final ContratService contratService;

    private static final String PROCESS_DEFINITION_KEY = "ProccessDemandeLocation";
    
    public CamundaServiceImpl(RuntimeService runtimeService, TaskService taskService, ContratService contratService) {
        this.runtimeService = runtimeService;
        this.taskService = taskService;
        this.contratService = contratService;
    }
	
    // Démarrer le process avec businessKey
    @Override
    public String startProcess(Long clientId, Long vehiculeId) {
        Map<String, Object> variables = new HashMap <>();
        variables.put("clientId", clientId);
        variables.put("vehiculeId", vehiculeId);

        String businessKey = "CONTRAT_" + clientId + "_" + vehiculeId;

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(
                PROCESS_DEFINITION_KEY,
                businessKey,
                variables
        );

        return processInstance.getId();
    }
    
    // Assigner la tâche à un utilisateur
    @Override
    public void assignTask(String taskId, String userId) {
        taskService.setAssignee(taskId, userId);
    }
    
    // Compléter la tâche
    @Override
    public void completeTask(String taskId, Map<String, Object> variables) {
        taskService.complete(taskId, variables);

        // vérifier si le processus est terminé
        String businessKey = "CONTRAT_" + variables.get("clientId") + "_" + variables.get("vehiculeId");
        boolean processEnded = runtimeService.createProcessInstanceQuery()
                .processInstanceBusinessKey(businessKey)
                .singleResult() == null;

        if (processEnded) {
            Long clientId = Long.valueOf(variables.get("clientId").toString());
            Long vehiculeId = Long.valueOf(variables.get("vehiculeId").toString());
            Integer duree = Integer.valueOf(variables.get("duree").toString());
            Float prix = Float.valueOf(variables.get("prix").toString());

            ContratDTO contratDto = new ContratDTO();
            contratDto.setClient(contratMappers.clientEntityToDto(
                    clientRepository.findById(clientId)
                            .orElseThrow(() -> new RuntimeException("Client introuvable"))));
            contratDto.setVehicule(contratMappers.vehiculeEntityToDto(
                    vehiculeRepository.findById(vehiculeId)
                            .orElseThrow(() -> new RuntimeException("Vehicule introuvable"))));
            contratDto.setDuree(duree);
            contratDto.setPrix(prix);

            contratService.createContrat(contratDto, clientId, vehiculeId);
        }
    }
    
    // Lister les tâches assignées à un utilisateur
    @Override
    public List<TaskDto> listTasks(String userId) {
        return taskService.createTaskQuery()
                .taskAssignee(userId)
                .list()
                .stream()
                .map(t -> new TaskDto(t.getId(), t.getName(), t.getAssignee()))
                .collect(Collectors.toList());
    }
    
    
    // Envoyer un message (intermediate catch event)
    @Override
    public void sendMessage(String messageName, Map<String, Object> variables, String processInstanceId) {
        runtimeService.createMessageCorrelation(messageName)
                .processInstanceId(processInstanceId)
                .setVariables(variables)
                .correlate();
    }
    
	/* @Autowired
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
		} */
	
	
}


