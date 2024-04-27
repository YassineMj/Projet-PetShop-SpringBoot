package com.example.demo.controllers;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.repositories.UserRepository;
import com.example.demo.requests.UserLoginRequest;
import com.example.demo.requests.UserRequest;
import com.example.demo.services.UserService;

import jakarta.websocket.server.PathParam;

@CrossOrigin(origins = "http://localhost:4200") // Autorise les requêtes cross-origin depuis http://localhost:4200
@RestController // Indique que cette classe est un contrôleur REST
@RequestMapping("Petshop/api/user") // Préfixe d'URL pour toutes les méthodes de ce contrôleur
public class UserController {

	@Autowired
	private UserService userService; // Injection de dépendance du service UserService

	@Autowired
	private UserRepository userRepository; // Injection de dépendance du repository UserRepository

	@PostMapping("/sign-up-user") // Requête POST pour l'inscription d'un utilisateur
	public ResponseEntity<Map<String, String>> singUpUser(@RequestBody UserRequest userRequest) {
		try {
			if (userRepository.existsByUserEmail(userRequest.getUserEmail()) == true) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST) // Requête invalide
						.body(Collections.singletonMap("error", "L'e-mail existe déjà.")); // Message d'erreur : email déjà utilisé
			}
			userService.saveUser(userRequest); // Délégation de l'enregistrement de l'utilisateur au service UserService
			return ResponseEntity.status(HttpStatus.CREATED) // Requête traitée avec succès - code 201 (CREATED)
					.body(Collections.singletonMap("message", "Utilisateur enregistré avec succès")); // Message de confirmation d'enregistrement

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) // Erreur interne du serveur
					.body(Collections.singletonMap("error", "Erreur lors de l'ajout du consultant : " + e.getMessage())); // Message d'erreur détaillé
		}

	}

	@PostMapping("/login-user") // Requête POST pour la connexion d'un utilisateur
	public ResponseEntity<String> loginUser(@RequestBody UserLoginRequest userLoginRequest) {
		if (userService.loginUser(userLoginRequest) != null) {
			return ResponseEntity.ok("authantification reussie! "); // Requête traitée avec succès - code 200 (OK) et message de confirmation
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("authantification echoué !!!!"); // Non autorisé - code 401 (UNAUTHORIZED) et message d'erreur
		}
	}

	@PutMapping("/update-user/{iduser}") // Requête PUT pour la mise à jour d'un utilisateur
	public ResponseEntity<String> updateUser(@PathVariable Long iduser, @RequestBody UserRequest userRequest) {
		userService.updateUser(iduser, userRequest); // Délégation de la mise à jour de l'utilisateur au service UserService
		return ResponseEntity.ok("update reussie!"); // Requête traitée avec succès - code 200 (OK) et message de confirmation
	}
	@GetMapping("get-count-users") // Requête GET pour récupérer le nombre de chiens
	public ResponseEntity<Map<String, Long>> getCountUsers() {
		// Appeler le service pour obtenir le nombre de chiens
		long countUsers = userService.getCountUser();

		// Créer un map pour stocker le résultat
		Map<String, Long> resultMap = new HashMap<>();
		resultMap.put("countUsers", countUsers);

		// Retourner le résultat dans ResponseEntity
		return ResponseEntity.ok(resultMap); // Requête traitée avec succès - code 200 (OK) et résultat dans un Map
	}
}
