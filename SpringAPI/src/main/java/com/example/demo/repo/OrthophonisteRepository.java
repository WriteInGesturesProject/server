package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Enfant;
import com.example.demo.model.Orthophoniste;

@Repository

public interface OrthophonisteRepository extends JpaRepository<Orthophoniste, Long> {

		@Query("Select u From Orthophoniste u where u.nom = ?1 ")
		public Orthophoniste getNameOrtho(String name);
		
		@Query("Select u From Orthophoniste u where u.login = ?1 ")
		public Orthophoniste getLoginOrtho(String name);
}
