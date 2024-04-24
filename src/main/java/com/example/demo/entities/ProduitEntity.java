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
public class ProduitEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name ="idProduit")
	private Long idProduit;
	
	@Column(name ="nomProduit")
	private String nomProduit;
	
	@Column(name ="descriptionProduit")
	private String descriptionProduit;
	
	@Column(name ="prixProduit")
	private Long prixProduit;
	
	
	@Column(name ="imageProduit")
	private String imageProduit;
	
	@Column(name ="promotion")
	private boolean promotion;
	
	@ManyToOne
	    @JoinColumn(name = "fk_idCategorie")
	private CategorieEntity categorie;

}
