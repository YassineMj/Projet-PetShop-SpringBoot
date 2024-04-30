package com.example.demo.controllers;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.requests.AchatPetRequest;
import com.example.demo.requests.AchatProduitRequest;
import com.example.demo.services.AchatProduitService;

@CrossOrigin(origins = "http://localhost:4200") // Autorise les requêtes cross-origin depuis http://localhost:4200
@RestController // Indique que cette classe est un contrôleur REST
@RequestMapping("Petshop/api/achatproduit") // Préfixe d'URL pour toutes les méthodes de ce contrôleur
public class AchatProduitController {

	@Autowired
	AchatProduitService achatProduitService; // Injection de dépendance du service AchatProduitService

	@PostMapping("/card-achat-produit") // Requête POST pour ajouter un produit au panier
	public ResponseEntity<Map<String, String>> achatProduit(@RequestBody AchatProduitRequest achatPorduitRequest) {
		try {
			achatProduitService.achatProduit(achatPorduitRequest); // Délégation de l'ajout du produit au panier au service AchatProduitService
			return ResponseEntity.status(HttpStatus.CREATED) // Requête traitée avec succès - code 201 (CREATED)
					.body(Collections.singletonMap("Message", "Achat de produit effectué avec succès.")); // Message de confirmation d'ajout au panier
		}

		catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST) // Requête invalide - code 400 (BAD_REQUEST)
					.body(Collections.singletonMap("Message", e.getMessage())); // Message d'erreur avec details de l'exception
		}
	}
	
	@GetMapping("/most-popular-product") // Requête GET pour récupérer le produit le plus populaire
	public ResponseEntity<?> mostPopularProduct() {
		Map<String,Object> response = new HashMap<>(); // Map pour stocker les informations du produit le plus populaire
		String mostPopularProduct = achatProduitService.mostPopularProduct(); // Récupérer le produit le plus populaire via le service

		if (mostPopularProduct != null) {
			String[] productInfo = mostPopularProduct.split(","); // Diviser la chaîne de caractères en tableau d'informations produit
			response.put("id", productInfo[0]); // Ajouter l'ID du produit au Map
            response.put("name", productInfo[1]); // Ajouter le nom du produit au Map
            response.put("category", productInfo[2]); // Ajouter la catégorie du produit au Map
            response.put("quantite", Integer.parseInt(productInfo[3])); 
            // Ajouter la quantité du produit au Map (conversion en entier)
            response.put("description", productInfo[4]); // Ajouter la description du produit au Map
            response.put("imageUrl", productInfo[5]); // Ajouter l'URL de l'image du produit au Map
			return ResponseEntity.ok(response); // Requête traitée avec succès - code 200 (OK) et informations du produit dans le corps de la réponse
		} else {
			return ResponseEntity.notFound().build(); // Requête non trouvée - code 404 (NOT FOUND)
		}
	}
	
	@DeleteMapping("/delete-achat-product/{idUser}/{idProduit}")
	public ResponseEntity<Map<String, String>> deleteAchatProduct(@PathVariable long idUser ,@PathVariable long idProduit) {
		achatProduitService.deleteAchatProduct(idUser , idProduit);
	    return ResponseEntity.status(HttpStatus.OK)
	            .body(Collections.singletonMap("Message", "Achats de produits supprimés avec succès pour l'utilisateur avec l'id " + idUser));
	}

}
