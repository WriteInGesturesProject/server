package com.example.demo.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "avatar")
public class Avatar {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	
	private long id;
	
	@Column(name="nom_avatar")
	
	private String nom_avatar;
	
	@Column(name="cout")
	
	private int cout;
	
	public Avatar() {
		super();
			}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom_avatar() {
		return nom_avatar;
	}

	public void setNom_avatar(String nom_avatar) {
		this.nom_avatar = nom_avatar;
	}

	public int getCout() {
		return cout;
	}

	public void setCout(int cout) {
		this.cout = cout;
	}

	public Avatar(String nom_avatar, int cout) {
		super();
		this.nom_avatar = nom_avatar;
		this.cout = cout;
	}

	

	

}
