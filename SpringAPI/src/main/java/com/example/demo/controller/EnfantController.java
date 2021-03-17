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
import com.example.demo.repo.EnfantRepository;
import com.example.demo.repo.OrthophonisteRepository;
import com.example.demo.model.Enfant;
import com.example.demo.model.Liste_mot;
import com.example.demo.model.Mot;
import com.example.demo.model.Objet;
import com.example.demo.model.Orthophoniste;

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
	
	@GetMapping("enfant/objets/{login}")
	public List<Objet> getMotByListe_MotName(@PathVariable(value = "login") String enfantLogin)
			throws RessourceNotFoundException {
		
		Enfant enfant = enfantRepo.getLoginEnfant(enfantLogin);
		return enfant.getObjet();
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
	
	@GetMapping("enfant/liste_mot/{login}")
	public List <Liste_mot> getListe_motByEnfantName(@PathVariable(value = "login") String login)
			throws RessourceNotFoundException {
		return this.enfantRepo.getLoginEnfant(login).getListes_Mot();

	}
	
	@PostMapping("enfant")
	public Enfant createEnfant(@RequestBody Enfant enfant) {
		Enfant tmp = this.enfantRepo.getLoginEnfant(enfant.getLogin());
		if(tmp !=  null) {
			return null;
		}
		return this.enfantRepo.save(enfant);
	}
	
	@PutMapping("enfant/updatelisteobjet/{login}")
	public ResponseEntity<Enfant> addMotList(@Validated @RequestBody Enfant enfant, @PathVariable(value = "login") String login) {
		Enfant enf = this.enfantRepo.getLoginEnfant(login);
		Iterator<Objet> iter = enf.getObjet().iterator();
		Objet tmp;
		System.out.println(1);
		while(iter.hasNext()) {
			tmp = iter.next();
			enf.addObjet(tmp);
		}
		return ResponseEntity.ok(this.enfantRepo.save(enf));
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
		enfant.setOrthophoniste(null);
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
