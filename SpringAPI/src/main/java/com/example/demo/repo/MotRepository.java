package com.example.demo.repo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Mot;

@Repository
public interface MotRepository extends JpaRepository<Mot, Long> {
	
	@Query("SELECT u FROM Mot u where u.ortho = ?1") 
    List <Mot> findMotByOrtho(String ortho);

}
