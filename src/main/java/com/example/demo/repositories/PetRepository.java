package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.PetEntity;

public interface PetRepository extends JpaRepository<PetEntity, Long> {

	List<PetEntity> findTop3ByCategorieNomCategorieOrderByIdPetDesc(String nomCategorie);

	List<PetEntity> findByCategorieNomCategorieAndSpecialTrue(String nomCategorie);

	List<PetEntity> findByCategorieNomCategorie(String nomCategorie);

	Long countByCategorieNomCategorie(String nomCategorie);

	Optional<PetEntity> findByIdPet(Long idPet);
}
