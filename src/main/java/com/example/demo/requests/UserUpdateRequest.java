package com.example.demo.requests;

import lombok.Data;

@Data
public class UserUpdateRequest {
	private String nom;
	private String email;
	private String telephone;
	private String adresse;
	private String motDePasse;

}
