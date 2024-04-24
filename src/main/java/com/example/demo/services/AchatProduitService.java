package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.AchatProduitEntity;
import com.example.demo.entities.ProduitEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.repositories.AchatProduitRepository;
import com.example.demo.repositories.ProduitRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.requests.AchatProduitRequest;

@Service
public class AchatProduitService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	AchatProduitRepository achatProduitRepository;
	@Autowired
	ProduitRepository produitRepository;

	public void achatProduit(AchatProduitRequest achatProduitRequest) {
		ProduitEntity produit = produitRepository.findByIdProduit(achatProduitRequest.getIdProduit()).orElseThrow(()->new IllegalArgumentException("Produit non trouvé"));
		UserEntity user = userRepository.findByIdUser(achatProduitRequest.getIdUser()).orElseThrow(()->new IllegalArgumentException("user non trouvé"));
		AchatProduitEntity achatProduitEntity = new AchatProduitEntity();
		achatProduitEntity.setUser(user);
		achatProduitEntity.setProduit(produit);
		achatProduitEntity.setQuantiteProduit(achatProduitRequest.getQuantiteProduit());
		achatProduitRepository.save(achatProduitEntity);

	}
	
}
