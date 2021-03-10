package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "image")
public class Image {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	
	private long id;
	
	@Column(name="type")
	
	private String type;
	
	@Column(name="nom")
	
	private String nom;
	
	@Column(name="description")
	
	private String description;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Image(String type, String nom, String description) {
		super();
		this.type = type;
		this.nom = nom;
		this.description = description;
	}

	public Image() {
		super();
	}
	
	
}
