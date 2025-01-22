package com.tpnoel.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tpnoel.reservation.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    // Cette interface héritera des méthodes CRUD standard de JpaRepository pour l'entité Notification
}
