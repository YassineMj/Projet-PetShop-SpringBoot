package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	@GetMapping("/get-all-product")
	public List<ProduitEntity> getAllProdocuts() {
		return produitService.getAllProduits();
	}

	@GetMapping("get-new-product")
	public ProduitEntity getNewProduct() {
		return produitService.getNewProduct();
	}

	@GetMapping("get-Threelastet-products")
	public List<ProduitEntity> getThreeLastet() {
		return produitService.getThreeLastetProducts();
	}

	@GetMapping("get-count-products")
	public Long getCountProducts() {
		long countProduct = produitService.getCountProducts();
		return countProduct;
	}

}
