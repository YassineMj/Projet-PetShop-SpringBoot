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

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("Petshop/api/achatCart")
public class CartController {
	@Autowired
	AchatProduitService achatProduitService;
	@Autowired
	AchatPetService achatPetService;

	@GetMapping("get-card/{idUser}")
	public ResponseEntity<?> getCardProduitByIdUser(@PathVariable Long idUser) {
		List<CartResponse> cartResponsePet = new ArrayList<CartResponse>();
		List<CartResponse> cartResponseProduit = new ArrayList<CartResponse>();
		List<CartResponse> cart = new ArrayList<CartResponse>();

		cartResponseProduit = achatProduitService.getCardProduitByIdUser(idUser);
		cartResponsePet = achatPetService.getCardPetByIdUser(idUser);
		if (cartResponseProduit == null && cartResponsePet == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Collections.singletonMap("error", "cart is empty"));
		}
		if (cartResponseProduit != null)
			cart.addAll(cartResponseProduit);
		if (cartResponsePet != null)
			cart.addAll(cartResponsePet);

		return ResponseEntity.ok(cart);

	}
}
