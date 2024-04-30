package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.AchatPetEntity;
import com.example.demo.entities.AchatProduitEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.repositories.AchatPetRepository;
import com.example.demo.repositories.AchatProduitRepository;
import com.example.demo.requests.PaiementRequest;
import com.example.demo.responses.CartResponse;
import com.example.demo.services.AchatPetService;
import com.example.demo.services.AchatProduitService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentConfirmParams;
import com.stripe.param.PaymentIntentCreateParams;

@CrossOrigin(origins = "http://localhost:4200") // Autorise les requêtes cross-origin depuis http://localhost:4200
@RestController // Indique que cette classe est un contrôleur REST
@RequestMapping("Petshop/api/achatCart") // Préfixe d'URL pour toutes les méthodes de ce contrôleur
public class CartController {

	@Autowired
	AchatProduitService achatProduitService; // Injection de dépendance du service AchatProduitService

	@Autowired
	AchatPetService achatPetService; // Injection de dépendance du service AchatPetService
	
	@Autowired
	AchatPetRepository achatPetRepository;
	
	@Autowired
	AchatProduitRepository achatProduitRepository;

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
	
	@PostMapping("card-paiement")
	public ResponseEntity<?> cardPaiement(@RequestBody PaiementRequest paiementRequest){
		
		Stripe.apiKey = "sk_test_51OyypLAfJRZNY57iG5692KP0pf71AvYcyQRjEOzJCQTbkwyFPop01V3DPbBdTzz4JGVzUHgu7kRIUsUhiOIzClNa007kAcWtMi";

        // Création du paiement depuis le client source
        PaymentIntent intent;
		try {
			intent = PaymentIntent.create(PaymentIntentCreateParams.builder()
			        .setAmount((long) paiementRequest.getTotal()*100) // Montant à transférer en centimes
			        .setCurrency("eur") // Devise du montant
			        .setCustomer(paiementRequest.getSourceClientIdStripe()) // Identifiant du client source
			        .build());
			
			// Confirmation du paiement avec une URL de retour fictive
            PaymentIntentConfirmParams confirmParams = PaymentIntentConfirmParams.builder()
                    .setPaymentMethod(paiementRequest.getSourceCartIdStrip())
                    .setReturnUrl("http://example.com")
                    .build();
            intent.confirm(confirmParams);
			
			//initialisation status Achat pet de user
			List<String> listIdAchatPet = achatPetRepository.findAllByIdUser(paiementRequest.getIdUser());
			for(String achat : listIdAchatPet) {
				Optional<AchatPetEntity> achatPetEntity= achatPetRepository.findById(Long.parseLong(achat));
				achatPetEntity.get().setStatusAchatPet(false);
				achatPetRepository.save(achatPetEntity.get());
			}
			
			//initialisation status Achat product de user
			List<String> listIdAchatProduct = achatProduitRepository.findAllByIdUser(paiementRequest.getIdUser());
			for(String achat : listIdAchatProduct) {
				Optional<AchatProduitEntity> achatProductEntity= achatProduitRepository.findById(Long.parseLong(achat));
				achatProductEntity.get().setStatusAchatProduit(false);
				achatProduitRepository.save(achatProductEntity.get());
			}
			
			// Affichage de l'ID du paiement créé
			return ResponseEntity.status(HttpStatus.OK)
		            .body(Collections.singletonMap("Message", "Payment created: " + intent.getId()));
			
		} catch (StripeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST) // Requête invalide - code 400 (BAD_REQUEST)
					.body(Collections.singletonMap("Message", "Error"));
		}
		
	}
}
