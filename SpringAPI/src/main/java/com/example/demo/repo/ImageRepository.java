package com.example.demo.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Image;

@Repository

public interface ImageRepository extends JpaRepository<Image, Long> {
	
	@Query("SELECT u FROM Image u where u.nom = ?1") 
    Image findImageByName(String name);

}
