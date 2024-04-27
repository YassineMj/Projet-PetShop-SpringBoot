package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.PetEntity;
import com.example.demo.entities.ProduitEntity;

public interface ProduitRepository extends JpaRepository<ProduitEntity, Long> {

	List<ProduitEntity> findTop3ByCategorieNomCategorieOrderByIdProduitDesc(String nomCategorie);

	Long countByCategorieNomCategorie(String nomCategorie);

	Optional<ProduitEntity> findByIdProduit(Long idProduit);

}
