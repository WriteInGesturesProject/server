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
import com.example.demo.repo.ObjetRepository;
import com.example.demo.model.Objet;

@RestController
@RequestMapping("/api/v1")
public class ObjetController {
	@Autowired
	private ObjetRepository objetRepo;
	
	@GetMapping("objet")
	public List<Objet> getAllObjet(){
		return this.objetRepo.findAll();
		
	}
	@GetMapping("objet/{id}")
	public ResponseEntity<Objet> getObjetByID(@PathVariable(value = "id") Long objetID)
			throws RessourceNotFoundException {
		
		Objet objet = objetRepo.findById(objetID).orElseThrow(() -> new RessourceNotFoundException("L'objet n'a pas été trouvé pour cet ID ::" + objetID));
		return ResponseEntity.ok().body(objet);
	}
	@PostMapping("objet")
	public Objet createObjet(@RequestBody Objet objet) {
		return this.objetRepo.save(objet);
	}
	@PutMapping("objet/{id}")
	public ResponseEntity<Objet> updateObjet(@PathVariable(value = "id") Long objetID, @Validated @RequestBody Objet objetdetails)
			throws RessourceNotFoundException {
		Objet objet = objetRepo.findById(objetID).orElseThrow(() -> new RessourceNotFoundException("L'objet n'a pas été trouvé pour cet ID ::" + objetID));
		objet.setNom_objet(objetdetails.getNom_objet());
		
		return ResponseEntity.ok(this.objetRepo.save(objet));
		
	}
	@DeleteMapping("objet/{id}")
	public Map<String, Boolean> deleteObjet (@PathVariable(value = "id") Long objetID ) throws RessourceNotFoundException{
		Objet objet = objetRepo.findById(objetID).orElseThrow(() -> new RessourceNotFoundException("L'objet n'a pas été trouvé pour cet ID ::" + objetID));
		this.objetRepo.delete(objet);
		Map <String, Boolean> map = new HashMap<>();
		map.put("deleted", Boolean.TRUE);
		return map;
	}

}
