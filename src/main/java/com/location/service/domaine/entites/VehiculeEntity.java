package com.location.service.domaine.entites;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicules")
public class VehiculeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long identifiantVehicule;

    private String marque;
    private String modele;
    private String matricule;
    private Integer kilometrage;

    public VehiculeEntity() {}

    public Long getIdentifiantVehicule() {
        return identifiantVehicule;
    }

    public void setIdentifiantVehicule(Long identifiantVehicule) {
        this.identifiantVehicule = identifiantVehicule;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public Integer getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(Integer kilometrage) {
        this.kilometrage = kilometrage;
    }
}
