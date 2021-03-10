package com.example.demo.controller;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.Exception.RessourceNotFoundException;
import com.example.demo.repo.ImageRepository;
import com.example.demo.model.Image;


@RestController
@RequestMapping("/api/v1")
public class ImageController {
	@Autowired
	private ImageRepository imageRepo;
	
	@GetMapping("image")
	public byte[] getAllImage(){
		return this.imageRepo.findAll().get(0).getImage();
		
	}
	@GetMapping("image/{id}")
	public ResponseEntity<byte[]> getImageByID(@PathVariable(value = "id") Long imageID)
			throws RessourceNotFoundException {
		
		Image image = imageRepo.findById(imageID).orElseThrow(() -> new RessourceNotFoundException("L'image n'a pas été trouvé pour cet ID ::" + imageID));
		return ResponseEntity.ok().body(image.getImage());
	}
	@PostMapping("image")
	public Image createImage(@RequestParam("file") MultipartFile  file) throws IOException {
		Image image = new Image();
		System.out.println("1");
		System.out.println(file.getOriginalFilename());
	    image.setNom(file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf(".")));
	    System.out.println("2");
	    String tmp = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
	    System.out.println("2.5");
	    image.setMimeType(tmp);
	    System.out.println("3");
	    image.setImage(file.getBytes());
	    System.out.println("4");
		return this.imageRepo.save(image);
	}
	@PutMapping("image/{id}")
	public ResponseEntity<Image> updateImage(@PathVariable(value = "id") Long imageID, @Validated @RequestBody Image imagedetails)
			throws RessourceNotFoundException {
		Image image = imageRepo.findById(imageID).orElseThrow(() -> new RessourceNotFoundException("L'image n'a pas été trouvé pour cet ID ::" + imageID));
		
			
		
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
