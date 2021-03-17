package com.example.demo.model;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "mot")
public class Mot {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	
	private long id;
	
	
	@Column(name="ortho")
	private String ortho;
	
	@Column(name="phon")
	private String phon;
	
	@Column(name="cgram")
	private String cgram;
	
	@Column(name="freqfilms2")
	private float freqfilms2;
	
	@Column(name="nbphons")
	private int nbphons;
	
	@Column(name="p_cvcv")
	private String p_cvcv;
	
	@Column(name="nbsyll")
	private int nbsyll;
	
	@Column(name="image")
    private String image;
	
	@OneToMany(mappedBy = "id.mot")
    private Set<Statistique_mot> Statistiques_mot = new HashSet<Statistique_mot>();

	public Mot(String ortho, String phon, String cgram, float freqfilms2, int nbphons, String p_cvcv, int nbsyll,
			String image) {
		super();
		this.ortho = ortho;
		this.phon = phon;
		this.cgram = cgram;
		this.freqfilms2 = freqfilms2;
		this.nbphons = nbphons;
		this.p_cvcv = p_cvcv;
		this.nbsyll = nbsyll;
		this.image = image;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOrtho() {
		return ortho;
	}

	public void setOrtho(String ortho) {
		this.ortho = ortho;
	}

	public String getPhon() {
		return phon;
	}

	public void setPhon(String phon) {
		this.phon = phon;
	}

	public String getCgram() {
		return cgram;
	}

	public void setCgram(String cgram) {
		this.cgram = cgram;
	}

	public float getFreqfilms2() {
		return freqfilms2;
	}

	public void setFreqfilms2(float freqfilms2) {
		this.freqfilms2 = freqfilms2;
	}

	public int getNbphons() {
		return nbphons;
	}

	public void setNbphons(int nbphons) {
		this.nbphons = nbphons;
	}

	public String getP_cvcv() {
		return p_cvcv;
	}

	public void setP_cvcv(String p_cvcv) {
		this.p_cvcv = p_cvcv;
	}

	public int getNbsyll() {
		return nbsyll;
	}

	public void setNbsyll(int nbsyll) {
		this.nbsyll = nbsyll;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Set<Statistique_mot> getStatistiques_mot() {
		return Statistiques_mot;
	}

	public void setStatistiques_mot(Set<Statistique_mot> statistiques_mot) {
		Statistiques_mot = statistiques_mot;
	}

	public Mot() {
		super();
	}
	public boolean equalName(String name) {
		return this.getOrtho() == name;
	}
}
