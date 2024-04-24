package com.example.demo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.ProduitEntity;
import com.example.demo.services.ProduitService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("Petshop/api/produit")
public class ProduitController {

	@Autowired
	ProduitService produitService;

	@GetMapping("/get-all-products")
	public List<ProduitEntity> getAllProdocuts() {
		return produitService.getAllProduits();
	}

	/*
	 * @GetMapping("get-new-product") public ProduitEntity getNewProduct() { return
	 * produitService.getNewProduct(); }
	 */

	@GetMapping("get-Threelastet-products")
	public List<ProduitEntity> getThreeLastet() {
		return produitService.getThreeLastetProducts();
	}

	@GetMapping("get-count-products")
	public ResponseEntity<Map<String, Long>> getCountProducts() {
		// Appeler le service pour obtenir le nombre de produits
		long countProducts = produitService.getCountProducts();

		// Créer un map pour stocker le résultat
		Map<String, Long> resultMap = new HashMap<>();
		resultMap.put("countProducts", countProducts);

		// Retourner le résultat dans ResponseEntity
		return ResponseEntity.ok(resultMap);
	}

}
