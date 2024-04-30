package com.example.demo.repositories;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.AchatProduitEntity;
import com.example.demo.entities.UserEntity;

public interface AchatProduitRepository extends JpaRepository<AchatProduitEntity, Long> {
	@Query(value = "SELECT ap.fk_id_produit, p.image_produit, p.nom_produit, p.prix_produit, SUM(ap.quantite_produit), p.prix_produit * SUM(ap.quantite_produit) "
			+ "FROM achat_produit_entity ap " + "INNER JOIN produit_entity p ON ap.fk_id_produit = p.id_produit "
			+ "WHERE ap.fk_id_user = :idUser AND ap.status_achat_produit = true "
			+ "GROUP BY ap.fk_id_produit", nativeQuery = true)
	List<String> getCardProductByIdUser(@Param("idUser") Long idUser);
	
	@Query(value = "SELECT ap.fk_id_produit, p.nom_produit, c.nom_categorie, p.prix_produit, p.description_produit, p.image_produit "
			+ "FROM achat_produit_entity ap " + "INNER JOIN produit_entity p ON ap.fk_id_produit = p.id_produit "
			+ "INNER JOIN categorie_entity c ON p.fk_id_categorie = c.id_categorie "
			+ "WHERE c.nom_categorie = :categorieproduit " + "GROUP BY ap.fk_id_produit "
			+ "ORDER BY SUM(ap.quantite_produit) DESC LIMIT 1", nativeQuery = true)
	String mostPopularProduct(@Param("categorieproduit") String categorieProduit);

    List<AchatProduitEntity> findByUser(Optional<UserEntity> userEntity);

	@Query(value = "SELECT ap.id_achat_produit FROM achat_produit_entity ap where ap.fk_id_user=:idUser ", nativeQuery = true)
	List<String> findAllByIdUser(@Param("idUser") long idUser);


}
