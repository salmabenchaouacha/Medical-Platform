package com.medical.userservice.controller;

import com.medical.userservice.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody Map<String, String> request) {

        // DEBUG 1: Afficher la requête brute
        System.out.println("=== DEBUG: Requête reçue ===");
        System.out.println("Type de request: " + request.getClass());
        System.out.println("Taille de la map: " + request.size());
        System.out.println("Contenu: " + request.toString());

        // Vérifier si la map est vide
        if (request == null || request.isEmpty()) {
            System.out.println("ERROR: La requête est NULL ou VIDE");
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "La requête est vide"));
        }

        // Afficher toutes les clés disponibles
        System.out.println("Clés disponibles dans la map:");
        for (String key : request.keySet()) {
            System.out.println("  - '" + key + "' = '" + request.get(key) + "'");
        }

        // Extraire les valeurs
        String username = request.get("username");
        String password = request.get("password");
        String role = request.get("role");

        System.out.println("=== Valeurs extraites ===");
        System.out.println("username: " + username);
        System.out.println("password: " + password);
        System.out.println("role: " + role);

        // Validation simple
        if (username == null || username.trim().isEmpty()) {
            System.out.println("ERROR: username est null ou vide");
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Le champ 'username' est requis"));
        }

        if (password == null || password.trim().isEmpty()) {
            System.out.println("ERROR: password est null ou vide");
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Le champ 'password' est requis"));
        }

        if (role == null || role.trim().isEmpty()) {
            System.out.println("ERROR: role est null ou vide");
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Le champ 'role' est requis"));
        }

        try {
            System.out.println("=== Appel du AuthService ===");
            String token = authService.register(username, password, role);

            System.out.println("=== SUCCÈS ===");
            log.info("Nouvel utilisateur enregistré: {} — token: {}", username, token);
            System.out.println("Token généré: " + token);

            return ResponseEntity.ok(Map.of("token", token));

        } catch (Exception e) {
            System.out.println("=== ERREUR dans AuthService ===");
            System.out.println("Type d'exception: " + e.getClass().getName());
            System.out.println("Message: " + e.getMessage());
            e.printStackTrace(); // Stack trace complète

            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> request) {
        try {
            String username = request.get("username");
            String password = request.get("password");

            String token = authService.login(username, password);

            return ResponseEntity.ok(Map.of("token", token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Nom d'utilisateur ou mot de passe incorrect."));
        }
    }
    @GetMapping("/hello")
    public String sayHelloTest() {
        // Renvoie une chaîne confirmant l'identité du service et son port
        return "SUCCESS: Bonjour de l'Équipe 'Qui êtes-vous ?' (User-Service).";
    }
}