package com.medical.userservice;

import com.medical.userservice.Security.JwtService;
import com.medical.userservice.model.User;
import com.medical.userservice.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthService {
    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;

    // Le constructeur reste inchangé
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Inscription d'un nouvel utilisateur. Retourne le JWT directement.
     */
    public String register(String username, String password, String role) { // <-- Prend des String au lieu du DTO
        // Validation basique (vérifier si l'utilisateur existe déjà)
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("L'utilisateur existe déjà.");
        }

        // 1. Créer l'utilisateur
        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password)) // Hachage du mot de passe
                .role(role != null ? "ROLE_" + role.toUpperCase() : "ROLE_PATIENT")
                .build();

        userRepository.save(user);

        // 2. Générer le token pour l'utilisateur fraîchement créé
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        String jwtToken = jwtService.generateToken(userDetails);

        return jwtToken; // <-- Retourne String
    }

    /**
     * Connexion de l'utilisateur. Retourne le JWT directement.
     */
    public String login(String username, String password) { // <-- Prend des String au lieu du DTO

        // 1. Authentifier l'utilisateur
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password
                )
        );

        // 2. Si l'authentification réussit (aucune exception levée)
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // 3. Générer le token
        String jwtToken = jwtService.generateToken(userDetails);

        log.info("Généré token pour user {}: {}", username, jwtToken);
        System.out.println("[AuthService] token=" + jwtToken);

        return jwtToken; // <-- Retourne String
    }
}