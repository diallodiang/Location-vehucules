package com.location.service.api.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.location.service.api.ContratsApi;
import com.location.service.domaine.services.CamundaService;
import com.location.service.domaine.services.ContratService;
import com.location.service.model.ContratDTO;
import com.location.service.model.VehiculeDTO;

import jakarta.validation.Valid;

@RestController
public class ContratController implements ContratsApi {
	
	/* @Autowired
	private ContratService contratService;
	
	@Override
	public ResponseEntity<ContratDTO> createContrat(@Valid ContratDTO contrat) {
		// TODO Auto-generated method stub
        String businessKey = "ProccessDemandeLocation" + contrat.getIdentifiantContrat();

        CamundaService.demarrerProcessus("ProccessDemandeLocation", businessKey);

        return ResponseEntity.ok(contrat);	
        
	} */

}