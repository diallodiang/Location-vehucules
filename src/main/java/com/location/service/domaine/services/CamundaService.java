package com.location.service.domaine.services;

import com.location.service.model.ContratDTO;

import java.util.List;
import java.util.Map;

public interface CamundaService {

    // Démarrer le processus pour un contrat
    String startProcess(ContratDTO contratDTO);

    // Lister les tâches assignées à un utilisateur
    List<Map<String, Object>> listTasks(String userId);

    // Assigner une tâche à un utilisateur
    void assignTask(String taskId, String userId);

    // Compléter une tâche
    void completeTask(String taskId, Map<String, Object> variables);

    // Envoyer un message à un processus
    void sendMessage(String messageName, String businessKey, Map<String, Object> variables);



    /* static void demarrerProcessus(String processDefinitionKey, String businessKey);

    Void assignerTache(String idTache, String userId);

    void completerTache(String idTache, Map<String, Object> variables);

    List<org.camunda.bpm.engine.task.Task> listeTaches(String userId);

    void envoyerMessage(String messageName, String businessKey, Map<String, Object> variables); */
}