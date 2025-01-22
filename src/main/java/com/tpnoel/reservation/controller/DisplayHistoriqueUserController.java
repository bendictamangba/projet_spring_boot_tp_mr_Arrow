package com.tpnoel.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tpnoel.reservation.model.Reservation;
import com.tpnoel.reservation.service.ReservationService;

@Controller
public class DisplayHistoriqueUserController {
	
	 @Autowired
	 private ReservationService reservationService;

	
	@GetMapping("/{userId}/history")
	    public String getUserHistory(@PathVariable Long userId, Model model) {
	        List<Reservation> reserva = reservationService.getReservationsByUserId(userId);
	        System.out.println("reserva: " + reserva);
	        model.addAttribute("reserva", reserva);
	        model.addAttribute("userId", userId);
	        return "displayReservationHistory"; // Nom de la vue Thymeleaf
	    }
}
