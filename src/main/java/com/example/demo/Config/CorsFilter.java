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

@Component
@WebFilter(urlPatterns = "/Petshop/api/*")
public class CorsFilter implements Filter {

	 @Override
	    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	            throws IOException, ServletException {
	        HttpServletResponse httpResponse = (HttpServletResponse) response;
	        httpResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
	        httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
	        httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type");
	        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
	        chain.doFilter(request, response);
	    }

	    @Override
	    public void init(FilterConfig filterConfig) throws ServletException {
	    }

	    @Override
	    public void destroy() {
	    }
}
