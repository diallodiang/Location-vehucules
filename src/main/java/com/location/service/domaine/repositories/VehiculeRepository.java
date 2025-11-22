package com.location.service.domaine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.location.service.domaine.entites.VehiculeEntity;

import java.util.List;

public interface VehiculeRepository extends JpaRepository<VehiculeEntity, Long> {
    List<VehiculeEntity> findByMarque(String marque);
}

