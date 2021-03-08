package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Statistique_motID;

@Repository
public interface Statistique_motIDRepository extends JpaRepository<Statistique_motID, Long> {

}
