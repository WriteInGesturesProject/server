package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Orthophoniste;

@Repository

public interface OrthophonisteRepository extends JpaRepository<Orthophoniste, Long> {

}
