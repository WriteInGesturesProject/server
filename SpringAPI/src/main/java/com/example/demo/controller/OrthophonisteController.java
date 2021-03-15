package com.example.demo.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.example.demo.repo.OrthophonisteRepository;
import com.example.demo.model.Enfant;
import com.example.demo.model.Mot;
import com.example.demo.model.Orthophoniste;

@RestController
@RequestMapping("/api/v1")
public class OrthophonisteController {

	@Autowired
	private OrthophonisteRepository orthophonisteRepo;

	@GetMapping("orthophoniste")
	public List<Orthophoniste> getAllOrthophoniste() {
		return this.orthophonisteRepo.findAll();

	}
	
	@GetMapping("orthophoniste/connexion/{login}/{password}")
	public ResponseEntity<Orthophoniste> getOrthophonisteConnection(@PathVariable(value = "login") String login,@PathVariable(value = "password") String password)
			throws RessourceNotFoundException {
		Orthophoniste ortho = this.orthophonisteRepo.getLoginOrtho(login);
		if(ortho.getPassword().contentEquals(password)) {
			return ResponseEntity.ok().body(ortho);
		}
		return null;
	}
	
	@GetMapping("orthophoniste/enfant/{name}")
	public List <Enfant> getEnfantsByName(@PathVariable(value = "name") String name)
			throws RessourceNotFoundException {
		return this.orthophonisteRepo.getNameOrtho(name).getEnfants();

	}
	
	@GetMapping("orthophoniste/name/{name}")
	public Orthophoniste getOrthophonisteByName(@PathVariable(value = "name") String name)
			throws RessourceNotFoundException {
		return this.orthophonisteRepo.getNameOrtho(name);

	}
	
	@GetMapping("orthophoniste/login/{login}")
	public Orthophoniste getOrthophonisteByLogin(@PathVariable(value = "login") String login)
			throws RessourceNotFoundException {
		return this.orthophonisteRepo.getLoginOrtho(login);

	}

	@GetMapping("orthophoniste/{id}")
	public ResponseEntity<Orthophoniste> getOrthophonisteByID(@PathVariable(value = "id") Long orthophonisteID)
			throws RessourceNotFoundException {

		Orthophoniste orthophoniste = orthophonisteRepo.findById(orthophonisteID)
				.orElseThrow(() -> new RessourceNotFoundException(
						"L'orthophoniste n'a pas été trouvé pour cet ID ::" + orthophonisteID));
		return ResponseEntity.ok().body(orthophoniste);
	}

	@PostMapping("orthophoniste")
	public Orthophoniste createOrthophoniste(@RequestBody Orthophoniste orthophoniste) {
		return this.orthophonisteRepo.save(orthophoniste);
	}

	@PutMapping("orthophoniste/{id}")
	public ResponseEntity<Orthophoniste> updateOrthophoniste(@PathVariable(value = "id") Long orthophonisteID,
			@Validated @RequestBody Orthophoniste orthophonistedetails) throws RessourceNotFoundException {
		Orthophoniste orthophoniste = orthophonisteRepo.findById(orthophonisteID)
				.orElseThrow(() -> new RessourceNotFoundException(
						"L'orthophoniste n'a pas été trouvé pour cet ID ::" + orthophonisteID));
		orthophoniste.setNom(orthophonistedetails.getNom());
		orthophoniste.setPrenom(orthophonistedetails.getPrenom());
		orthophoniste.setEmail(orthophonistedetails.getEmail());
		orthophoniste.setLogin(orthophonistedetails.getLogin());
		orthophoniste.setPassword(orthophonistedetails.getPassword());

		return ResponseEntity.ok(this.orthophonisteRepo.save(orthophoniste));

	}

	@DeleteMapping("orthophoniste/{id}")
	public Map<String, Boolean> deleteOrthophoniste(@PathVariable(value = "id") Long orthophonisteID)
			throws RessourceNotFoundException {
		Orthophoniste orthophoniste = orthophonisteRepo.findById(orthophonisteID)
				.orElseThrow(() -> new RessourceNotFoundException(
						"L'orthophoniste n'a pas été trouvé pour cet ID ::" + orthophonisteID));
		this.orthophonisteRepo.delete(orthophoniste);
		Map<String, Boolean> map = new HashMap<>();
		map.put("deleted", Boolean.TRUE);
		return map;
	}
}
