package com.location.service.domaine.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.location.service.domaine.entites.ContratEntity;

public interface ContratRepository extends JpaRepository<ContratEntity, Long> {
	
	 // Trouver les contrats d’un client précis
    
    List<ContratEntity> findContratByClientIdentifiant(Long identifiant);


    // Trouver les contrats liés à un véhicule précis
    List<ContratEntity> findContratByVehiculeIdentifiantVehicule(Long VehiculeId);
	

}

