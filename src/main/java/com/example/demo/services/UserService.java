package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.UserEntity;
import com.example.demo.repositories.UserRepository;
import com.example.demo.requests.UserLoginRequest;
import com.example.demo.requests.UserSinscrireRequest;
import com.example.demo.requests.UserUpdateRequest;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository; // Injection de dépendance du repository UserRepository

	// Méthode pour enregistrer un nouvel utilisateur
	public void saveUser(UserSinscrireRequest userSinscrireRequest) {
		// Créer une nouvelle instance d'UserEntity avec les données de la demande
		UserEntity userEntity = new UserEntity();
		// Setter les valeurs de l'utilisateur avec les données de la demande
		userEntity.setUserNom(userSinscrireRequest.getUserNom());
		userEntity.setUserMotDePasse(userSinscrireRequest.getUserMotDePasse());
		userEntity.setUserEmail(userSinscrireRequest.getUserEmail());
		userEntity.setUserAdresse(userSinscrireRequest.getUserAdresse());
		userEntity.setUserTelephone(userSinscrireRequest.getUserTelephone());
		userEntity.setUserNumCart(userSinscrireRequest.getUserNumCart());
		userEntity.setUserCvc(userSinscrireRequest.getUserCvc());
		userEntity.setAnneeExpCart(userSinscrireRequest.getAnneeExpCart());
		userEntity.setMoisExpCart(userSinscrireRequest.getMoisExpCart());
		userEntity.setSourceClientIdStripe(userSinscrireRequest.getSourceClientIdStripe());
		userEntity.setSourceCartIdStrip(userSinscrireRequest.getSourceCartIdStrip());
		// Enregistrer l'utilisateur dans la base de données
		userRepository.save(userEntity);
	}

	// Méthode pour authentifier un utilisateur
	public UserEntity loginUser(UserLoginRequest userLoginRequest) {
		// Rechercher l'utilisateur par son e-mail dans la base de données
		UserEntity user = userRepository.findByUserEmail(userLoginRequest.getUserEmail());
		// Vérifier si l'utilisateur existe et si le mot de passe correspond
		if (user != null && user.getUserMotDePasse().equals(userLoginRequest.getUserMotDePasse())) {
			return user; // Retourner l'utilisateur authentifié
		}
		return null; // Authentification échouée
	}

	// Méthode pour mettre à jour les informations de l'utilisateur
	public UserEntity updateUser(Long idUser, UserUpdateRequest userUpdateRequest) {
		// Rechercher l'utilisateur par son ID dans la base de données
		Optional<UserEntity> optionalUser = userRepository.findByIdUser(idUser);
		if (optionalUser.isPresent()) {
			// Si l'utilisateur est trouvé, mettre à jour ses informations avec les données
			// de la demande
			UserEntity user = optionalUser.get();
			user.setUserNom(userUpdateRequest.getNom());
			user.setUserEmail(userUpdateRequest.getEmail());
			user.setUserAdresse(userUpdateRequest.getAdresse());
			user.setUserMotDePasse(userUpdateRequest.getMotDePasse());
			user.setUserTelephone(userUpdateRequest.getTelephone());

			// Enregistrer les modifications dans la base de données et retourner
			// l'utilisateur mis à jour
			return userRepository.save(user);
		}
		return null; // Gérer le cas où l'utilisateur n'est pas trouvé
	}

	public long getCountUser() {
		return userRepository.count();
	}
}
