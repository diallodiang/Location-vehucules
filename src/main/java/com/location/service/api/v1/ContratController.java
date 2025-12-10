package com.location.service.api.v1;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.location.service.api.ContratsApi;
import com.location.service.domaine.services.CamundaService;
import com.location.service.domaine.services.ContratService;
import com.location.service.model.ContratDTO;
import com.location.service.model.VehiculeDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/contrats")
public class ContratController implements ContratsApi {
	
	@Autowired
	private ContratService contratService;

    @PostMapping("/")
    public ResponseEntity<ContratDTO> create(@RequestBody ContratDTO dto) {
        ContratDTO result = contratService.createContrat(dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/")
    public List<ContratDTO> getAll() {
        return contratService.getAllContrats();
    }

    @GetMapping("/{id}")
    public Optional<ContratDTO> getOne(@PathVariable Long id) {
        return contratService.getContratById(id);
    }
    
	
	/* @Override
	public ResponseEntity<ContratDTO> createContrat(@Reque ContratDTO contrat) {
		// TODO Auto-generated method stub
        String businessKey = "ProccessDemandeLocation" + contrat.getIdentifiantContrat();

        CamundaService.demarrerProcessus("ProccessDemandeLocation", businessKey);

        return ResponseEntity.ok(contrat);
	} */

}