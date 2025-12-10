package com.location.service.api.v1;

import com.location.service.domaine.services.ClientService;
import com.location.service.domaine.services.VehiculeService;
import com.location.service.model.ClientDTO;
import com.location.service.model.VehiculeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicules")
public class VehiculeController {
    @Autowired
    private VehiculeService vehiculeService;

    // Ajouter un véhicule
    @PostMapping("/")
    public VehiculeDTO addVehicule(@RequestBody VehiculeDTO vehiculeDTO) {
        return vehiculeService.saveVehicule(vehiculeDTO);
    }

    // Ajouter plusieurs véhicules
    @PostMapping("/add-list")
    public List<VehiculeDTO> addVehicules(@RequestBody List<VehiculeDTO> vehiculeList) {
        List<VehiculeDTO> savedVehicules = new ArrayList<>();
        for (VehiculeDTO v : vehiculeList) {
            savedVehicules.add(vehiculeService.saveVehicule(v));
        }
        return savedVehicules;
    }

    // Afficher tous les véhicules
    @GetMapping("/")
    public List<VehiculeDTO> getAllVehicules() {
        return vehiculeService.getAllVehicules();
    }

    // Afficher un véhicule
    @GetMapping("/{id}")
    public Optional<VehiculeDTO> getVehiculeById(@PathVariable Long id) {
        return vehiculeService.getVehiculeById(id);
    }

    // Supprimer un véhicule
    @DeleteMapping("/{id}")
    public List<VehiculeDTO> deleteVehiculeById(@PathVariable Long id) {
        vehiculeService.deleteVehicule(id);
        return this.getAllVehicules();
    }
}
