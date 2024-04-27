package com.example.demo.Config;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

@Component // Indique que cette classe est un composant Spring
@WebFilter(urlPatterns = "/Petshop/api/*") // Filtre les requêtes commençant par "/Petshop/api/*"
public class CorsFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse httpResponse = (HttpServletResponse) response; // Conversion de la réponse en HttpServletResponse

		// Configuration des entêtes CORS pour autoriser les requêtes cross-origin
		httpResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:4200"); // Origine autorisée
		httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE"); // Méthodes autorisées
		httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type"); // Entête autorisé
		httpResponse.setHeader("Access-Control-Allow-Credentials", "true"); // Autoriser les requêtes avec des identifiants

		chain.doFilter(request, response); // Passage de la requête au filtre suivant
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}

