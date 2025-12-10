package com.location.service.api.v1;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.location.service.domaine.services.CamundaService;
import com.location.service.domaine.services.TaskDto;

public class CamundaController {

    /* private final CamundaService camundaService;

    public CamundaController(CamundaService camundaService) {
        this.camundaService = camundaService;
    }

    // Démarrer le process
    @PostMapping("/start")
    public ResponseEntity<String> startProcess(@RequestParam Long clientId,
                                               @RequestParam Long vehiculeId) {
        String processId = camundaService.startProcess(clientId, vehiculeId);
        return ResponseEntity.ok("Processus démarré avec ID: " + processId);
    }

    // Assigner la tâche
    @PostMapping("/task/assign")
    public ResponseEntity<String> assignTask(@RequestParam String taskId,
                                             @RequestParam String userId) {
        camundaService.assignTask(taskId, userId);
        return ResponseEntity.ok("Tâche " + taskId + " assignée à " + userId);
    }

    // Compléter la tâche
    @PostMapping("/task/complete")
    public ResponseEntity<String> completeTask(@RequestParam String taskId,
                                               @RequestBody Map<String, Object> variables) {
        camundaService.completeTask(taskId, variables);
        return ResponseEntity.ok("Tâche " + taskId + " complétée");
    }

    // Lister les tâches d'un utilisateur
    @GetMapping("/tasks/{userId}")
    public List<TaskDto> listTasks(@PathVariable String userId) {
        return camundaService.listTasks(userId);
    }

    // Envoyer un message
    @PostMapping("/message")
    public ResponseEntity<String> sendMessage(@RequestParam String messageName,
                                              @RequestParam String processInstanceId,
                                              @RequestBody Map<String, Object> variables) {
        camundaService.sendMessage(messageName, variables, processInstanceId);
        return ResponseEntity.ok("Message '" + messageName + "' envoyé au process " + processInstanceId);
    } */
}