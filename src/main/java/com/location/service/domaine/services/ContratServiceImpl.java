package com.location.service.domaine.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.location.service.domaine.entites.ContratEntity;
import com.location.service.domaine.mappers.ContratMappers;
import com.location.service.domaine.repositories.ContratRepository;
import com.location.service.model.ContratDTO;

@Service

public class ContratServiceImpl implements ContratService {
	@Autowired
	private ContratRepository contratRepository;
	
	@Autowired
	private ContratMappers contratMappers;
	
	
	@Override
	public ContratDTO createContrat(ContratDTO c) {
		ContratEntity contratEntity = contratMappers.contratDtoToEntity(c);
		contratRepository.save(contratEntity);
	
		// TODO Auto-generated method stub
		return contratMappers.contratEntityToDto(contratEntity);
	}

	@Override
	public List<ContratDTO> listContrats() {
		// TODO Auto-generated method stub
		
		
		 return contratRepository.findAll()
	                .stream()
	                .map(contratMappers::contratEntityToDto) // Entity → DTO
	                .collect(Collectors.toList());
	}

}
