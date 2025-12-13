package com.location.service.domaine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.location.service.domaine.entites.VehiculeEntity;

import java.util.List;
import java.util.Optional;

public interface VehiculeRepository extends JpaRepository<VehiculeEntity, Long> {
	
    List<VehiculeEntity> findByMarque(String marque);
    
    VehiculeEntity findOneByIdentifiantVehicule(Long vehiculeId);

}

