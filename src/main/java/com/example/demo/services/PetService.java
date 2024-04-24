package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.PetEntity;
import com.example.demo.repositories.PetRepository;

@Service
public class PetService {
	
	@Autowired
	PetRepository petRepository;
	
	public List<PetEntity> getAllPets(){
		return petRepository.findAll();
	}
	public List<PetEntity> getAllCats(){
		return petRepository.findByCategorieNomCategorie("chats");
	}
	public List<PetEntity> getAllDogs(){
		return petRepository.findByCategorieNomCategorie("chiens");
	}
	
	public PetEntity getNewCatPet() {
		return petRepository.findFirstByCategorieNomCategorieOrderByIdPetDesc("chats");
	}
	public PetEntity getNewDogPet() {
		return petRepository.findFirstByCategorieNomCategorieOrderByIdPetDesc("chiens");
	}
	public List<PetEntity> getThreeLastetCatPet(){
		return petRepository.findTop3ByCategorieNomCategorieOrderByIdPetDesc("chats");
	}
	public List<PetEntity> getThreeLastetDogPet(){
		return petRepository.findTop3ByCategorieNomCategorieOrderByIdPetDesc("chiens");
	}
	public List<PetEntity> getAllSpecialCatPet(){
		return petRepository.findByCategorieNomCategorieAndSpecialTrue("chats");
	}
	public List<PetEntity> getAllSpecialDogPet(){
		return petRepository.findByCategorieNomCategorieAndSpecialTrue("chiens");
	}
	public Long getCountCats() {
		return petRepository.countByCategorieNomCategorie("chats");
	}
	public Long getCountDogs() {
		return petRepository.countByCategorieNomCategorie("chiens");
	}
}
