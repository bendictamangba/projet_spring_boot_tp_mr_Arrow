package com.tpnoel.reservation.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tpnoel.reservation.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // Méthode pour récupérer les réservations d'un utilisateur spécifique par son ID
    List<Reservation> findByUserId(Long userId);
}
