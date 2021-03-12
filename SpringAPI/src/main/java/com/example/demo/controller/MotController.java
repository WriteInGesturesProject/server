package com.example.demo.controller;
import java.util.HashMap;
import java.util.Iterator;
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
import com.example.demo.repo.MotRepository;
import com.example.demo.model.Mot;

@RestController
@RequestMapping("/api/v1")
public class MotController {
	@Autowired
	private MotRepository motRepo;
	
	@GetMapping("mot")
	public List<Mot> getAllMot(){
		return this.motRepo.findAll();
		
	}
	
	@GetMapping("mot/{name}")
	public List <Mot> getMotByName(@PathVariable(value = "name") String motName)
			throws RessourceNotFoundException {
		return this.motRepo.findMotByOrtho(motName);
	}
	
	@GetMapping("mot/phonetic/{name}")
	public String getMotPhoneticByName(@PathVariable(value = "name") String motName)
			throws RessourceNotFoundException {
		List <Mot> Mots = this.motRepo.findMotByOrtho(motName);
		return Mots.get(0).getPhon();
	}
	
	@PostMapping("mot")
	public Mot createMot(@RequestBody Mot mot) {
		return this.motRepo.save(mot);
	}
	@PutMapping("mot/{id}")
	public ResponseEntity<Mot> updateMot(@PathVariable(value = "id") Long motID, @Validated @RequestBody Mot motdetails)
			throws RessourceNotFoundException {
		Mot mot = motRepo.findById(motID).orElseThrow(() -> new RessourceNotFoundException("Le mot n'a pas été trouvé pour cet ID ::" + motID));
		
		return ResponseEntity.ok(this.motRepo.save(mot));
		
	}
	@DeleteMapping("mot/{id}")
	public Map<String, Boolean> deleteMot (@PathVariable(value = "id") Long motID ) throws RessourceNotFoundException{
		Mot mot = motRepo.findById(motID).orElseThrow(() -> new RessourceNotFoundException("Le mot n'a pas été trouvé pour cet ID ::" + motID));
		this.motRepo.delete(mot);
		Map <String, Boolean> map = new HashMap<>();
		map.put("deleted", Boolean.TRUE);
		return map;
	}

}
