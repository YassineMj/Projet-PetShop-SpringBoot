package com.example.demo.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRequest {

	private String userNom; 
	private String userEmail;
	private String userMotDePasse;
	private String userTelephone;
	private String userAdresse;
	private String userEntreprise;
	private String userNumCart;
	private String userCvc; 
	private String moisExpCart;
	private String anneeExpCart;
	
	

	
	

}
