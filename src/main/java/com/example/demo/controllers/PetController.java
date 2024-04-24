package com.example.demo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.PetEntity;
import com.example.demo.services.PetService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("Petshop/api/pet")

public class PetController {

	@Autowired
	PetService petService;

	/*
	 * @GetMapping("/get-all-pet") public List<PetEntity> getAllPets() { return
	 * petService.getAllPets(); }
	 */

	@GetMapping("/get-all-cats")
	public List<PetEntity> getAllCats() {
		return petService.getAllCats();
	}

	@GetMapping("/get-all-dogs")
	public List<PetEntity> getAllDogs() {
		return petService.getAllDogs();
	}

	/*
	 * @GetMapping("/get-new-cat") public PetEntity getNewCat() { return
	 * petService.getNewCatPet(); }
	 * 
	 * @GetMapping("/get-new-dog") public PetEntity getNewDog() { return
	 * petService.getNewDogPet(); }
	 */

	@GetMapping("/get-threelastet-cats")
	public List<PetEntity> getThreeLastetCatPet() {
		return petService.getThreeLastetCatPet();
	}

	@GetMapping("/get-threelastet-dogs")
	public List<PetEntity> getThreeLastetDogPet() {
		return petService.getThreeLastetDogPet();
	}

	@GetMapping("/get-all-special-cats")
	public List<PetEntity> getAllSpecialCat() {
		return petService.getAllSpecialCatPet();
	}

	@GetMapping("/get-all-special-dogs")
	public List<PetEntity> getAllSpecialDog() {
		return petService.getAllSpecialDogPet();
	}

	@GetMapping("get-count-cats")
	public ResponseEntity<Map<String, Long>> getCountCats() {
		// Appeler le service pour obtenir le nombre de chats
		long countCats = petService.getCountCats();

		// Créer un map pour stocker le résultat
		Map<String, Long> resultMap = new HashMap<>();
		resultMap.put("countCats", countCats);

		// Retourner le résultat dans ResponseEntity
		return ResponseEntity.ok(resultMap);
	}

	@GetMapping("get-count-dogs")
	public ResponseEntity<Map<String, Long>> getCountDogs() {
		// Appeler le service pour obtenir le nombre de chiens
		long countDogs = petService.getCountDogs();

		// Créer un map pour stocker le résultat
		Map<String, Long> resultMap = new HashMap<>();
		resultMap.put("countDogs", countDogs);

		// Retourner le résultat dans ResponseEntity
		return ResponseEntity.ok(resultMap);
	}

}
