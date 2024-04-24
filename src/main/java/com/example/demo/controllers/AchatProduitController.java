package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.requests.AchatPetRequest;
import com.example.demo.requests.AchatProduitRequest;
import com.example.demo.services.AchatProduitService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("Petshop/api/achatproduit")
public class AchatProduitController {

	@Autowired
	AchatProduitService achatProduitService;
	
	@PostMapping("/achat-produit")
	public ResponseEntity<String> achatProduit(@RequestBody AchatProduitRequest achatPorduitRequest){
		try {
			achatProduitService.achatProduit(achatPorduitRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Achat de pet effectué avec succès.");
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error dans l'achat de pet" +e.getMessage());
		}
	}
}
