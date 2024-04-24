package com.example.demo.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.example.demo.responses.CartPetResponse;

@Service
public class AchatPetService {
	@Autowired
	AchatPetRepository achatPetRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PetRepository petRepository;

	public String achatPet(AchatPetRequest achatPetRequest) {
		PetEntity pet = petRepository.findByIdPet(achatPetRequest.getIdPet())
				.orElseThrow(() -> new IllegalArgumentException("Pet non trouvé"));
		UserEntity user = userRepository.findByIdUser(achatPetRequest.getIdUser())
				.orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));
		AchatPetEntity achatPetEntity = new AchatPetEntity();
		achatPetEntity.setUser(user);
		achatPetEntity.setPet(pet);
		achatPetEntity.setQuantitePet(achatPetRequest.getQuantitePet());
		achatPetRepository.save(achatPetEntity);
		return "achat ajouté";
	}

	public ResponseEntity<?> getCartPetByIdUser(Long idUser) {
        List<String>arrayPet=achatPetRepository.getCartPetByIdUser(idUser);
        
        List<CartPetResponse> cartPetResponse=new ArrayList<>();
        
        for(int i=0;i<arrayPet.size();i++) {
        	
        	String[] parts = arrayPet.get(i).split(",");
        	CartPetResponse cart = new CartPetResponse();
            if (arrayPet.equals("")==false) {
            	
            	cart.getDetailsMapCart().put("idAchatPet", parts[0]);
            	cart.getDetailsMapCart().put("imagePathPet", parts[1]);
            	cart.getDetailsMapCart().put("nomPet", parts[2]);
            	cart.getDetailsMapCart().put("prixPet", parts[3]);
            	cart.getDetailsMapCart().put("qunatitePet", parts[4]);
            	cart.getDetailsMapCart().put("sousPrixPet", parts[5]);

            }
            cartPetResponse.add(cart);
        } 
        if(cartPetResponse.size()>0)
        {
        	return ResponseEntity.ok(cartPetResponse);
        }
        return ResponseEntity.notFound().build();
    }
}
