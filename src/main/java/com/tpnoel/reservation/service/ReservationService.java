package com.tpnoel.reservation.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tpnoel.reservation.model.Reservation;
import com.tpnoel.reservation.model.Services;
import com.tpnoel.reservation.model.User;
import com.tpnoel.reservation.repository.ReservationRepository;
import com.tpnoel.reservation.repository.ServicesRepository;
import com.tpnoel.reservation.repository.UserRepository;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ServicesRepository servicesRepository;

    // Récupérer toutes les réservations
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    // Trouver toutes les réservations
    public List<Reservation> findAllReservations() {
        return reservationRepository.findAll();
    }

    // Rechercher des services en fonction de critères
    public List<Services> searchServices(String name, String type, boolean isAvailable) {
        return servicesRepository.findByNameContainingAndTypeContainingAndIsAvailable(name, type, isAvailable);
    }


    // Créer une nouvelle réservation
    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    // Récupérer l'historique des réservations pour un utilisateur spécifique
    public List<Reservation> getReservationsByUserId(Long userId) {
        return reservationRepository.findByUserId(userId);
    }
    
    public Reservation saveReservation(Reservation reservation, Long service_id, Long user_id) {
    	Services service= servicesRepository.findById(service_id).orElseThrow();
    	User user= userRepository.findById(user_id).orElseThrow();
    	
    	reservation.setService(service);
    	reservation.setUser(user);
		return reservationRepository.save(reservation);
	}
    
    public List<Services> searchServices(String name, String type, Boolean isAvailable) {
        // Exemple de recherche de services avec filtre sur le nom, type et disponibilité
        return servicesRepository.findAll().stream()
            .filter(service -> (name.isEmpty() || service.getName().contains(name)) &&
                               (type.isEmpty() || service.getType().equals(type)) &&
                               (isAvailable == null || service.isAvailable() == isAvailable))
            .collect(Collectors.toList());
    }
 // Supprimer une reservation
    public void deleteReservation(Long id) {
		reservationRepository.deleteById(id);
	
} 
}
