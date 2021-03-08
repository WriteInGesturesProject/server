package com.example.demo.model;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "mot")
public class Mot {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	
	private long id;
	
	@Column(name="name")
	
	private String name;
	
	@OneToOne
    private Image image;
	
	@OneToMany(mappedBy = "id.mot")
    private Set<Statistique_mot> Statistiques_mot = new HashSet<Statistique_mot>();
	
    
    

	
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Mot() {
		super();
	}

	public Mot(String name, Image image,Set <Statistique_mot> mot) {
		super();
		this.name = name;
		this.image = image;
		this.Statistiques_mot = mot;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Statistique_mot> getStatistiques_mot() {
		return Statistiques_mot;
	}

	public void setStatistiques_mot(Set<Statistique_mot> statistiques_mot) {
		Statistiques_mot = statistiques_mot;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMot() {
		return name;
	}

	public void setMot(String mot) {
		this.name = mot;
	}
	
}
