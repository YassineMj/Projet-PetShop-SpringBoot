package com.example.demo.requests;

import lombok.Data;

@Data
public class PaiementRequest {
	
	private long idUser;
	
	private int total;
		
	private String sourceClientIdStripe;
	
	private String sourceCartIdStrip;
}
