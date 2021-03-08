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
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "enfant")
public class Enfant {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	
	private long id;
	

    @OneToMany(mappedBy = "enfant2")
    private List<Statistique_mot> enfant2;
	
	@ManyToMany()
    private Collection<Avatar> avatar ;
	@ManyToOne
    private Orthophoniste orthophoniste;
	
	@Column(name="nom")
	
	private String nom;
	
	@Column(name="prenom")
	
	private String prenom;
	
	@Column(name="sexe")
	
	private String sexe;
	
	@Column(name="age")
	
	private int age;

	@Column(name="id_avatar")

	private int id_avatar;
	
	@Column(name="login")
	
	private int login;
	
	public List<Statistique_mot> getenfant() {
		return enfant2;
	}
	public void setenfant(List<Statistique_mot> enfant) {
		this.enfant2 = enfant;
	}
	public Collection<Avatar> getAvatar() {
		return avatar;
	}
	public void setAvatar(Collection<Avatar> avatar) {
		this.avatar = avatar;
	}
	@Column(name="nb_piece")
	
	private int nb_piece;

	@Column(name="password")

	private String password;
	
	
	public Enfant(long id, String nom, String prenom, String sexe, int age, int id_ortho, int id_avatar, int login,
			String password, int nb_piece,Orthophoniste ortho,Collection<Avatar> avatar,List <Statistique_mot> enfant) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		this.age = age;
		this.id_avatar = id_avatar;
		this.login = login;
		this.password = password;
		this.nb_piece = nb_piece;
		this.orthophoniste = ortho;
		this.avatar = avatar;
		this.enfant2 = enfant;
		}
	public int getNb_piece() {
		return nb_piece;
	}
	public void setNb_piece(int nb_piece) {
		this.nb_piece = nb_piece;
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
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Orthophoniste getOrthophoniste() {
		return orthophoniste;
	}
	public void setOrthophoniste(Orthophoniste orthophoniste) {
		this.orthophoniste = orthophoniste;
	}
	public int getId_avatar() {
		return id_avatar;
	}
	public void setId_avatar(int id_avatar) {
		this.id_avatar = id_avatar;
	}
	public int getLogin() {
		return login;
	}
	public void setLogin(int login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}
	public Enfant() {
		super();
	}
	
		
	
	
	

}
