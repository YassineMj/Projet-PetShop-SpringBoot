package com.example.demo.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AchatPetRequest {
	private Long idUser;
	private Long idPet;
	private int quantitePet;
}
