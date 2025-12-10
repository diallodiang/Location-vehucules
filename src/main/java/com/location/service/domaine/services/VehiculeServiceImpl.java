package com.location.service.domaine.services;

import com.location.service.domaine.entites.VehiculeEntity;
import com.location.service.domaine.mappers.ContratMappers;
import com.location.service.domaine.repositories.VehiculeRepository;
import com.location.service.model.VehiculeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculeServiceImpl implements VehiculeService {

    @Autowired
    private VehiculeRepository vehiculeRepository;

    @Autowired
    private ContratMappers contratMappers;

    @Override
    public List<VehiculeDTO> getAllVehicules() {
        return vehiculeRepository.findAll()
                .stream()
                .map(contratMappers::vehiculeEntityToDto)
                .toList();
    }

    @Override
    public Optional<VehiculeDTO> getVehiculeById(Long id) {
        return Optional.ofNullable(vehiculeRepository.findById(id)
                .map(contratMappers::vehiculeEntityToDto)
                .orElse(null));
    }

    @Override
    public VehiculeDTO saveVehicule(VehiculeDTO vehiculeDTO) {
        VehiculeEntity entity = contratMappers.vehiculeDtoToEntity(vehiculeDTO);
        VehiculeEntity saved = vehiculeRepository.save(entity);
        return contratMappers.vehiculeEntityToDto(saved);
    }

    @Override
    public void deleteVehicule(Long id) {
        vehiculeRepository.deleteById(id);
    }
}
