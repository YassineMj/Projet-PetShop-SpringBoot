package com.example.demo.controllers;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.PetEntity;
import com.example.demo.entities.ProduitEntity;
import com.example.demo.services.ProduitService;

@CrossOrigin(origins = "http://localhost:4200") // Autorise les requêtes cross-origin depuis http://localhost:4200
@RestController // Indique que cette classe est un contrôleur REST
@RequestMapping("Petshop/api/produit") // Préfixe d'URL pour toutes les méthodes de ce contrôleur
public class ProduitController {

	@Autowired
	ProduitService produitService; // Injection de dépendance du service ProduitService

	@GetMapping("/get-product/{idProduct}")
	public Optional<ProduitEntity> getPet(@PathVariable Long idProduct) {
		return produitService.getProduct(idProduct); 
	}
	
	@GetMapping("/get-all-products") // Requête GET pour récupérer tous les produits
	public List<ProduitEntity> getAllProdocuts() {
		return produitService.getAllProduits(); // Délégation de la récupération de tous les produits au service ProduitService
	}


	@GetMapping("get-Threelatest-products") // Requête GET pour récupérer les 3 derniers produits 
	public List<ProduitEntity> getThreeLastet() {
		return produitService.getThreeLastetProducts(); // Délégation de la récupération des 3 derniers produits au service ProduitService
	}

	@GetMapping("get-count-products") // Requête GET pour récupérer le nombre de produits
	public ResponseEntity<Map<String, Long>> getCountProducts() {
		// Appeler le service pour obtenir le nombre de produits
		long countProducts = produitService.getCountProducts();

		// Créer un map pour stocker le résultat
		Map<String, Long> resultMap = new HashMap<>();
		resultMap.put("countProducts", countProducts);

		// Retourner le résultat dans ResponseEntity
		return ResponseEntity.ok(resultMap); // Requête traitée avec succès - code 200 (OK) et résultat dans un Map
	}

}
