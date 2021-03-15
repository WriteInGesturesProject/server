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
import javax.persistence.JoinColumn;
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

    @OneToMany(mappedBy = "id.enfant")
    private Set<Statistique_mot> Statistiques_mot = new HashSet<Statistique_mot>();
	
	@ManyToMany()
    private Collection<Objet> objet ;
	
	@ManyToOne 
	//@JoinColumn(name = "id_orthophoniste")
    private Orthophoniste orthophoniste;
	
	@Column(name="nom")
	
	private String nom;
	
	@Column(name="prenom")
	
	private String prenom;
	
	@Column(name="sexe")
	
	private String sexe;
	
	@Column(name="age")
	
	private int age;
	
	@Column(name = "ethnicite")
	
	private String ethnicite;

	@Column(name="id_objet")

	private int[] id_objet;
	
	@Column(name="login")
	
	private String login;
	
	@Column(name="nb_etoile")
	
	private int nb_etoile;

	@Column(name="password")

	private String password;
	
	
	public Enfant(long id, String nom, String prenom, String sexe, int age, int id_ortho, int[] id_objet, String login,
			String password, int nb_etoile,Orthophoniste ortho,String ethnicite, Collection<Objet> objet,Set <Statistique_mot> stats) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		this.age = age;
		this.ethnicite = ethnicite;
		this.id_objet = id_objet;
		this.login = login;
		this.password = password;
		this.nb_etoile = nb_etoile;
		ortho.addEnfant(this);
		this.orthophoniste = ortho;
		this.objet = objet;
		this.Statistiques_mot = stats;
		}
	public Collection<Objet> getObjet() {
		return objet;
	}
	public void setObjet(Collection<Objet> objet) {
		this.objet = objet;
	}
	public String getEthnicite() {
		return ethnicite;
	}
	public void setEthnicite(String ethnicite) {
		this.ethnicite = ethnicite;
	}
	public Set<Statistique_mot> getStatistiques_mot() {
		return Statistiques_mot;
	}
	public void setStatistiques_mot(Set<Statistique_mot> statistiques_mot) {
		Statistiques_mot = statistiques_mot;
	}
	public int getNb_etoile() {
		return nb_etoile;
	}
	public void setNb_etoile(int nb_etoile) {
		this.nb_etoile = nb_etoile;
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
	public long getOrthophoniste() {
		return orthophoniste.getId();
	}
	public void setOrthophoniste(Orthophoniste orthophoniste) {
		this.orthophoniste = orthophoniste;
	}
	public int[] getId_objet() {
		return id_objet;
	}
	public void setId_objet(int[] id_objet) {
		this.id_objet = id_objet;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
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
