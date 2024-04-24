package com.example.demo.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.requests.AchatPetRequest;
import com.example.demo.services.AchatPetService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("Petshop/api/achatpet")
public class AchatPetController {

	@Autowired
	AchatPetService achatPetService;

	@PostMapping("/card-achat-pet")
	public ResponseEntity<Map<String, String>> achatPet(@RequestBody AchatPetRequest achatPetRequest) {
		try {
			achatPetService.achatPet(achatPetRequest);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(Collections.singletonMap("Message", "Achat de pet effectué avec succès."));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(Collections.singletonMap("Message", e.getMessage()));
		}
	}
	
	@GetMapping("get-card-pet/{idUser}")
	public ResponseEntity<?> getCartByIdUser(@PathVariable Long idUser) {
        return achatPetService.getCartPetByIdUser(idUser);
    }
}
