package com.example.demo.requests;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserSinscrireRequest {

	private String userNom;
	private String userEmail;
	private String userMotDePasse;
	private String userTelephone;
	private String userAdresse;
	private String userNumCart;
	private String userCvc;
	private String moisExpCart;
	private String anneeExpCart;
	private String sourceClientIdStripe;
	private String sourceCartIdStrip;

}
