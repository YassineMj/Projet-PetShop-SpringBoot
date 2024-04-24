package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idUser")
	private Long idUser;

	@Column(name = "userNom")
	private String userNom;

	@Column(name = "userEmail")
	private String userEmail;

	@Column(name = "userMotDePasse")
	private String userMotDePasse;

	@Column(name = "userTelephone")
	private String userTelephone;

	@Column(name = "userAdresse")
	private String userAdresse;

	@Column(name = "userEntreprise")
	private String userEntreprise;

	@Column(name = "userNumCart")
	private String userNumCart;

	@Column(name = "userCvc")
	private String userCvc;

	@Column(name = "moisExpCart")
	private String moisExpCart;

	@Column(name = "anneeExpCart")
	private String anneeExpCart;

}
