package com.location.service.v1.controllers;

import org.springframework.http.ResponseEntity;

import com.location.service.api.ContratsApi;
import com.location.service.model.Contrat;

import jakarta.validation.Valid;

public class ContratController implements ContratsApi {
	@Override
	public ResponseEntity<Contrat> createContrat(@Valid Contrat contrat) {
		// TODO Auto-generated method stub
		return null;
	}

}