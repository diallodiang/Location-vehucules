package com.location.service.domaine.services;

import java.util.List;
import java.util.Optional;

import com.location.service.model.ContratDTO;

public interface ContratService {
    ContratDTO createContrat(ContratDTO contratDTO);

    List<ContratDTO> getAllContrats();

    Optional<ContratDTO> getContratById(Long id);
}

