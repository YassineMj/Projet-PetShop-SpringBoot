package com.example.demo.responses;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class CartResponse {
	private Map<String, String> detailsMapCart = new HashMap<>();
}
