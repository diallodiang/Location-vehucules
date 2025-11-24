package com.location.service.domaine.services;

import java.util.List;
import java.util.Map;

import okhttp3.internal.concurrent.Task;

public interface CamundaService {

    void demarrerProcessus(String processDefinitionKey, String businessKey);

    Void assignerTache(String idTache, String userId);

    void completerTache(String idTache, Map<String, Object> variables);

    List<Task> listeTaches(String userId);

    void envoyerMessage(String messageName, String businessKey, Map<String, Object> variables);
}