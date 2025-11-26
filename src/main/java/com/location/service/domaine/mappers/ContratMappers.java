package com.location.service.domaine.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.location.service.domaine.entites.ClientEntity;
import com.location.service.domaine.entites.ContratEntity;
import com.location.service.domaine.entites.VehiculeEntity;
import com.location.service.model.ClientDTO;
import com.location.service.model.ContratDTO;
import com.location.service.model.VehiculeDTO;

@Mapper(componentModel ="spring")
public interface ContratMappers {
	
	// ---------------- CONTRAT ----------------
    @Mapping(target = "id", source = "identifiantContrat")
    ContratEntity contratDtoToEntity(ContratDTO dto);

    @InheritInverseConfiguration
    ContratDTO contratEntityToDto(ContratEntity entity);


    // ---------------- CLIENT ----------------
    ClientEntity clientDtoToEntity(ClientDTO dto);

    ClientDTO clientEntityToDto(ClientEntity entity);


    // ---------------- VEHICULE ----------------
    VehiculeEntity vehiculeDtoToEntity(VehiculeDTO dto);

    VehiculeDTO vehiculeEntityToDto(VehiculeEntity entity);
}

