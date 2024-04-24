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
public class AchatPetEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idAchatPet")
	private Long idAchatPet;

	@ManyToOne
	@JoinColumn(name = "fk_idUser")
	private UserEntity user;

	@ManyToOne
	@JoinColumn(name = "fk_idPet")
	private PetEntity pet;

	@JoinColumn(name = "quantitePet")
	private String quantitePet;

}
