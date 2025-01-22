package com.tpnoel.reservation.model;

import java.util.List;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(schema = "reservationschema", name = "service")
@Data
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int capacity;              // Capacité totale
    private int currentOccupancy = 0;  // Occupation actuelle
    private boolean isAvailable ; // Disponibilite
    private String name;
    private String type;
    private String imagePath;     // Champ pour le chemin du fichier image

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    private List<LigneReservation> ligneReservations;
       private double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCurrentOccupancy() {
        return currentOccupancy;
    }

    public void setCurrentOccupancy(int currentOccupancy) {
        this.currentOccupancy = currentOccupancy;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<LigneReservation> getLigneReservations() {
        return ligneReservations;
    }

    public void setLigneReservations(List<LigneReservation> ligneReservations) {
        this.ligneReservations = ligneReservations;
    }

    public void reserve(int numberOfUsers) {
        if (isAvailable && currentOccupancy + numberOfUsers <= capacity) {
            currentOccupancy += numberOfUsers;
            if (currentOccupancy == capacity) {
                isAvailable = false; // Le service n'est plus disponible
            }
        } else {
            throw new RuntimeException("Capacity exceeded or service unavailable");
        }
    }

    public void release(int numberOfUsers) {
        currentOccupancy -= numberOfUsers;
        if (currentOccupancy < capacity) {
            isAvailable = true; // Le service redevient disponible
        }
    }
}
