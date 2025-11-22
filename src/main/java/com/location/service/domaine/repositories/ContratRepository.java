package com.location.service.domaine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.location.service.domaine.entites.ContratEntity;

public interface ContratRepository extends JpaRepository<ContratEntity, Long> {

}

