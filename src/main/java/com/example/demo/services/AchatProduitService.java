package com.example.demo.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entities.AchatProduitEntity;
import com.example.demo.entities.ProduitEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.repositories.AchatProduitRepository;
import com.example.demo.repositories.ProduitRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.requests.AchatProduitRequest;
import com.example.demo.responses.CartResponse;

@Service
public class AchatProduitService {

	@Autowired
	UserRepository userRepository; // Injection de dépendance du repository UserRepository

	@Autowired
	AchatProduitRepository achatProduitRepository; // Injection de dépendance du repository AchatProduitRepository

	@Autowired
	ProduitRepository produitRepository; // Injection de dépendance du repository ProduitRepository

	public String achatProduit(AchatProduitRequest achatProduitRequest) {
		ProduitEntity produit = produitRepository.findByIdProduit(achatProduitRequest.getIdProduit())
				.orElseThrow(() -> new IllegalArgumentException("Produit non trouvé")); // Recherche le produit par son ID et lève une exception si introuvable
		UserEntity user = userRepository.findByIdUser(achatProduitRequest.getIdUser())
				.orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé")); // Recherche l'utilisateur par son ID et lève une exception si introuvable
		AchatProduitEntity achatProduitEntity = new AchatProduitEntity(); // Crée une nouvelle entité d'achat de produit
		achatProduitEntity.setUser(user); // Associe l'utilisateur à l'achat
		achatProduitEntity.setProduit(produit); // Associe le produit à l'achat
		achatProduitEntity.setQuantiteProduit(achatProduitRequest.getQuantiteProduit()); // Définit la quantité du produit acheté
		achatProduitRepository.save(achatProduitEntity); // Enregistre l'achat de produit en base de données
		return "Achat effectué avec succès !"; // Message de confirmation d'achat réussi
	}

	public List<CartResponse> getCardProduitByIdUser(Long idUser) {

		List<String> arrayProduit = achatProduitRepository.getCardProductByIdUser(idUser); // Récupère les produits du panier de l'utilisateur par son ID

		List<CartResponse> cartResponse = new ArrayList<>(); // Crée une liste pour stocker les réponses du panier

		for (int i = 0; i < arrayProduit.size(); i++) {

			String[] parts = arrayProduit.get(i).split(","); // Sépare les informations du produit par des virgules
			CartResponse cart = new CartResponse(); // Crée une nouvelle réponse de panier
			if (!arrayProduit.equals("")) { // Vérifie si le panier n'est pas vide

				cart.getDetailsMapCart().put("idProduit", parts[0]); // Ajoute l'identifiant du produit à la réponse
				cart.getDetailsMapCart().put("imagePathProduit", parts[1]); // Ajoute le chemin de l'image du produit à la réponse
				cart.getDetailsMapCart().put("nomProduit", parts[2]); // Ajoute le nom du produit à la réponse
				cart.getDetailsMapCart().put("prixProduit", parts[3]); // Ajoute le prix du produit à la réponse
				cart.getDetailsMapCart().put("quantiteProduit", parts[4]); // Ajoute la quantité du produit à la réponse
				cart.getDetailsMapCart().put("sousPrixProduit", parts[5]); // Ajoute le sous-total du produit (quantité * prix) à la réponse
			}
			cartResponse.add(cart); // Ajoute la réponse de panier à la liste
		}
		if (cartResponse.size() > 0) { // Vérifie si la liste de réponses n'est pas vide
			return cartResponse; // Renvoie la liste des réponses du panier
		}
		return null; // Renvoie null si le panier est vide
	}

	public String mostPopularProduct() {
		return achatProduitRepository.mostPopularProduct("produits"); // Récupère le produit le plus populaire de la catégorie "produits"
	}
	
	public void deleteAchatProduct(long idUser , long idProduit) {
		Optional<UserEntity> userEntity = userRepository.findById(idUser);
	    List<AchatProduitEntity> listAchat = achatProduitRepository.findByUser(userEntity);
	    for (AchatProduitEntity ap : listAchat) {
	    	if(ap.isStatusAchatProduit() && ap.getProduit().getIdProduit()==idProduit) {
	    		achatProduitRepository.delete(ap);
	    	}
	        
	    }
	}
}
