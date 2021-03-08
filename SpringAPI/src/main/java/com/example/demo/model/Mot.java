package com.example.demo.model;
import java.util.Collection;
import java.util.List;

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
	
	@ManyToMany()
    private Collection<Image> image ;
	
	@OneToMany(mappedBy = "mot2")
    private List<Statistique_mot> mot2;

	
	public Collection<Image> getImage() {
		return image;
	}

	public void setImage(Collection<Image> image) {
		this.image = image;
	}

	public List<Statistique_mot> getmot() {
		return mot2;
	}

	public void setmot(List<Statistique_mot> mot) {
		this.mot2 = mot;
	}

	public Mot() {
		super();
	}

	public Mot(String name, Son son, Collection<Image> image,List <Statistique_mot> mot) {
		super();
		this.name = name;
		this.son = son;
		this.image = image;
		this.mot2 = mot;
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

	public Son getSon() {
		return son;
	}

	public void setSon(Son son) {
		this.son = son;
	}

	@OneToOne
    private Son son;
	
}
