package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.responses.CartResponse;
import com.example.demo.services.AchatPetService;
import com.example.demo.services.AchatProduitService;

@CrossOrigin(origins = "http://localhost:4200") // Autorise les requêtes cross-origin depuis http://localhost:4200
@RestController // Indique que cette classe est un contrôleur REST
@RequestMapping("Petshop/api/achatCart") // Préfixe d'URL pour toutes les méthodes de ce contrôleur
public class CartController {

	@Autowired
	AchatProduitService achatProduitService; // Injection de dépendance du service AchatProduitService

	@Autowired
	AchatPetService achatPetService; // Injection de dépendance du service AchatPetService

	@GetMapping("get-card/{idUser}") // Requête GET pour récupérer le panier d'un utilisateur
	public ResponseEntity<?> getCardProduitByIdUser(@PathVariable Long idUser) {
		List<CartResponse> cartResponsePet = new ArrayList<CartResponse>(); // Liste pour les réponses des animaux
		List<CartResponse> cartResponseProduit = new ArrayList<CartResponse>(); // Liste pour les réponses des produits
		List<CartResponse> cart = new ArrayList<CartResponse>(); // Liste combinée pour toutes les réponses du panier

		cartResponseProduit = achatProduitService.getCardProduitByIdUser(idUser); // Récupérer les produits du panier
		cartResponsePet = achatPetService.getCardPetByIdUser(idUser); // Récupérer les animaux du panier

		// Vérifier si le panier est vide
		if (cartResponseProduit == null && cartResponsePet == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) // Erreur interne du serveur
					.body(Collections.singletonMap("error", "Le panier est vide")); // Message d'erreur : panier vide
		}

		// Combiner les réponses des produits et des animaux dans la liste finale
		if (cartResponseProduit != null)
			cart.addAll(cartResponseProduit);
		if (cartResponsePet != null)
			cart.addAll(cartResponsePet);

		return ResponseEntity.ok(cart); // Requête traitée avec succès - code 200 (OK) et panier combiné dans le corps de la réponse
	}
}
