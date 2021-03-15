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
import com.example.demo.repo.OrthophonisteRepository;
import com.example.demo.model.Enfant;
import com.example.demo.model.Orthophoniste;
import com.example.demo.controller.*;

@RestController
@RequestMapping("/api/v1")
public class EnfantController {
	@Autowired
	private EnfantRepository enfantRepo;
	
	@Autowired
	private OrthophonisteRepository OrthoRepo;
	@GetMapping("enfant")
	public List<Enfant> getAllEnfant(){
		return this.enfantRepo.findAll();
		
	}
	
	@GetMapping("enfant/connexion/{login}/{password}")
	public ResponseEntity<Enfant> getEnfantConnection(@PathVariable(value = "login") String login,@PathVariable(value = "password") String password)
			throws RessourceNotFoundException {
		Enfant enfant = this.enfantRepo.getLoginEnfant(login);
		if(enfant.getPassword().equals(password)) {
			return ResponseEntity.ok().body(enfant);
		}
		return null;
	}
	
	
	@GetMapping("enfant/name/{name}")
	public Enfant getEnfantByName(@PathVariable(value = "name") String name)
			throws RessourceNotFoundException {
		return this.enfantRepo.getNameEnfant(name);

	}
	
	@GetMapping("enfant/login/{login}")
	public Enfant getEnfantByLogin(@PathVariable(value = "login") String login)
			throws RessourceNotFoundException {
		return this.enfantRepo.getLoginEnfant(login);

	}
	
	@GetMapping("enfant/{id}")
	public ResponseEntity<Enfant> getEnfantByID(@PathVariable(value = "id") Long enfantID)
			throws RessourceNotFoundException {
		
		Enfant enfant = enfantRepo.findById(enfantID).orElseThrow(() -> new RessourceNotFoundException("L'enfant n'a pas été trouvé pour cet ID ::" + enfantID));
		return ResponseEntity.ok().body(enfant);
	}
	@GetMapping("enfant/ortho/{id}")
	public Orthophoniste getEnfantOrtho(@PathVariable(value = "id") Long enfantID)
			throws RessourceNotFoundException {
		
		Enfant enfant = enfantRepo.findById(enfantID).orElseThrow(() -> new RessourceNotFoundException("L'enfant n'a pas été trouvé pour cet ID ::" + enfantID));
		long id = enfant.getOrthophoniste();
		return OrthoRepo.findById(id).orElseThrow(() -> new RessourceNotFoundException("L'orthophoniste n'a pas été trouvé pour cet ID ::" + id));
		
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
	@PutMapping("enfant/{id}/objet/{id}")
	public Boolean addObjet (long EnfantID, long ObjetID) {
		return true;
	}
}
