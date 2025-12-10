package com.location.service.domaine.services;

import com.location.service.model.VehiculeDTO;

import java.util.List;
import java.util.Optional;

public interface VehiculeService {
    List<VehiculeDTO> getAllVehicules();
    Optional<VehiculeDTO> getVehiculeById(Long id);
    VehiculeDTO saveVehicule(VehiculeDTO vehicule);
    void deleteVehicule(Long id);
}
