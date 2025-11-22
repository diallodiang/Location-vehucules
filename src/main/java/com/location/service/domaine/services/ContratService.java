package com.location.service.domaine.services;

import java.util.List;

import com.location.service.model.ContratDTO;

public interface ContratService {
    ContratDTO createContrat(ContratDTO c);
    List<ContratDTO> listContrats();
}

