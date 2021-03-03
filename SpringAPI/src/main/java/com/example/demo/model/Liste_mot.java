package com.example.demo.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "liste_mot")
public class Liste_mot {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	
	private long id;
	
	@ManyToOne
    private Enfant enfant;
	
	@OneToOne
    private Image image;
	
	@ManyToMany()
    private Collection<Mot> mot ;
	
	
	@Column(name="type")
	
	private String type;
	
	@Column(name="nb_mot")
	
	private int nb_mot;
	
	@Column(name="active")
	
	private int active;
	
	@Column(name="nom")
	
	private String nom;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Enfant getEnfant() {
		return enfant;
	}

	public void setEnfant(Enfant enfant) {
		this.enfant = enfant;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getNb_mot() {
		return nb_mot;
	}

	public void setNb_mot(int nb_mot) {
		this.nb_mot = nb_mot;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Liste_mot(Enfant enfant, String type, int nb_mot, int active, String nom,Image image) {
		super();
		this.enfant = enfant;
		this.type = type;
		this.nb_mot = nb_mot;
		this.active = active;
		this.nom = nom;
		this.image = image;
		}

	public Liste_mot() {
		super();
	}
	

}
