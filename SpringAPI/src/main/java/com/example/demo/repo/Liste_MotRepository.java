package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Liste_mot;

@Repository
public interface Liste_MotRepository extends JpaRepository<Liste_mot, Long> {

}
