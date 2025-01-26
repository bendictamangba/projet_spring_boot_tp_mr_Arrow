package com.tpnoel.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tpnoel.reservation.model.Services;
import com.tpnoel.reservation.service.ReservationService;
import com.tpnoel.reservation.service.ServicesService;

@RestController // Indique que la classe est un composant Spring gérant les requêtes HTTP entrantes
@RequestMapping("/services") // Cette annotation lie les requêtes HTTP à cette classe
public class ServicesController {

	
    @Autowired
    private ServicesService servicesService;

    @Autowired
    private ReservationService reservationService;

@GetMapping("/all")
    
    public List<Services> getAllService() {
        List<Services> serv = servicesService.findAll();
        if (serv.isEmpty()) {
            throw new RuntimeException("Aucun service trouvé.");
        }
        return serv;
    }
   
    
    
    

    // Créer un service
    @PostMapping("/reservations") // Permet de créer un service
    public Services createService(@RequestBody Services services) {
        return servicesService.createService(services);
    }

    // Supprimer un service par son ID
    @DeleteMapping("reservations/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        servicesService.deleteService(id);
        return ResponseEntity.noContent().build(); // Retourne une réponse sans contenu (code 204)
    }
    
   // cette methode permet de rechercher les services en temps reels
    @GetMapping("/services/search")
    public ResponseEntity<List<Services>> searchServices(
        @RequestParam(required = false, defaultValue = "") String name,
        @RequestParam(required = false, defaultValue = "") String type,
        @RequestParam(required = false) String isAvailable)
    {
        Boolean isAvailableBoolean = null;

       
        if (isAvailable != null && !isAvailable.isEmpty()) {
            isAvailableBoolean = Boolean.valueOf(isAvailable);
        }

       
        if (isAvailableBoolean == null) {
            // Par exemple, ici nous retournons tous les services, indépendamment de leur disponibilité
            isAvailableBoolean = null; 
        }

        List<Services> services = reservationService.searchServices(name, type, isAvailableBoolean);
        return ResponseEntity.ok(services);
    }

    

    @GetMapping("/services/searchavailable")
    public ResponseEntity<List<Services>> searchServicess(
        @RequestParam(required = false, defaultValue = "") String name,
        @RequestParam(required = false, defaultValue = "") String type,
        @RequestParam(required = false) String isAvailable
    ) {
        // Convertir la valeur de 'isAvailable' en Boolean. Si elle est vide ou null, la valeur par défaut sera true (services disponibles).
        Boolean isAvailableBoolean = (isAvailable != null && !isAvailable.isEmpty()) ? Boolean.valueOf(isAvailable) : true;

        // Recherche des services en fonction des critères
        List<Services> services = reservationService.searchServices(name, type, isAvailableBoolean);

        // Retourner les services trouvés (uniquement ceux qui sont disponibles si isAvailable est vrai)
        return ResponseEntity.ok(services);
    }


}
