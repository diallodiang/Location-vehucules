package com.location.service.domaine.services;

import java.util.List;
import com.location.service.model.Contrat;

public interface ContratService {
    Contrat createContrat(Contrat c);
    List<Contrat> listContrats();
}

