package com.location.service.domaine.services;

import com.location.service.model.ClientDTO;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<ClientDTO> getAllClients();
    Optional<ClientDTO> getClientById(Long id);
    ClientDTO saveClient(ClientDTO client);
    void deleteClient(Long id);
}
