package com.tpnoel.reservation.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tpnoel.reservation.model.Services;
import com.tpnoel.reservation.repository.ServicesRepository;

@Service
public class ServicesService {

    @Autowired
    private ServicesRepository servicesRepository;

    // Récupérer tous les services
    public List<Services> getAllServices() {
        return servicesRepository.findAll();
    }

    // Créer un nouveau service
    public Services createService(Services services) {
        return servicesRepository.save(services);
    }

    // Supprimer un service par son ID
    public void deleteService(Long id) {
        servicesRepository.deleteById(id);
    }

    // Rechercher des services en fonction de critères (à implémenter)
   
    
    public List<Services> searchServices(String name, String type, String isAvailable) {
        System.out.println("Recherche avec nom: " + name + ", type: " + type + ", disponibilité: " + isAvailable);
        
        // Si "isAvailable" est vide, on ne filtre pas sur la disponibilité
        if (isAvailable.isEmpty()) {
            return servicesRepository.findByNameContainingAndTypeContaining(name, type);
        } else {
            // Convertir la chaîne en boolean
            boolean available = Boolean.parseBoolean(isAvailable);
            return servicesRepository.findByNameContainingAndTypeContainingAndIsAvailable(name, type, available);
        }
    }
    // Méthode pour récupérer les services de type "chambre"
    public List<Services> getServicesByType(String type) {
        return servicesRepository.findByType(type);
    }
    
   



}
