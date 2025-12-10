package com.location.service.domaine.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.location.service.model.ContratDTO;
import org.springframework.web.client.RestTemplate;

@Service
public class CamundaServiceImpl implements CamundaService {

    @Value("${camunda.api.url}")
    private String camundaApiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String PROCESS_DEFINITION_KEY = "ProccessDemandeLocation";


    @Override
    public String startProcess(ContratDTO contratDTO) {
        String url = camundaApiUrl + "/process-definition/key/" + PROCESS_DEFINITION_KEY + "/start";

        Map<String, Object> variables = new HashMap<>();
        variables.put("contratId", Map.of("value", contratDTO.getIdentifiantContrat(), "type", "String"));
        variables.put("clientId", Map.of("value", contratDTO.getClient().getIdentifiant(), "type", "String"));
        variables.put("vehiculeId", Map.of("value", contratDTO.getVehicule().getIdentifiantVehicule(), "type", "String"));
        variables.put("prix", Map.of("value", contratDTO.getPrix(), "type", "String"));
        variables.put("duree", Map.of("value", contratDTO.getDuree(), "type", "String"));

        Map<String, Object> request = new HashMap<>();
        request.put("variables", variables);
        request.put("businessKey", "CONTRAT_" + contratDTO.getIdentifiantContrat());

        Map response = restTemplate.postForObject(url, request, Map.class);
        return (String) response.get("id");
    }

    @Override
    public List<Map<String, Object>> listTasks(String userId) {
        String url = camundaApiUrl + "/task?assignee=" + userId;
        List<Map<String, Object>> tasks = restTemplate.getForObject(url, List.class);
        return tasks;
    }

    @Override
    public void assignTask(String taskId, String userId) {
        String url = camundaApiUrl + "/task/" + taskId + "/assignee";
        Map<String, String> request = Map.of("userId", userId);
        restTemplate.postForObject(url, request, Void.class);
    }

    @Override
    public void completeTask(String taskId, Map<String, Object> variables) {
        String url = camundaApiUrl + "/task/" + taskId + "/complete";
        Map<String, Object> request = new HashMap<>();

        if (variables != null && !variables.isEmpty()) {
            Map<String, Object> varMap = new HashMap<>();
            variables.forEach((k,v) -> varMap.put(k, Map.of("value", v, "type", "String")));
            request.put("variables", varMap);
        }

        restTemplate.postForObject(url, request, Void.class);
    }

    @Override
    public void sendMessage(String messageName, String businessKey, Map<String, Object> variables) {
        String url = camundaApiUrl + "/message";

        Map<String, Object> request = new HashMap<>();
        request.put("messageName", messageName);
        request.put("businessKey", businessKey);

        if (variables != null && !variables.isEmpty()) {
            Map<String, Object> varMap = new HashMap<>();
            variables.forEach((k,v) -> varMap.put(k, Map.of("value", v, "type", "String")));
            request.put("processVariables", varMap);
        }

        restTemplate.postForObject(url, request, Void.class);
    }




    /* @Override
    public String startProcess(ContratDTO contratDTO) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("contratId", contratDTO.getIdentifiantContrat());
        variables.put("clientId", contratDTO.getClient().getIdentifiant());
        variables.put("vehoculeId", contratDTO.getVehicule().getIdentifiantVehicule());
        variables.put("prix", contratDTO.getPrix());
        variables.put("duree", contratDTO.getDuree());

        String businessKey = "CONTRAT_" + contratDTO.getIdentifiantContrat();

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(
                PROCESS_DEFINITION_KEY,
                businessKey,
                variables
        );

        return processInstance.getId();
    }

    @Override
    public List<TaskDto> listTasks(String userId) {
        return taskService.createTaskQuery()
                .taskAssignee(userId)
                .list()
                .stream()
                .map(t -> new TaskDto(t.getId(), t.getName(), t.getAssignee()))
                .collect(Collectors.toList());
    }

    @Override
    public void assignTask(String taskId, String userId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) throw new RuntimeException("Tâche introublie pour l'id: " + taskId);
        taskService.setAssignee(taskId, userId);
    }

    @Override
    public void completeTask(String taskId, Map<String, Object> variables) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) throw new RuntimeException("Tâche introublie pour l'id: " + taskId);

        if (variables != null && !variables.isEmpty()) {
            taskService.complete(taskId, variables);
        } else {
            taskService.complete(taskId);
        }
    }

    @Override
    public void sendMessage(String messageName, String businessKey, Map<String, Object> variables) {
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
    } */
}


