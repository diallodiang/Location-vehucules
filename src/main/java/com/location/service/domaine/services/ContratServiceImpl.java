package com.location.service.domaine.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.location.service.domaine.entites.ClientEntity;
import com.location.service.domaine.entites.ContratEntity;
import com.location.service.domaine.entites.VehiculeEntity;
import com.location.service.domaine.mappers.ContratMappers;
import com.location.service.domaine.repositories.ClientRepository;
import com.location.service.domaine.repositories.ContratRepository;
import com.location.service.domaine.repositories.VehiculeRepository;
import com.location.service.model.ContratDTO;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ContratServiceImpl implements ContratService {

    private final ContratRepository contratRepository;
    private final ClientRepository clientRepository;
    private final VehiculeRepository vehiculeRepository;
    private final ContratMappers contratMappers;

    public ContratServiceImpl(ContratRepository contratRepository,
                              ClientRepository clientRepository,
                              VehiculeRepository vehiculeRepository,
                              ContratMappers contratMappers) {
        this.contratRepository = contratRepository;
        this.clientRepository = clientRepository;
        this.vehiculeRepository = vehiculeRepository;
        this.contratMappers = contratMappers;
    }

    @Override
    public ContratDTO createContrat(ContratDTO dto, Long clientId, Long vehiculeId) {
        // Récupération du client
        ClientEntity client = clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Client introuvable: " + clientId));

        // Récupération du véhicule
        VehiculeEntity vehicule = vehiculeRepository.findById(vehiculeId)
                .orElseThrow(() -> new EntityNotFoundException("Vehicule introuvable: " + vehiculeId));

        // Conversion DTO → Entity
        ContratEntity contrat = contratMappers.contratDtoToEntity(dto);

        // Association client et véhicule
        contrat.setClient(client);
        contrat.setVehicule(vehicule);

        // Sauvegarde en base
        ContratEntity savedContrat = contratRepository.save(contrat);

        // Conversion Entity → DTO et retour
        return contratMappers.contratEntityToDto(savedContrat);
    }

    @Override
    public List<ContratDTO> listContrats() {
        return contratRepository.findAll()
                .stream()
                .map(contratMappers::contratEntityToDto)
                .collect(Collectors.toList());
    }
}
