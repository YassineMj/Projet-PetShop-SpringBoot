package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	boolean existsByUserEmail(String userEmail);

	UserEntity findByUserEmail(String userEmail);

	Optional<UserEntity> findByIdUser(Long idUser);

}
