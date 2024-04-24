package com.example.demo.responses;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class CartPetResponse {
	private  Map<String, String> detailsMapCart = new HashMap<>();
}
