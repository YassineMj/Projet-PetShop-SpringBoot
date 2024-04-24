package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.ProduitEntity;
import com.example.demo.repositories.ProduitRepository;

@Service
public class ProduitService {

	@Autowired
	ProduitRepository produitRepository;

	public List<ProduitEntity> getAllProduits() {
		return produitRepository.findAll();
	}

	public ProduitEntity getNewProduct() {
		return produitRepository.findFirstByCategorieNomCategorieOrderByIdProduitDesc("produits");
	}

	public List<ProduitEntity> getThreeLastetProducts() {
		return produitRepository.findTop3ByCategorieNomCategorieOrderByIdProduitDesc("produits");
	}

	public Long getCountProducts() {
		return produitRepository.countByCategorieNomCategorie("produits");
	}
}
