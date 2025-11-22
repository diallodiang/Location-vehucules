package com.location.service.domaine.services;

import java.util.List;

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
		ContratEntity contratEntity = contratMappers.dtoVersEntity(c);
		contratRepository.save(contratEntity);
	
		// TODO Auto-generated method stub
		return contratMappers;
	}

	@Override
	public List<ContratDTO> listContrats() {
		// TODO Auto-generated method stub
		return null;
	}

}
