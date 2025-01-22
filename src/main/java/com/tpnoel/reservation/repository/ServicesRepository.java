package com.tpnoel.reservation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tpnoel.reservation.model.Services;

public interface ServicesRepository extends JpaRepository<Services, Long> {
    // Rechercher des services par nom, type et disponibilité (isAvailable) sans utiliser LIKE pour un booléen
    List<Services> findByNameContainingAndTypeContainingAndIsAvailable(String name, String type, boolean isAvailable);

    List<Services> findByNameContainingAndTypeContaining(String name, String type);

    // Recherche par nom, type et disponibilité (isAvailable)
 
    // List<Services> findByNameContainingAndTypeContainingAndIsAvailable(String name, String type, boolean isAvailable);
    
    List<Services> findAll();
    List<Services> findByType(String type);
    
//    Optional<Services> findById(Long id);
}
