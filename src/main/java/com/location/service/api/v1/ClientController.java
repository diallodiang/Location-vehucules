package com.location.service.api.v1;

import com.location.service.domaine.services.ClientService;
import com.location.service.model.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    // Ajouter un client
    @PostMapping("/")
    public ClientDTO addClient(@RequestBody ClientDTO clientDTO) {
        return clientService.saveClient(clientDTO);
    }

    // Ajouter plusieurs clients
    @PostMapping("/add-list")
    public List<ClientDTO> addClients(@RequestBody List<ClientDTO> clientList) {
        List<ClientDTO> savedClients = new ArrayList<>();
        for (ClientDTO c : clientList) {
            savedClients.add(clientService.saveClient(c));
        }
        return savedClients;
    }

    // Afficher tous les clients
    @GetMapping("/")
    public List<ClientDTO> getAllClients() {
        return clientService.getAllClients();
    }

    // Afficher un client
    @GetMapping("/{id}")
    public Optional<ClientDTO> getClientById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    // Supprimer un client
    @DeleteMapping("/{id}")
    public List<ClientDTO> deleteClientById(@PathVariable Long id) {
        clientService.deleteClient(id);
        return this.getAllClients();
    }
}
