package com.location.service.domaine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.location.service.domaine.entites.Client;
import com.location.service.domaine.entites.Contrat;
import com.location.service.domaine.entites.Vehicule;

import java.util.List;

public interface ContratRepository extends JpaRepository<Contrat, Long> {
    List<Contrat> findByClient(Client client);
    List<Contrat> findByVehicule(Vehicule vehicule);
}
