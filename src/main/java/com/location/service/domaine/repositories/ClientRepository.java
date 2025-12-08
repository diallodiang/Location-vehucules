package com.location.service.domaine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.location.service.domaine.entites.ClientEntity;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
	
    List<ClientEntity> findByNom(String nom);
    
    Optional<ClientEntity> findByIdentifiant(int identifiant);
    
    ClientEntity findByIdentifiant(Long identifiant);

    
}
