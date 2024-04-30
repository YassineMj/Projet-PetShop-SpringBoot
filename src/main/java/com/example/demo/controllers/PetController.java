package com.example.demo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.PetEntity;
import com.example.demo.services.PetService;

@CrossOrigin(origins = "http://localhost:4200") // Autorise les requêtes cross-origin depuis http://localhost:4200
@RestController // Indique que cette classe est un contrôleur REST
@RequestMapping("Petshop/api/pet") // Préfixe d'URL pour toutes les méthodes de ce contrôleur
public class PetController {

	@Autowired
	PetService petService; // Injection de dépendance du service PetService

	@GetMapping("/get-pet/{idPet}")
	public Optional<PetEntity> getPet(@PathVariable Long idPet) {
		return petService.getPet(idPet); 
	}
	
	@GetMapping("/get-all-cats") // Requête GET pour récupérer tous les chats
	public List<PetEntity> getAllCats() {
		return petService.getAllCats(); // Délégation de la récupération de tous les chats au service PetService
	}

	@GetMapping("/get-all-dogs") // Requête GET pour récupérer tous les chiens
	public List<PetEntity> getAllDogs() {
		return petService.getAllDogs(); // Délégation de la récupération de tous les chiens au service PetService
	}

	@GetMapping("/get-threelatest-cats") // Requête GET pour récupérer les 3 derniers chats 
	public List<PetEntity> getThreeLastetCatPet() {
		return petService.getThreeLastetCatPet(); // Délégation de la récupération des 3 derniers chats au service PetService
	}

	@GetMapping("/get-threelatest-dogs") // Requête GET pour récupérer les 3 derniers chiens 
	public List<PetEntity> getThreeLastetDogPet() {
		return petService.getThreeLastetDogPet(); // Délégation de la récupération des 3 derniers chiens au service PetService
	}

	@GetMapping("/get-all-special-cats") // Requête GET pour récupérer tous les chats spéciaux
	public List<PetEntity> getAllSpecialCat() {
		return petService.getAllSpecialCatPet(); // Délégation de la récupération de tous les chats spéciaux au service PetService
	}

	@GetMapping("/get-all-special-dogs") // Requête GET pour récupérer tous les chiens spéciaux
	public List<PetEntity> getAllSpecialDog() {
		return petService.getAllSpecialDogPet(); // Délégation de la récupération de tous les chiens spéciaux au service PetService
	}

	@GetMapping("get-count-cats") // Requête GET pour récupérer le nombre de chats
	public ResponseEntity<Map<String, Long>> getCountCats() {
		// Appeler le service pour obtenir le nombre de chats
		long countCats = petService.getCountCats();

		// Créer un map pour stocker le résultat
		Map<String, Long> resultMap = new HashMap<>();
		resultMap.put("countCats", countCats);

		// Retourner le résultat dans ResponseEntity
		return ResponseEntity.ok(resultMap); // Requête traitée avec succès - code 200 (OK) et résultat dans un Map
	}

	@GetMapping("get-count-dogs") // Requête GET pour récupérer le nombre de chiens
	public ResponseEntity<Map<String, Long>> getCountDogs() {
		// Appeler le service pour obtenir le nombre de chiens
		long countDogs = petService.getCountDogs();

		// Créer un map pour stocker le résultat
		Map<String, Long> resultMap = new HashMap<>();
		resultMap.put("countDogs", countDogs);

		// Retourner le résultat dans ResponseEntity
		return ResponseEntity.ok(resultMap); // Requête traitée avec succès - code 200 (OK) et résultat dans un Map
	}
}
