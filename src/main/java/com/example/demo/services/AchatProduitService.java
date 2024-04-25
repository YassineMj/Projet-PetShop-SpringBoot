package com.example.demo.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	UserRepository userRepository;
	@Autowired
	AchatProduitRepository achatProduitRepository;
	@Autowired
	ProduitRepository produitRepository;

	public String achatProduit(AchatProduitRequest achatProduitRequest) {
		ProduitEntity produit = produitRepository.findByIdProduit(achatProduitRequest.getIdProduit())
				.orElseThrow(() -> new IllegalArgumentException("Produit non trouvé"));
		UserEntity user = userRepository.findByIdUser(achatProduitRequest.getIdUser())
				.orElseThrow(() -> new IllegalArgumentException("user non trouvé"));
		AchatProduitEntity achatProduitEntity = new AchatProduitEntity();
		achatProduitEntity.setUser(user);
		achatProduitEntity.setProduit(produit);
		achatProduitEntity.setQuantiteProduit(achatProduitRequest.getQuantiteProduit());
		achatProduitRepository.save(achatProduitEntity);
		return "achatproduit reussie !";
	}

	public List<CartResponse> getCardProduitByIdUser(Long idUser) {

		List<String> arrayProduit = achatProduitRepository.getCardProductByIdUser(idUser);

		List<CartResponse> cartResponse = new ArrayList<>();

		for (int i = 0; i < arrayProduit.size(); i++) {

			String[] parts = arrayProduit.get(i).split(",");
			CartResponse cart = new CartResponse();
			if (arrayProduit.equals("") == false) {

				cart.getDetailsMapCart().put("idProduit", parts[0]);
				cart.getDetailsMapCart().put("imagePathProduit", parts[1]);
				cart.getDetailsMapCart().put("nomProduit", parts[2]);
				cart.getDetailsMapCart().put("prixProduit", parts[3]);
				cart.getDetailsMapCart().put("quantiteProduit", parts[4]);
				cart.getDetailsMapCart().put("sousPrixProduit", parts[5]);
			}
			cartResponse.add(cart);
		}
		if (cartResponse.size() > 0) {
			return cartResponse;
		}
		return null;
	}

}
