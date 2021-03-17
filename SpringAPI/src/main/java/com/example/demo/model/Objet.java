package com.example.demo.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "objet")
public class Objet {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	
	private long id;
	
	@Column(name="nom_objet")
	
	private String nom_objet;
	
	@Column
	
	private String type;
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Objet() {
		super();
			}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom_objet() {
		return nom_objet;
	}

	public void setNom_objet(String nom_objet) {
		this.nom_objet = nom_objet;
	}

	public Objet(String nom_objet,String type) {
		super();
		this.nom_objet = nom_objet;
		this.type = type;
	}

	

	

}
