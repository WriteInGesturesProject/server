package com.example.demo.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Exception.RessourceNotFoundException;
import com.example.demo.repo.EnfantRepository;
import com.example.demo.model.Enfant;


@RestController
@RequestMapping("/api/v1")
public class EnfantController {
	@Autowired
	private EnfantRepository enfantRepo;
	
	@GetMapping("enfant")
	public List<Enfant> getAllEnfant(){
		return this.enfantRepo.findAll();
		
	}
	@GetMapping("enfant/{id}")
	public ResponseEntity<Enfant> getEnfantByID(@PathVariable(value = "id") Long enfantID)
			throws RessourceNotFoundException {
		
		Enfant enfant = enfantRepo.findById(enfantID).orElseThrow(() -> new RessourceNotFoundException("L'enfant n'a pas été trouvé pour cet ID ::" + enfantID));
		return ResponseEntity.ok().body(enfant);
	}
	@PostMapping("enfant")
	public Enfant createEnfant(@RequestBody Enfant enfant) {
		return this.enfantRepo.save(enfant);
	}
	@PutMapping("enfant/{id}")
	public ResponseEntity<Enfant> updateEnfant(@PathVariable(value = "id") Long enfantID, @Validated @RequestBody Enfant enfantdetails)
			throws RessourceNotFoundException {
		Enfant enfant = enfantRepo.findById(enfantID).orElseThrow(() -> new RessourceNotFoundException("L'enfant n'a pas été trouvé pour cet ID ::" + enfantID));
		enfant.setNom(enfantdetails.getNom());
		enfant.setPrenom(enfantdetails.getPrenom());
		enfant.setSexe(enfantdetails.getSexe());
		enfant.setAge(enfantdetails.getAge());
		enfant.setEthnicite(enfantdetails.getEthnicite());
		enfant.setNb_etoile(enfantdetails.getNb_etoile());
		enfant.setId_objet(enfantdetails.getId_objet());
		enfant.setLogin(enfantdetails.getLogin());
		enfant.setPassword(enfantdetails.getPassword());
		
		return ResponseEntity.ok(this.enfantRepo.save(enfant));
		
	}
	@DeleteMapping("enfant/{id}")
	public Map<String, Boolean> deleteEnfant (@PathVariable(value = "id") Long enfantID ) throws RessourceNotFoundException{
		Enfant enfant = enfantRepo.findById(enfantID).orElseThrow(() -> new RessourceNotFoundException("L'enfant n'a pas été trouvé pour cet ID ::" + enfantID));
		this.enfantRepo.delete(enfant);
		Map <String, Boolean> map = new HashMap<>();
		map.put("deleted", Boolean.TRUE);
		return map;
	}
}
