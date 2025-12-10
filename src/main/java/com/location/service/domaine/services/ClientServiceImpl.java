package com.location.service.domaine.services;

import com.location.service.domaine.entites.ClientEntity;
import com.location.service.domaine.mappers.ContratMappers;
import com.location.service.domaine.repositories.ClientRepository;
import com.location.service.model.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ContratMappers contratMappers;

    @Override
    public List<ClientDTO> getAllClients() {
        List<ClientEntity> clientEntities = clientRepository.findAll();
        return clientEntities.stream()
                .map(contratMappers::clientEntityToDto)
                .toList();
    }

    @Override
    public Optional<ClientDTO> getClientById(Long id) {
        return Optional.ofNullable(clientRepository.findById(id)
                .map(contratMappers::clientEntityToDto)
                .orElse(null));
    }

    @Override
    public ClientDTO saveClient(ClientDTO clientDTO) {
        ClientEntity clientEntity = contratMappers.clientDtoToEntity(clientDTO);
        ClientEntity saved = clientRepository.save(clientEntity);
        return contratMappers.clientEntityToDto(saved);
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
