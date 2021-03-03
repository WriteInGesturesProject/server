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
import com.example.demo.repo.AvatarRepository;
import com.example.demo.model.Avatar;

@RestController
@RequestMapping("/api/v1")
public class AvatarController {
	@Autowired
	private AvatarRepository avatarRepo;
	
	@GetMapping("avatar")
	public List<Avatar> getAllAvatar(){
		return this.avatarRepo.findAll();
		
	}
	@GetMapping("avatar/{id}")
	public ResponseEntity<Avatar> getAvatarByID(@PathVariable(value = "id") Long avatarID)
			throws RessourceNotFoundException {
		
		Avatar avatar = avatarRepo.findById(avatarID).orElseThrow(() -> new RessourceNotFoundException("L'avatar n'a pas été trouvé pour cet ID ::" + avatarID));
		return ResponseEntity.ok().body(avatar);
	}
	@PostMapping("avatar")
	public Avatar createAvatar(@RequestBody Avatar avatar) {
		return this.avatarRepo.save(avatar);
	}
	@PutMapping("avatar/{id}")
	public ResponseEntity<Avatar> updateAvatar(@PathVariable(value = "id") Long avatarID, @Validated @RequestBody Avatar avatardetails)
			throws RessourceNotFoundException {
		Avatar avatar = avatarRepo.findById(avatarID).orElseThrow(() -> new RessourceNotFoundException("L'avatar n'a pas été trouvé pour cet ID ::" + avatarID));
		avatar.setNom_avatar(avatardetails.getNom_avatar());
		avatar.setCout(avatardetails.getCout());
		
		return ResponseEntity.ok(this.avatarRepo.save(avatar));
		
	}
	@DeleteMapping("avatar/{id}")
	public Map<String, Boolean> deleteAvatar (@PathVariable(value = "id") Long avatarID ) throws RessourceNotFoundException{
		Avatar avatar = avatarRepo.findById(avatarID).orElseThrow(() -> new RessourceNotFoundException("L'avatar n'a pas été trouvé pour cet ID ::" + avatarID));
		this.avatarRepo.delete(avatar);
		Map <String, Boolean> map = new HashMap<>();
		map.put("deleted", Boolean.TRUE);
		return map;
	}

}
