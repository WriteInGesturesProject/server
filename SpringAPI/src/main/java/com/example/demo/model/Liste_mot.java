package com.example.demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "liste_mot")
public class Liste_mot {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	
	private long id;
	
	@ManyToOne
    private Enfant enfant;
	
	@Column
    private String Image;
	
	@ManyToMany()
    private List<Mot> mots ;
	
	
	public Enfant getEnfant() {
		return enfant;
	}

	public void setEnfant(Enfant enfant) {
		this.enfant = enfant;
	}
	
	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public List<Mot> getMots() {
		return mots;
	}

	public void setMots(List<Mot> mots) {
		this.mots = mots;
	}

	public int getNb_mot() {
		return nb_mot;
	}

	public void setNb_mot(int nb_mot) {
		this.nb_mot = nb_mot;
	}

	public int getNb_tentative() {
		return nb_tentative;
	}

	public void setNb_tentative(int nb_tentative) {
		this.nb_tentative = nb_tentative;
	}

	public Mot[] getMots_utilisés() {
		return mots_utilisés;
	}

	public void setMots_utilisés(Mot[] mots_utilisés) {
		this.mots_utilisés = mots_utilisés;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Liste_mot(Enfant enfant, String image, List<Mot> mots, int nb_mot, int nb_tentative, Mot[] mots_utilisés,String nom) {
		super();
		this.enfant = enfant;
		this.Image = image;
		this.mots = mots;
		this.nb_mot = nb_mot;
		this.nb_tentative = nb_tentative;
		this.mots_utilisés = mots_utilisés;
		this.nom = nom;
	}


	@Column(name="nb_mot")
	
	private int nb_mot;
	
	@Column(name="nb_tentative")
	
	private int nb_tentative;
	
	@Column(name="mots_utilisés")

	private Mot[] mots_utilisés;
	
	@Column(name="nom")
	
	private String nom;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public Liste_mot() {
		super();
	}
	
	public void addMot(Mot mot) {
		this.mots.add(mot);
	}

}
