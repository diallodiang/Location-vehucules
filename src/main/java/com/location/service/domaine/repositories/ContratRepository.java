package com.location.service.domaine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.location.service.domaine.entites.Contrat;

public interface ContratRepository extends JpaRepository<Contrat, Long> {

}

