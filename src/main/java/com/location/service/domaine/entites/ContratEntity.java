package com.location.service.domaine.entites;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "contrats")
public class ContratEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehicule_id")
    private VehiculeEntity vehicule;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity client;

    private float prix;
    private int duree;

    // Nouveaux champs pour les dates
    private LocalDate dateDebut;
    private LocalDate dateFinEstimee;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public VehiculeEntity getVehicule() {
		return vehicule;
	}
	public void setVehicule(VehiculeEntity vehicule) {
		this.vehicule = vehicule;
	}
	public ClientEntity getClient() {
		return client;
	}
	public void setClient(ClientEntity client) {
		this.client = client;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public int getDuree() {
		return duree;
	}
	public void setDuree(int duree) {
		this.duree = duree;
	}

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFinEstimee() {
        return dateFinEstimee;
    }

    public void setDateFinEstimee(LocalDate dateFinEstimee) {
        this.dateFinEstimee = dateFinEstimee;
    }
}
