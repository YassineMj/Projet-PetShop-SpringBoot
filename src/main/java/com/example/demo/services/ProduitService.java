package com.example.demo.services; // Indique le package dans lequel se trouve la classe ProduitService

import java.util.List; // Importe la classe List de la librairie java.util
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired; // Annotation Spring pour l'injection de dépendances
import org.springframework.stereotype.Service; // Annotation Spring pour définir la classe comme un service

import com.example.demo.entities.AchatProduitEntity;
import com.example.demo.entities.ProduitEntity; // Importe la classe ProduitEntity
import com.example.demo.entities.UserEntity;
import com.example.demo.repositories.AchatProduitRepository;
import com.example.demo.repositories.ProduitRepository;
import com.example.demo.repositories.UserRepository;

@Service
public class ProduitService { // Déclaration de la classe ProduitService

	@Autowired // Injection de dépendance du repository ProduitRepository
	ProduitRepository produitRepository;
	
	@Autowired
	UserRepository userRepository;

	public List<ProduitEntity> getAllProduits() { // Méthode pour récupérer tous les produits
		return produitRepository.findAll(); // Retourne la liste de tous les produits
	}

	public List<ProduitEntity> getThreeLastetProducts() { // Méthode pour récupérer les 3 derniers produits
		return produitRepository.findTop3ByCategorieNomCategorieOrderByIdProduitDesc("produits"); 
		// Recherche les 3 premiers produits de la catégorie "produits" ordonnés par idProduit décroissant
	}

	public Long getCountProducts() { // Méthode pour compter le nombre de produits
		return produitRepository.countByCategorieNomCategorie("produits"); // Compte le nombre de produits de la catégorie "produits"
	}

	public Optional<ProduitEntity> getProduct(Long idProduct) {
		return produitRepository.findById(idProduct);
	}
	


}
