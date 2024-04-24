package com.example.demo.controllers;

import java.util.List;

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
	
	
	@GetMapping("/get-all-pet")
	public List<PetEntity> getAllPets(){
		return petService.getAllPets();
	}
	
	@GetMapping("/get-all-cat")
	public List<PetEntity> getAllCats(){
		return petService.getAllCats();
	}
	@GetMapping("/get-all-dog")
	public List<PetEntity> getAllDogs(){
		return petService.getAllDogs();
	}
	
	@GetMapping("/get-new-cat")
	public PetEntity getNewCat() {
		return petService.getNewCatPet();
	}
	
	@GetMapping("/get-new-dog")
	public PetEntity getNewDog() {
		return petService.getNewDogPet();
		}
	
	@GetMapping("/get-threelastet-cat")
	public List<PetEntity> getThreeLastetCatPet(){
		return petService.getThreeLastetCatPet();
	}
	
	@GetMapping("/get-threelastet-dog")
	public List<PetEntity> getThreeLastetDogPet(){
		return petService.getThreeLastetDogPet();
	}
	
	@GetMapping("/get-all-special-cat")
		public List<PetEntity> getAllSpecialCat(){
			return petService.getAllSpecialCatPet();
		}
	
	@GetMapping("/get-all-special-dog")
	public List<PetEntity> getAllSpecialDog(){
		return petService.getAllSpecialDogPet();
	}
	
	@GetMapping("get-count-cat")
	public Long getCountCats() {
		long countcat = petService.getCountCats();
		return countcat;
	}
	@GetMapping("get-count-dog")
	public Long getCountDogs() {
		long countchien = petService.getCountDogs();
		return countchien;
	}
	
	  
}
