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
	@Mapping(target = "id",source = "identifiantContrat")
  ContratEntity dtoVersEntity (ContratDTO dto);
	
	
	@InheritInverseConfiguration
  ContratDTO entityVersDTO (ContratEntity entity);
  
  ClientEntity dtoversEntity (ClientDTO dto);
  
  ClientDTO entityversDTO (ClientEntity entity); 
  
  VehiculeEntity dtoversEntity (VehiculeDTO dto);
  
  VehiculeDTO entityversDTO (VehiculeEntity entity);
  
  
}
