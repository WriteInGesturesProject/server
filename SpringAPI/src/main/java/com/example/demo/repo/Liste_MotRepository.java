package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Liste_mot;

@Repository
public interface Liste_MotRepository extends JpaRepository<Liste_mot, Long> {

	@Query("SELECT u FROM Liste_mot u where u.nom = ?1") 
    Liste_mot findListeByName(String name);
}
