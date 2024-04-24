package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.UserEntity;
import com.example.demo.repositories.UserRepository;
import com.example.demo.requests.UserLoginRequest;
import com.example.demo.requests.UserRequest;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public void saveUser(UserRequest userRequest) {
		UserEntity userEntity = new UserEntity();
		userEntity.setUserNom(userRequest.getUserNom());
		userEntity.setUserMotDePasse(userRequest.getUserMotDePasse());
		userEntity.setUserEmail(userRequest.getUserEmail());
		userEntity.setUserAdresse(userRequest.getUserAdresse());
		userEntity.setUserTelephone(userRequest.getUserTelephone());
		userEntity.setUserEntreprise(userRequest.getUserEntreprise());
		userEntity.setUserNumCart(userRequest.getUserNumCart());
		userEntity.setUserCvc(userRequest.getUserCvc());
		userEntity.setAnneeExpCart(userRequest.getAnneeExpCart());
		userEntity.setMoisExpCart(userRequest.getMoisExpCart());
		
		userRepository.save(userEntity);
	}
	public boolean loginUser(UserLoginRequest userLoginRequest) {
		UserEntity user = userRepository.findByUserEmail(userLoginRequest.getUserEmail());
		if(user != null && user.getUserMotDePasse().equals(userLoginRequest.getUserMotDePasse())){
			return true;
			
		}
			return false;
	}
	public void updateUser(Long idUser, UserRequest userRequest) {
		Optional<UserEntity> optionalUser = userRepository.findByIdUser(idUser);
		  if(optionalUser.isPresent()) {
			  UserEntity user = optionalUser.get();
			  user.setUserNom(userRequest.getUserNom());
			  user.setUserEmail(userRequest.getUserEmail());
			  user.setUserAdresse(userRequest.getUserAdresse());
			  user.setUserMotDePasse(userRequest.getUserMotDePasse());
			  user.setUserEntreprise(userRequest.getUserEntreprise());
			  userRepository.save(user);
		}
		  
	}
}
