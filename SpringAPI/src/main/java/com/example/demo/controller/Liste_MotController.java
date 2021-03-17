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
import com.example.demo.repo.Liste_MotRepository;
import com.example.demo.model.Enfant;
import com.example.demo.model.Liste_mot;
import com.example.demo.model.Mot;

@RestController
@RequestMapping("/api/v1")

public class Liste_MotController {
	@Autowired
	private Liste_MotRepository Liste_MotRepo;
	
	@Autowired
	private EnfantRepository enfantRepo;
	
	@GetMapping("liste_mot")
	public List<Liste_mot> getAllListe_mot(){
		return this.Liste_MotRepo.findAll();
		
	}
	@GetMapping("liste_mot/{id}")
	public ResponseEntity<Liste_mot> getListe_motByID(@PathVariable(value = "id") Long liste_motID)
			throws RessourceNotFoundException {
		
		Liste_mot liste_mot = Liste_MotRepo.findById(liste_motID).orElseThrow(() -> new RessourceNotFoundException("La liste_mot n'a pas été trouvé pour cet ID ::" + liste_motID));
		return ResponseEntity.ok().body(liste_mot);
	}
	
	@GetMapping("liste_mot/mots/{name}")
	public List<Mot> getMotByListe_MotName(@PathVariable(value = "name") String liste_motName)
			throws RessourceNotFoundException {
		
		Liste_mot liste_mot = Liste_MotRepo.findListeByName(liste_motName);
		return liste_mot.getMots();
	}
	
	@GetMapping("liste_mot/name/{name}")
	public ResponseEntity<Liste_mot> getListe_motByName(@PathVariable(value = "name") String liste_motName)
			throws RessourceNotFoundException {
		
		Liste_mot liste_mot = Liste_MotRepo.findListeByName(liste_motName);
		return ResponseEntity.ok().body(liste_mot);
	}
	
	@GetMapping("liste_mot/enfant/{name}")
	public Enfant FromList(@PathVariable(value = "name") String name)
			throws RessourceNotFoundException {
		return enfantRepo.findById(Liste_MotRepo.findListeByName(name).getEnfant()).orElseThrow(() -> new RessourceNotFoundException("L'orthophoniste n'a pas été trouvé pour cet ID ::"));
	
		
	}
	
	
	
	@PutMapping("liste_mot/updateliste/{name}")
	public ResponseEntity<Liste_mot> addMotList(@Validated @RequestBody List<Mot> liste_mot, @PathVariable(value = "name") String liste_motName) {
		Liste_mot ldm = this.Liste_MotRepo.findListeByName(liste_motName);
		Iterator<Mot> iter = liste_mot.iterator();
		Mot tmp;
		while(iter.hasNext()) {
			tmp = iter.next();
			ldm.addMot(tmp);
		}
		return ResponseEntity.ok(this.Liste_MotRepo.save(ldm));
		}
	
	@PostMapping("liste_mot")
	public Liste_mot createListe_mot(@RequestBody Liste_mot liste_mot) {
		Liste_mot ldm = this.Liste_MotRepo.findListeByName(liste_mot.getNom());
		if(ldm != null ) {
			return null;
		}
		return this.Liste_MotRepo.save(liste_mot);
	}
	@PutMapping("liste_mot/{id}")
	public ResponseEntity<Liste_mot> updateListe_mot(@PathVariable(value = "id") Long liste_motID, @Validated @RequestBody Liste_mot liste_motdetails)
			throws RessourceNotFoundException {
		Liste_mot liste_mot = Liste_MotRepo.findById(liste_motID).orElseThrow(() -> new RessourceNotFoundException("La liste_mot n'a pas été trouvé pour cet ID ::" + liste_motID));
		liste_mot.setMots_utilisés(liste_motdetails.getMots_utilisés());
		liste_mot.setNb_tentative(liste_motdetails.getNb_tentative());
		liste_mot.setNb_mot(liste_motdetails.getNb_mot());
		liste_mot.setMots(liste_motdetails.getMots());
		liste_mot.setNom(liste_motdetails.getNom());
		liste_mot.setImage(liste_motdetails.getImage());
		return ResponseEntity.ok(this.Liste_MotRepo.save(liste_mot));
		
	}
	@DeleteMapping("liste_mot/{id}")
	public Map<String, Boolean> deleteListe_mot (@PathVariable(value = "id") Long liste_motID ) throws RessourceNotFoundException{
		Liste_mot liste_mot = Liste_MotRepo.findById(liste_motID).orElseThrow(() -> new RessourceNotFoundException("La liste_mot n'a pas été trouvé pour cet ID ::" + liste_motID));
		this.Liste_MotRepo.delete(liste_mot);
		Map <String, Boolean> map = new HashMap<>();
		map.put("deleted", Boolean.TRUE);
		return map;
	}

}
