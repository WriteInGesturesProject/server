package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Enfant;

@Repository
public interface EnfantRepository extends JpaRepository<Enfant, Long>{
	@Query("Select u From Enfant u where u.nom = ?1 ")
	public Enfant getNameEnfant(String name);
	
	@Query("Select u From Enfant u where u.login = ?1 ")
	public Enfant getLoginEnfant(String name);
}
