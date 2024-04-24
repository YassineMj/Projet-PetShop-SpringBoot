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
public class CartEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCart")
	private Long idCart;

	@ManyToOne
	@JoinColumn(name = "fk_idUser")
	private UserEntity user;

	@ManyToOne
	@JoinColumn(name = "fk_idPet")
	private PetEntity pet;

	@ManyToOne
	@JoinColumn(name = "fk_idProduit")
	private ProduitEntity produit;

	@Column(name = "quantiteTotal")
	private String quantiteTotal;

}
