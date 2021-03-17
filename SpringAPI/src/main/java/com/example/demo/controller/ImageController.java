package com.example.demo.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Exception.RessourceNotFoundException;
import com.example.demo.repo.ImageRepository;
import com.example.demo.model.Image;

@RestController
@RequestMapping("/api/v1")
public class ImageController {
	@Autowired
	private ImageRepository imageRepo;
	
	@Transactional 
	@GetMapping("image/name/{nom}")
	public byte[] getImageByName(@PathVariable(value = "nom") String nom) throws RessourceNotFoundException {
		return this.imageRepo.findImageByName(nom).getImage();

	}
	@Transactional 
	@PostMapping("image")
	public Image createImage(@RequestParam("file") MultipartFile file) throws IOException {
		Image image = new Image();
		System.out.println(file.getOriginalFilename());
		image.setNom(file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf(".")));
		String tmp = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
		image.setMimeType(tmp);
		image.setImage(file.getBytes());
		Image img = this.imageRepo.findImageByName(image.getNom());
		if(img != null ) {
			return null;
		}
		return this.imageRepo.save(image);
	}

	@PutMapping("image/{id}")
	public ResponseEntity<Image> updateImage(@PathVariable(value = "id") Long imageID,
			@Validated @RequestBody Image imagedetails) throws RessourceNotFoundException {
		Image image = imageRepo.findById(imageID).orElseThrow(
				() -> new RessourceNotFoundException("L'image n'a pas été trouvé pour cet ID ::" + imageID));

		return ResponseEntity.ok(this.imageRepo.save(image));

	}

	@DeleteMapping("image/{id}")
	public Map<String, Boolean> deleteImage(@PathVariable(value = "id") Long imageID)
			throws RessourceNotFoundException {
		Image image = imageRepo.findById(imageID).orElseThrow(
				() -> new RessourceNotFoundException("L'image n'a pas été trouvé pour cet ID ::" + imageID));
		this.imageRepo.delete(image);
		Map<String, Boolean> map = new HashMap<>();
		map.put("deleted", Boolean.TRUE);
		return map;
	}

}
