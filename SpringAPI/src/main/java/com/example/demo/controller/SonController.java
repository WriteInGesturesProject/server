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
import com.example.demo.repo.SonRepository;
import com.example.demo.model.Son;

@RestController
@RequestMapping("/api/v1")
public class SonController {
	@Autowired
	private SonRepository sonRepo;
	
	@GetMapping("son")
	public List<Son> getAllSon(){
		return this.sonRepo.findAll();
		
	}
	@GetMapping("son/{id}")
	public ResponseEntity<Son> getSonByID(@PathVariable(value = "id") Long sonID)
			throws RessourceNotFoundException {
		
		Son son = sonRepo.findById(sonID).orElseThrow(() -> new RessourceNotFoundException("Le son n'a pas été trouvé pour cet ID ::" + sonID));
		return ResponseEntity.ok().body(son);
	}
	@PostMapping("son")
	public Son createson(@RequestBody Son son) {
		return this.sonRepo.save(son);
	}
	@PutMapping("son/{id}")
	public ResponseEntity<Son> updateSon(@PathVariable(value = "id") Long sonID, @Validated @RequestBody Son sondetails)
			throws RessourceNotFoundException {
		Son son = sonRepo.findById(sonID).orElseThrow(() -> new RessourceNotFoundException("Le son n'a pas été trouvé pour cet ID ::" + sonID));
		son.setNom(sondetails.getNom());
		son.setDuree(sondetails.getDuree());
		son.setDescription(sondetails.getDescription());
		
		return ResponseEntity.ok(this.sonRepo.save(son));
		
	}
	@DeleteMapping("son/{id}")
	public Map<String, Boolean> deleteSon (@PathVariable(value = "id") Long sonID ) throws RessourceNotFoundException{
		Son son = sonRepo.findById(sonID).orElseThrow(() -> new RessourceNotFoundException("Le son n'a pas été trouvé pour cet ID ::" + sonID));
		this.sonRepo.delete(son);
		Map <String, Boolean> map = new HashMap<>();
		map.put("deleted", Boolean.TRUE);
		return map;
	}
}
