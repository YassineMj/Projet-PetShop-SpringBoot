package com.example.demo.controllers;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.PetEntity;
import com.example.demo.requests.AchatPetRequest;
import com.example.demo.services.AchatPetService;

@CrossOrigin(origins = "http://localhost:4200") // Autorise les requêtes cross-origin depuis http://localhost:4200
@RestController // Indique que cette classe est un contrôleur REST
@RequestMapping("Petshop/api/achatpet") // Préfixe d'URL pour toutes les méthodes de ce contrôleur
public class AchatPetController {

	@Autowired
	AchatPetService achatPetService; // Injection de dépendance du service AchatPetService

	@PostMapping("/card-achat-pet") // Requête POST pour ajouter un animal au panier
	public ResponseEntity<Map<String, String>> achatPet(@RequestBody AchatPetRequest achatPetRequest) {
		try {
			achatPetService.achatPet(achatPetRequest); // Délégation de l'ajout de l'animal au panier au service AchatPetService
			return ResponseEntity.status(HttpStatus.CREATED) // Requête traitée avec succès - code 201 (CREATED)
					.body(Collections.singletonMap("Message", "Achat de pet effectué avec succès.")); // Message de confirmation d'ajout au panier
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST) // Requête invalide - code 400 (BAD_REQUEST)
					.body(Collections.singletonMap("Message", e.getMessage())); // Message d'erreur avec details de l'exception
		}
	}

    @GetMapping("/most-popular-pet-chat") // Requête GET pour récupérer le chat le plus populaire
    public ResponseEntity<?> mostPopularPetChat() {
        Map<String, Object> response = new HashMap<>(); // Map pour stocker les informations du chat le plus populaire
        String mostPopularPet = achatPetService.mostPopularChat(); // Récupérer le chat le plus populaire via le service

        if (mostPopularPet != null) {
            String[] petInfo = mostPopularPet.split(","); // Diviser la chaîne de caractères en tableau d'informations sur l'animal
            response.put("id", petInfo[0]); // Ajouter l'ID de l'animal au Map
            response.put("name", petInfo[1]); // Ajouter le nom de l'animal au Map
            response.put("categorie", petInfo[2]); // Ajouter la catégorie de l'animal au Map (chat dans ce cas)
            response.put("quantite", Integer.parseInt(petInfo[3])); // Ajouter la quantité de l'animal au Map (conversion en entier)
            response.put("description", petInfo[4]); // Ajouter la description de l'animal au Map
            response.put("imageUrl", petInfo[5]); // Ajouter l'URL de l'image de l'animal au Map
            return ResponseEntity.ok(response); // Requête traitée avec succès - code 200 (OK) et informations de l'animal dans le corps de la réponse
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucun animal populaire trouvé"); // Requête non trouvée - code 404 (NOT FOUND) avec message plus explicite
        }
    }


    @GetMapping("/most-popular-pet-chien") // Requête GET pour récupérer le chien le plus populaire
    public ResponseEntity<?> mostPopularPetChien() {
        Map<String, Object> response = new HashMap<>(); // Map pour stocker les informations du chien le plus populaire
        String mostPopularPet = achatPetService.mostPopularChien(); // Récupérer le chien le plus populaire via le service

        if (mostPopularPet != null) {
            String[] petInfo = mostPopularPet.split(","); // Diviser la chaîne de caractères en tableau d'informations sur l'animal
            response.put("id", petInfo[0]); // Ajouter l'ID de l'animal au Map
            response.put("name", petInfo[1]); // Ajouter le nom de l'animal au Map
            response.put("categorie", petInfo[2]); // Ajouter la catégorie de l'animal au Map (chien dans ce cas)
            response.put("quantite", Integer.parseInt(petInfo[3])); // Ajouter la quantité de l'animal au Map (conversion en entier)
            response.put("description", petInfo[4]); // Ajouter la description de l'animal au Map
            response.put("imageUrl", petInfo[5]); // Ajouter l'URL de l'image de l'animal au Map
            return ResponseEntity.ok(response); // Requête traitée avec succès - code 200

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No popular pet found");
        }
    }
    
    @DeleteMapping("/delete-achat-pet/{idUser}/{idPet}")
    public ResponseEntity<Map<String,String>> deleteAchatPet(@PathVariable long idUser , @PathVariable long idPet) {
        achatPetService.deleteAchatPet(idUser,idPet);
        return ResponseEntity.status(HttpStatus.CREATED)
        		.body(Collections.singletonMap("Message","Achats supprimés avec succès pour l'utilisateur avec l'id " + idUser));
    }

}
