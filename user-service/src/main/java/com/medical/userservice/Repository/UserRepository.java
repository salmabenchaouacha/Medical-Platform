package com.medical.userservice.Repository;

import com.medical.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// HÉRITE de JpaRepository pour obtenir les méthodes CRUD de base
public interface UserRepository extends JpaRepository<User, Long> {

    // Méthode personnalisée cruciale pour Spring Security, elle récupère un User par son username.
    Optional<User> findByUsername(String username);
}