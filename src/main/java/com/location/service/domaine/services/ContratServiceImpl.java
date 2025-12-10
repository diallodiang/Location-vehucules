package com.location.service.domaine.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ContratRepository contratRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private VehiculeRepository vehiculeRepository;

    @Autowired
    private ContratMappers contratMappers;

    @Autowired
    private CamundaService camundaService;


    @Override
    public ContratDTO createContrat(ContratDTO contratDTO) {
        ClientEntity clientEntity = clientRepository.findById(contratDTO.getClient().getIdentifiant())
                .orElseThrow(() -> new RuntimeException("Client introuvable"));

        VehiculeEntity vehiculeEntity = vehiculeRepository.findById(contratDTO.getVehicule().getIdentifiantVehicule())
                .orElseThrow(() -> new RuntimeException("Véhicule introuvable"));

        ContratEntity contratEntity = new ContratEntity();
        contratEntity.setClient(clientEntity);
        contratEntity.setVehicule(vehiculeEntity);
        contratEntity.setPrix(contratDTO.getPrix());
        contratEntity.setDuree(contratDTO.getDuree());

        // Calcul automatique des dates
        LocalDate dateDebut = LocalDate.now();
        LocalDate dateFinEstimee = dateDebut.plusDays(contratDTO.getDuree());
        contratEntity.setDateDebut(dateDebut);
        contratEntity.setDateFinEstimee(dateFinEstimee);

        // Enregistrer dans la bdd
        ContratEntity saved = contratRepository.save(contratEntity);

        // Démarrage du processus Camunda après la création
        ContratDTO contratDtoConversion = contratMappers.contratEntityToDto(saved);
        camundaService.startProcess(contratDtoConversion);

        return contratDtoConversion;
    }

    @Override
    public List<ContratDTO> getAllContrats() {
        return contratRepository.findAll().stream()
                .map(contratMappers::contratEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ContratDTO> getContratById(Long id) {
        return Optional.ofNullable(contratRepository.findById(id)
                .map(contratMappers::contratEntityToDto)
                .orElse(null));
    }
}
