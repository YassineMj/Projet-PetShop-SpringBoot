package com.example.demo.controllers;

import java.util.Collections;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.repositories.UserRepository;
import com.example.demo.requests.UserLoginRequest;
import com.example.demo.requests.UserRequest;
import com.example.demo.services.UserService;

import jakarta.websocket.server.PathParam;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("Petshop/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/sign-up-user")
    public ResponseEntity<Map<String, String>> singUpUser(@RequestBody UserRequest userRequest) {
        try {
            if (userRepository.existsByUserEmail(userRequest.getUserEmail()) == true) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Collections.singletonMap("error", "L'e-mail existe déjà."));
            }
            userService.saveUser(userRequest);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Collections.singletonMap("message", "Utilisateur enregistré avec succès"));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    Collections.singletonMap("error", "Erreur lors de l'ajout du consultant : " + e.getMessage()));

        }

    }

    @PostMapping("/login-user")
    public ResponseEntity<String> loginUser(@RequestBody UserLoginRequest userLoginRequest) {
        if (userService.loginUser(userLoginRequest) != null) {
            return ResponseEntity.ok("authantification reussie! ");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("authantification echoué !!!!");
        }
    }

    @PutMapping("/update-user/{iduser}")
    public ResponseEntity<String> updateUser(@PathVariable Long iduser, @RequestBody UserRequest userRequest) {
        userService.updateUser(iduser, userRequest);
        return ResponseEntity.ok("update reussie!");

    }
}