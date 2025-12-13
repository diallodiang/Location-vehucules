package com.location.service.domaine.services;

import com.location.service.model.ContratDTO;

import java.util.List;
import java.util.Map;

public interface CamundaService {

    // Démarrer le processus pour un contrat
    String startProcess(ContratDTO contratDTO);

    // Lister les tâches assignées à un utilisateur
    List<Map<String, Object>> listTasks(String userId);

    // Afficher toutes les tâches (assignées ou pas)
    List<Map<String, Object>> listTasks();

    // Assigner une tâche à un utilisateur
    void assignTask(String taskId, String userId);

    // Compléter une tâche
    void completeTask(String taskId, Map<String, Object> variables);

    // Envoyer un message à un processus
    void sendMessage(String messageName, String businessKey, Map<String, Object> variables);

    // Dans l'interface
    Map<String, Object> getProcessInstanceByBusinessKey(String businessKey);

}