package com.tpnoel.reservation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tpnoel.reservation.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // Cette interface héritera automatiquement des méthodes CRUD de JpaRepository pour l'entité User

    // Méthode pour trouver un utilisateur par son email
    Optional<User> findByEmail(String email);
    User findBySurname(String surname);
}
