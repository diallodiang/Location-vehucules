package com.location.service.domaine.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.location.service.domaine.entites.ClientEntity;
import com.location.service.domaine.entites.ContratEntity;
import com.location.service.domaine.entites.VehiculeEntity;
import com.location.service.model.ClientDTO;
import com.location.service.model.ContratDTO;
import com.location.service.model.VehiculeDTO;

// @Mapper(componentModel = "spring")
@Component 
public class ContratMappers {
	
    // @Mapping(target = "id", source = "identifiantContrat")
    // ContratEntity contratDtoToEntity(ContratDTO dto);
	 public ContratEntity contratDtoToEntity(ContratDTO dto) {
	        if (dto == null) return null;

	        ContratEntity entity = new ContratEntity();
	        entity.setId(dto.getIdentifiantContrat());

	        // Mapping client
	        if (dto.getClient() != null) {
	            entity.setClient(clientDtoToEntity(dto.getClient()));
	        }

	        // Mapping véhicule
	        if (dto.getVehicule() != null) {
	            entity.setVehicule(vehiculeDtoToEntity(dto.getVehicule()));
	        }

	        entity.setPrix(dto.getPrix());
	        entity.setDuree(dto.getDuree());

	        return entity;
	    }

    // @InheritInverseConfiguration
    // ContratDTO contratEntityToDto(ContratEntity entity);
	 public ContratDTO contratEntityToDto(ContratEntity entity) {
	        if (entity == null) return null;

	        ContratDTO dto = new ContratDTO();
	        dto.setIdentifiantContrat(entity.getId());

	        if (entity.getClient() != null) {
	            dto.setClient(clientEntityToDto(entity.getClient()));
	        }

	        if (entity.getVehicule() != null) {
	            dto.setVehicule(vehiculeEntityToDto(entity.getVehicule()));
	        }

	        dto.setPrix(entity.getPrix());
	        dto.setDuree(entity.getDuree());

	        return dto;
	    }



    // ClientEntity clientDtoToEntity(ClientDTO dto);
	 public ClientEntity clientDtoToEntity(ClientDTO dto) {
	        if (dto == null) return null;

	        ClientEntity entity = new ClientEntity();
	        entity.setIdentifiant(dto.getIdentifiant());
	        entity.setNom(dto.getNom());
	        entity.setPrenom(dto.getPrenom());
	        entity.setAdresse(dto.getAdresse());
	        entity.setEmail(dto.getEmail());
	        entity.setTelephone(dto.getTelephone());

	        return entity;
	    }

    // ClientDTO clientEntityToDto(ClientEntity entity);
	 public ClientDTO clientEntityToDto(ClientEntity entity) {
	        if (entity == null) return null;

	        ClientDTO dto = new ClientDTO();
	        dto.setIdentifiant(entity.getIdentifiant());
	        dto.setNom(entity.getNom());
	        dto.setPrenom(entity.getPrenom());
	        dto.setAdresse(entity.getAdresse());
	        dto.setEmail(entity.getEmail());
	        dto.setTelephone(entity.getTelephone());

	        return dto;
	    }


    // VehiculeEntity vehiculeDtoToEntity(VehiculeDTO dto);
	 public VehiculeEntity vehiculeDtoToEntity(VehiculeDTO dto) {
	        if (dto == null) return null;

	        VehiculeEntity entity = new VehiculeEntity();
	        entity.setIdentifiantVehicule(dto.getIdentifiantVehicule());
	        entity.setMarque(dto.getMarque());
	        entity.setModel(dto.getModele());
	        entity.setMatricule(dto.getMatricule());
	        entity.setKilometrage(dto.getKilometrage());

	        return entity;
	    }

    // VehiculeDTO vehiculeEntityToDto(VehiculeEntity entity);
	 public VehiculeDTO vehiculeEntityToDto(VehiculeEntity entity) {
	        if (entity == null) return null;

	        VehiculeDTO dto = new VehiculeDTO();
	        dto.setIdentifiantVehicule(entity.getIdentifiantVehicule());
	        dto.setMarque(entity.getMarque());
	        dto.setModele(entity.getModel());
	        dto.setMatricule(entity.getMatricule());
	        dto.setKilometrage(entity.getKilometrage());

	        return dto;
	    }
	 
	 

}

