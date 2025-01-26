package com.tpnoel.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tpnoel.reservation.model.Reservation;
import com.tpnoel.reservation.model.User;
import com.tpnoel.reservation.repository.UserRepository;
import com.tpnoel.reservation.repository.UserRepository;
import com.tpnoel.reservation.service.ReservationService;

@Controller
public class DisplayHistoriqueUserController {
	
	 @Autowired
	 private ReservationService reservationService;

	 @Autowired
	 private UserRepository userRepository;

		
	@GetMapping("/{userId}/history")
	    public String getUserHistory(@PathVariable Long userId, Model model) {
	        List<Reservation> reserva = reservationService.getReservationsByUserId(userId);
	        System.out.println("reserva: " + reserva);
	        model.addAttribute("reserva", reserva);
	        model.addAttribute("userId", userId);
	        return "displayReservationHistory"; 
	    }
	
	@GetMapping("/history")
	public String getUserHistory(Model model) {
	    // Récupérer l'email de l'utilisateur connecté
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String email = authentication.getName(); // L'email de l'utilisateur connecté

	    // Trouver l'utilisateur par son email
	    User user = userRepository.findByEmail(email)
	            .orElseThrow(() -> new IllegalStateException("User not found with email: " + email));

	    // Obtenir les réservations de l'utilisateur connecté
	    List<Reservation> reserva = reservationService.getReservationsByUserId(user.getId());

	    // Ajouter les données au modèle
	    model.addAttribute("reserva", reserva);
	    model.addAttribute("userId", user.getId());

	    return "historiqueUserConnecte"; 
	}

	
}

