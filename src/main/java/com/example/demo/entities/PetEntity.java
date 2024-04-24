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
public class PetEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name ="idPet")
	private Long idPet;
	
	@Column(name ="nomPet")
	private String nomPet;
	
	@Column(name ="descriptionPet")
	private String descriptionPet;
	
	@Column(name ="prixPet")
	private Long prixPet;
	
	@Column(name ="special")
	private boolean special;
	
	@Column(name ="imagePet")
	private String imagePet;
	
	@Column(name ="promotion")
	private boolean promotion;
	
	@ManyToOne
	    @JoinColumn(name = "fk_idCategorie")
	private CategorieEntity categorie;
	

}
