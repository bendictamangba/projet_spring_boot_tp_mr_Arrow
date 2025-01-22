package com.tpnoel.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tpnoel.reservation.model.Reservation;
import com.tpnoel.reservation.model.Services;
import com.tpnoel.reservation.model.User;
import com.tpnoel.reservation.repository.NotificationRepository;
import com.tpnoel.reservation.repository.ServicesRepository;
import com.tpnoel.reservation.repository.UserRepository;
import com.tpnoel.reservation.service.ReservationService;

@RestController
@RequestMapping("/recherche")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    // Récupérer toutes les réservations
    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @Autowired
    private UserRepository userRepository; // Pour récupérer l'utilisateur par ID
    @Autowired
    private ServicesRepository servicesRepository; // Pour récupérer le service par ID
    @Autowired
    private NotificationRepository notificationRepository; // Pour récupérer la notification par ID

    // Recherche des services avec des critères
    
    
   
   
      
    
    // Créer une réservation
    @PostMapping("/enregistrementreservation")
    public ResponseEntity<?> createReservation(@RequestBody Reservation reservation) {
        // Vérification des IDs
        if (reservation.getUser() == null || reservation.getUser().getId() == null) {
            return ResponseEntity.badRequest().body("User ID must not be null");
        }
        if (reservation.getService() == null || reservation.getService().getId() == null) {
            return ResponseEntity.badRequest().body("Service ID must not be null");
        }

        // Vérification et récupération des entités
        User user = userRepository.findById(reservation.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Services service = servicesRepository.findById(reservation.getService().getId())
                .orElseThrow(() -> new RuntimeException("Service not found"));

        // Gestion de la disponibilité
        int numberOfUsers = reservation.getNumberOfUsers();
        try {
            service.reserve(numberOfUsers); // Met à jour l'occupation et la disponibilité
            servicesRepository.save(service); // Sauvegarde les changements
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        // Lier et enregistrer la réservation
        reservation.setUser(user);
        reservation.setService(service);
        Reservation savedReservation = reservationService.createReservation(reservation);

        return ResponseEntity.ok(savedReservation);
    }
    
    

}
