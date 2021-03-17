package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Objet;

@Repository
public interface ObjetRepository extends JpaRepository<Objet, Long>{
	
	@Query("SELECT u FROM Objet u where u.nom_objet = ?1") 
    Objet findObjetByName(String nom_objet);
}
