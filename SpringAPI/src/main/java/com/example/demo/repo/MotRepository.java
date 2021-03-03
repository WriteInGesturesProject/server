package com.example.demo.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Mot;

@Repository
public interface MotRepository extends JpaRepository<Mot, Long> {

}
