package com.example.demo.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AchatProduitRequest {
	private Long idUser;
	private Long idProduit;
	private String quantiteProduit;
}
