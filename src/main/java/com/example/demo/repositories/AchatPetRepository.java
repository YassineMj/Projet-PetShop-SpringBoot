package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.AchatPetEntity;
import com.example.demo.entities.PetEntity;
import com.example.demo.entities.UserEntity;

import jakarta.transaction.Transactional;

public interface AchatPetRepository extends JpaRepository<AchatPetEntity, Long> {
	@Query(value = "SELECT ap.fk_id_pet , p.image_pet , p.nom_pet , p.prix_pet , sum(ap.quantite_pet) , p.prix_pet*sum(ap.quantite_pet) FROM achat_pet_entity ap inner JOIN pet_entity p on ap.fk_id_pet = p.id_pet\r\n"
			+ "WHERE ap.fk_id_user=:userId && ap.status_achat_pet is true\r\n"
			+ "GROUP by ap.fk_id_pet", nativeQuery = true)
	List<String> getCardPetByIdUser(@Param("userId") Long userId);

	@Query(value = "SELECT ap.fk_id_pet, p.nom_pet, c.nom_categorie, p.prix_pet, p.description_pet, p.image_pet "
			+ "FROM achat_pet_entity ap " + "INNER JOIN pet_entity p ON ap.fk_id_pet = p.id_pet "
			+ "INNER JOIN categorie_entity c ON p.fk_id_categorie = c.id_categorie "
			+ "WHERE c.nom_categorie = :categoriePet " + "GROUP BY ap.fk_id_pet "
			+ "ORDER BY SUM(ap.quantite_pet) DESC LIMIT 1", nativeQuery = true)
	String mostPopularPet(@Param("categoriePet") String categoriePet);

	List<AchatPetEntity> findByUser(Optional<UserEntity> userEntity);
	
	@Query(value = "SELECT ap.id_achat_pet FROM achat_pet_entity ap where ap.fk_id_user=:idUser ", nativeQuery = true)
	List<String> findAllByIdUser(@Param("idUser") long idUser);
}
