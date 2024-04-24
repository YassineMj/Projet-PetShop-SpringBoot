package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.AchatPetEntity;
import com.example.demo.entities.PetEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.repositories.AchatPetRepository;
import com.example.demo.repositories.PetRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.requests.AchatPetRequest;

@Service
public class AchatPetService {
	@Autowired
	AchatPetRepository achatPetRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PetRepository petRepository;

	public void achatPet(AchatPetRequest achatPetRequest) {
		PetEntity pet = petRepository.findByIdPet(achatPetRequest.getIdPet())
				.orElseThrow(() -> new IllegalArgumentException("Pet non trouvé"));
		UserEntity user = userRepository.findByIdUser(achatPetRequest.getIdUser())
				.orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));
		AchatPetEntity achatPetEntity = new AchatPetEntity();
		achatPetEntity.setUser(user);
		achatPetEntity.setPet(pet);
		achatPetEntity.setQuantitePet(achatPetRequest.getQuantitePet());
		achatPetRepository.save(achatPetEntity);

	}
}
