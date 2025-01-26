package com.tpnoel.reservation.controller;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
import org.springframework.web.client.RestTemplate;

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
	
	  @Autowired
	    private RestTemplate restTemplate;

	    private final String BASE_URL = "http://localhost:8080/facilities"; 
 
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
	 public String saveReservation(
	     @ModelAttribute("reservation") Reservation reservation,
	     @RequestParam Long service_id, 
	     @RequestParam Long user_id, 
	     Model model
	 ) {
	     // Récupérer la date d'aujourd'hui
	     LocalDate today = LocalDate.now();

	     // Définir une limite pour la date future (par exemple, dans les 30 prochains jours)
	     LocalDate maxFutureDate = today.plusDays(30);

	     // Conversion des dates saisies
	     LocalDate arrivalDate = LocalDate.parse(reservation.getArrival_date());
	     LocalDate departureDate = LocalDate.parse(reservation.getDeparture_date());

	     // Vérification : la date d'arrivée ne peut pas être dans le passé
	     if (arrivalDate.isBefore(today)) {
	         model.addAttribute("error", "La date d'arrivée ne peut pas être dans le passé.");
	         return "addreservation"; // Retourne le formulaire avec un message d'erreur
	     }

	     // Vérification : la date d'arrivée ne peut pas être trop loin dans le futur
	     if (arrivalDate.isAfter(maxFutureDate)) {
	         model.addAttribute("error", "La date d'arrivée doit être dans les 30 jours à partir d'aujourd'hui.");
	         return "addreservation"; // Retourne le formulaire avec un message d'erreur
	     }

	     // Vérification : la date de départ ne peut pas être dans le passé
	     if (departureDate.isBefore(today)) {
	         model.addAttribute("error", "La date de départ ne peut pas être dans le passé.");
	         return "addreservation"; // Retourne le formulaire avec un message d'erreur
	     }

	     // Vérification : la date de départ doit être après la date d'arrivée
	     if (departureDate.isBefore(arrivalDate)) {
	         model.addAttribute("error", "La date de départ doit être après la date d'arrivée.");
	         return "addreservation"; // Retourne le formulaire avec un message d'erreur
	     }

	     // Sauvegarde de la réservation si toutes les validations sont réussies
	     reservationService.saveReservation(reservation, service_id, user_id);
	     return "merci"; // Page de confirmation
	 }

		 
		@GetMapping("/delete/{id}")
		public String deleteReservation(@PathVariable Long id) {
			reservationService.deleteReservation(id);
			return "redirect:/reservationss/displayreservation";
		

	
}
		
		

		
		
		
	
		
	 
	 
	 
	 
	 
	 
	 
	 

}
	 
	 
	 
	 
	 

	 