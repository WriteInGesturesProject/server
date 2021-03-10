package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "image")
public class Image {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	
	private long id;
	
	@Column(name="nom")
	
	private String nom;
	
	@Lob()
	
    private byte[] image;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}


	public Image(String type, String nom,byte[] img) {
		super();
		this.nom = nom;
		this.image = img;
	}

	public Image() {
		super();
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] img) {
		this.image = img;
	}
	
	
}
