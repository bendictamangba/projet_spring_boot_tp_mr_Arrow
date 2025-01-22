package com.tpnoel.reservation.controller;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tpnoel.reservation.model.Reservation;
import com.tpnoel.reservation.model.User;
import com.tpnoel.reservation.repository.NotificationRepository;
import com.tpnoel.reservation.repository.ServicesRepository;
import com.tpnoel.reservation.repository.UserRepository;
import com.tpnoel.reservation.service.ReservationService;
@Controller
@RequestMapping("/reservationss")
public class DisplayReservationController {
	
	 @Autowired
	 private ReservationService reservationService;
	 
	 @Autowired
	 private ServicesRepository servicesRepository;
	
	 @Autowired
	 private UserRepository userRepository;
	
	 @Autowired
	 private NotificationRepository notificationRepository;
	
	 
	 @GetMapping ("/displayreservation")
		private String displayReservation(Model model) {
			model.addAttribute("reservations", reservationService.getAllReservations());
			return "displayReservation";
	 }
	
	 
	 //il s'agit de la methode qui affiche le formulaire d'insertion
	 //de reservation en utilisans l'id de l'utilisateur qui est connectee et prendre le service sur lequel on a cliqué
	 @GetMapping("/addreservation")
	 public String addReservation(Model model, @RequestParam("serviceId") Long serviceId) {
	     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	     String email = authentication.getName(); // Email de l'utilisateur connecté
	     // Rechercher l'utilisateur avec son ID
	     User user = userRepository.findByEmail(email)
	             .orElseThrow(() -> new IllegalStateException("User not found"));

	     model.addAttribute("userId", user.getId()); // ID de l'utilisateur
	     model.addAttribute("serviceId", serviceId);

	     // Créer une nouvelle réservation
	     Reservation reservation = new Reservation();

	     // Définir la date de réservation sur la date et l'heure actuelles
	     LocalDateTime now = LocalDateTime.now();
	     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	     String formattedDate = now.format(formatter);
	     reservation.setReservation_date(formattedDate); 

	     // Ajouter l'objet Reservation au modèle
	     model.addAttribute("reservation", reservation);
	    
	     return "addreservation";
	 }

	     
	     
		@PostMapping("/addreservation")
		public String saveReservation(@ModelAttribute("reservation") Reservation reservation,@RequestParam Long service_id, @RequestParam Long user_id ) {
			reservationService.saveReservation(reservation,service_id ,user_id);
			return "merci";
		}
		
	
		 
		@GetMapping("/delete/{id}")
		public String deleteReservation(@PathVariable Long id) {
			reservationService.deleteReservation(id);
			return "redirect:/reservationss/displayreservation";
		

	
}
		
		

		
		
		
	
		
	 
	 
	 
	 
	 
	 
	 
	 

}
	 
	 
	 
	 
	 

	 