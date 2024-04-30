package com.example.demo.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entities.AchatPetEntity;
import com.example.demo.entities.PetEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.repositories.AchatPetRepository;
import com.example.demo.repositories.PetRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.requests.AchatPetRequest;
import com.example.demo.responses.CartResponse;

@Service
public class AchatPetService {

	@Autowired
	AchatPetRepository achatPetRepository; // Injection de dépendance du repository AchatPetRepository

	@Autowired
	UserRepository userRepository; // Injection de dépendance du repository UserRepository

	@Autowired
	PetRepository petRepository; // Injection de dépendance du repository PetRepository

	public String achatPet(AchatPetRequest achatPetRequest) {
		PetEntity pet = petRepository.findByIdPet(achatPetRequest.getIdPet())
				.orElseThrow(() -> new IllegalArgumentException("Animal non trouvé")); // Recherche l'animal par son ID et lève une exception si introuvable
		UserEntity user = userRepository.findByIdUser(achatPetRequest.getIdUser())
				.orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé")); // Recherche l'utilisateur par son ID et lève une exception si introuvable
		AchatPetEntity achatPetEntity = new AchatPetEntity(); // Crée une nouvelle entité d'achat d'animal
		achatPetEntity.setUser(user); // Associe l'utilisateur à l'achat
		achatPetEntity.setPet(pet); // Associe l'animal à l'achat
		achatPetEntity.setQuantitePet(achatPetRequest.getQuantitePet()); // Définit la quantité de l'animal acheté
		achatPetRepository.save(achatPetEntity); // Enregistre l'achat d'animal en base de données
		return "Achat ajouté"; // Message de confirmation d'ajout d'achat
	}

	public List<CartResponse> getCardPetByIdUser(Long idUser) {

		List<String> arrayPet = achatPetRepository.getCardPetByIdUser(idUser); // Récupère les animaux du panier de l'utilisateur par son ID

		List<CartResponse> cartResponse = new ArrayList<>(); // Crée une liste pour stocker les réponses du panier

		for (int i = 0; i < arrayPet.size(); i++) {

			String[] parts = arrayPet.get(i).split(","); // Sépare les informations de l'animal par des virgules
			CartResponse cart = new CartResponse(); // Crée une nouvelle réponse de panier
			if (!arrayPet.equals("")) { // Vérifie si le panier n'est pas vide

				cart.getDetailsMapCart().put("idPet", parts[0]); // Ajoute l'identifiant de l'animal à la réponse
				cart.getDetailsMapCart().put("imagePathPet", parts[1]); // Ajoute le chemin de l'image de l'animal à la réponse
				cart.getDetailsMapCart().put("nomPet", parts[2]); // Ajoute le nom de l'animal à la réponse
				cart.getDetailsMapCart().put("prixPet", parts[3]); // Ajoute le prix de l'animal à la réponse
				cart.getDetailsMapCart().put("quantitePet", parts[4]); // Ajoute la quantité de l'animal à la réponse
				cart.getDetailsMapCart().put("sousPrixPet", parts[5]); // Ajoute le sous-total de l'animal (quantité * prix) à la réponse
			}
			cartResponse.add(cart); // Ajoute la réponse de panier à la liste
		}
		if (cartResponse.size() > 0) { // Vérifie si la liste de réponses n'est pas vide
			return cartResponse; // Renvoie la liste des réponses du panier
		}
		return null; // Renvoie null si le panier est vide
	}

	public String mostPopularChien() {
		return achatPetRepository.mostPopularPet("chiens"); // Récupère l'animal le plus populaire de la catégorie "chiens"
	}

	public String mostPopularChat() {
		return achatPetRepository.mostPopularPet("chats"); // Récupère l'animal le plus populaire de la catégorie "chats"
	}
	
	public void deleteAchatPet(long idUser , long idPet) {
		Optional<UserEntity> userEntity = userRepository.findById(idUser);
		
		List<AchatPetEntity> listAchat=achatPetRepository.findByUser(userEntity);
		for(AchatPetEntity ap :listAchat) {
			if(ap.isStatusAchatPet() && ap.getPet().getIdPet()==idPet) {
				achatPetRepository.delete(ap);
			}
		}
    }
}