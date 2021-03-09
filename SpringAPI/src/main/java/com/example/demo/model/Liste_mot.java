package com.example.demo.model;

import java.util.Collection;
import java.util.List;

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

	public Enfant getEnfant() {
		return enfant;
	}

	public void setEnfant(Enfant enfant) {
		this.enfant = enfant;
	}

	public int getNb_mot() {
		return nb_mot;
	}

	public void setNb_mot(int nb_mot) {
		this.nb_mot = nb_mot;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Liste_mot(Enfant enfant, int nb_mot, String nom,Image image, Mot[] mots_utilisé, int nb_tentative) {
		super();
		this.enfant = enfant;
		this.nb_mot = nb_mot;
		this.nom = nom;
		this.image = image;
		this.nb_tentative = nb_tentative;
		this.mots_utilisés = mots_utilisé;
		}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Collection<Mot> getMot() {
		return mot;
	}

	public void setMot(Collection<Mot> mot) {
		this.mot = mot;
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

	public Liste_mot() {
		super();
	}
	

}
