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
import com.example.demo.repo.ImageRepository;
import com.example.demo.model.Image;


@RestController
@RequestMapping("/api/v1")
public class ImageController {
	@Autowired
	private ImageRepository imageRepo;
	
	@GetMapping("image")
	public List<Image> getAllImage(){
		return this.imageRepo.findAll();
		
	}
	@GetMapping("image/{id}")
	public ResponseEntity<Image> getImageByID(@PathVariable(value = "id") Long imageID)
			throws RessourceNotFoundException {
		
		Image image = imageRepo.findById(imageID).orElseThrow(() -> new RessourceNotFoundException("L'image n'a pas été trouvé pour cet ID ::" + imageID));
		return ResponseEntity.ok().body(image);
	}
	@PostMapping("image")
	public Image createImage(@RequestBody Image image) {
		return this.imageRepo.save(image);
	}
	@PutMapping("image/{id}")
	public ResponseEntity<Image> updateImage(@PathVariable(value = "id") Long imageID, @Validated @RequestBody Image imagedetails)
			throws RessourceNotFoundException {
		Image image = imageRepo.findById(imageID).orElseThrow(() -> new RessourceNotFoundException("L'image n'a pas été trouvé pour cet ID ::" + imageID));
		image.setNom(imagedetails.getNom());
		image.setImage(imagedetails.getImage());
		
		
		return ResponseEntity.ok(this.imageRepo.save(image));
		
	}
	@DeleteMapping("image/{id}")
	public Map<String, Boolean> deleteImage (@PathVariable(value = "id") Long imageID ) throws RessourceNotFoundException{
		Image image = imageRepo.findById(imageID).orElseThrow(() -> new RessourceNotFoundException("L'image n'a pas été trouvé pour cet ID ::" + imageID));
		this.imageRepo.delete(image);
		Map <String, Boolean> map = new HashMap<>();
		map.put("deleted", Boolean.TRUE);
		return map;
	}

}
