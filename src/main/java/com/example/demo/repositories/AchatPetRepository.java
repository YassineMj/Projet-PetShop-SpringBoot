package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.AchatPetEntity;

public interface AchatPetRepository extends JpaRepository<AchatPetEntity, Long> {
	@Query(value="SELECT ap.id_achat_pet , p.image_pet , p.nom_pet , p.prix_pet , sum(ap.quantite_pet) , p.prix_pet*sum(ap.quantite_pet) FROM achat_pet_entity ap inner JOIN pet_entity p on ap.fk_id_pet = p.id_pet\r\n"
			+ "WHERE ap.fk_id_user=:userId && ap.status_achat_pet is true\r\n"
			+ "GROUP by ap.fk_id_pet",nativeQuery = true)
	    List<String> getCartPetByIdUser(@Param("userId") Long userId);
}
