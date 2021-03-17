package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Statistique_mot")
public class Statistique_mot {
		@Id
		Statistique_motID id;
		

		@Column(name = "nb_tentative")
		private int nb_tentative;
		@Column(name = "nb_erreur")
		private int nb_erreur;
		@Column(name = "commentaire")
		private String commentaire;
		
		
		

		public int getNb_tentative() {
			return nb_tentative;
		}

		public void setNb_tentative(int nb_tentative) {
			this.nb_tentative = nb_tentative;
		}

		public int getNb_erreur() {
			return nb_erreur;
		}

		public void setNb_erreur(int nb_erreur) {
			this.nb_erreur = nb_erreur;
		}

		public String getCommentaire() {
			return commentaire;
		}

		public void setCommentaire(String commentaire) {
			this.commentaire = commentaire;
		}

		public Statistique_mot( int nb_tentative, int nb_erreur, 
				String commentaire, Mot mot , Enfant enfant) {
			super();
			this.nb_tentative = nb_tentative;
			this.nb_erreur = nb_erreur;
			this.commentaire = commentaire;
			
		}

		public Statistique_motID getId() {
			return id;
		}

		public void setId(Statistique_motID id) {
			this.id = id;
		}
		public Mot getFilm() {
	        return getId().getMot();
	}

	public void setMot(Mot mot) {
	        getId().setMot(mot);
	}

	public Enfant getEnfant() {
	        return getId().getEnfant();
	}

	public void setActeur(Enfant enfant) {
	        getId().setEnfant(enfant);
	}

}
