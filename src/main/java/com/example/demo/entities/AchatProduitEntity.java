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
public class AchatProduitEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idAchatProduit")
	private Long idAchatProduit;

	@ManyToOne
	@JoinColumn(name = "fk_idUser")
	private UserEntity user;

	@ManyToOne
	@JoinColumn(name = "fk_idProduit")
	private ProduitEntity Produit;

	@JoinColumn(name = "quantiteProduit")
	private int quantiteProduit;

	@Column(name = "statusAchatProduit")
	private boolean statusAchatProduit = true;

}
