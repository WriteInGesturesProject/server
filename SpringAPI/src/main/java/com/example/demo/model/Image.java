package com.example.demo.model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
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
	
	@Column(name="mime_type")
    private String mimeType;

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

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


	public Image(String type, String nom,byte[] img,String mimeType) {
		super();
		this.nom = nom;
		this.image = img;
		this.mimeType = mimeType;
	}

	public Image() {
		super();
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] byteArray){
		this.image = byteArray;
	}
	
	
	
	
}
