package com.location.service.api.v1;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/camunda")
public class CamundaController {

    @Autowired
    private CamundaService camundaService;

    /**
     * Lister les tâches assignées à un utilisateur
     */
    @GetMapping("/tasks")
    public ResponseEntity<List<Map<String, Object>>> listTasks(@RequestParam String userId) {
        List<Map<String, Object>> tasks = camundaService.listTasks(userId);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/all-tasks")
    public ResponseEntity<List<Map<String, Object>>> listTasks() {
        List<Map<String, Object>> tasks = camundaService.listTasks();
        return ResponseEntity.ok(tasks);
    }

    /**
     * Assigner une tâche à un utilisateur
     */
    @PostMapping("/task/{taskId}/assignee")
    public ResponseEntity<Void> assingTask(@PathVariable String taskId, @RequestParam String userId) {
        camundaService.assignTask(taskId, userId);
        return ResponseEntity.ok().build();
    }

    /**
     * Compléter une tâche avec des variables optionnelles
     */
    @PostMapping("/task/{taskId}/complete")
    public ResponseEntity<Void> completeTask(@PathVariable String taskId, @RequestBody(required = false) Map<String, Object> variables) {
        camundaService.completeTask(taskId, variables);

        return ResponseEntity.ok().build();
    }

    /**
     * Envoyer un message à un processus Camunda
     */
    @PostMapping("/message")
    public ResponseEntity<Void> sendMessage(@RequestParam String messageName, @RequestParam String businessKey, @RequestBody(required = false) Map<String, Object> variables
    ) {
        camundaService.sendMessage(messageName, businessKey, variables);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/process-instance")
    public ResponseEntity<Map<String, Object>> getProcessInstance(@RequestParam String businessKey) {
        Map<String, Object> instance = camundaService.getProcessInstanceByBusinessKey(businessKey);

        if (instance != null) {
            return ResponseEntity.ok(instance);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}