package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orthophoniste")
public class Orthophoniste {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	
	private long id;
	
	@Column(name="nom")
	
	private String nom;
	
	@Column(name="prenom")
	
	private String prenom;
	
	@Column(name="login")
	
	private String login;
	
	@Column(name="password")
	
	private int password;
	
	@Column(name="email")
	
	private int email;

	public Orthophoniste(long id, String nom, String prenom, String login, int password, int email) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.password = password;
		this.email = email;
	}
	public Orthophoniste() {
		super();
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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public int getPassword() {
		return password;
	}

	public void setPassword(int password) {
		this.password = password;
	}

	public int getEmail() {
		return email;
	}

	public void setEmail(int email) {
		this.email = email;
	}



}
