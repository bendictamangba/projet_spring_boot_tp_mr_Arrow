package com.tpnoel.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tpnoel.reservation.model.Reservation;
import com.tpnoel.reservation.service.ReservationService;

@RestController
@RequestMapping("/users") // Définit la base du chemin d'URL pour ce contrôleur
public class UserController {

    @Autowired
    private ReservationService reservationService;

    // Récupérer l'historique des réservations pour un utilisateur spécifique
    @GetMapping("/{userId}/historyUser") 
    public List<Reservation> getUserHistory(@PathVariable Long userId) {
        return reservationService.getReservationsByUserId(userId);
    }
  
   
    
    
    
}