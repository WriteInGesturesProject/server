package com.example.demo.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.*;
import antlr.collections.List;

@Entity
@Table(name = "Statistique_mot")
public class Statistique_mot {
		@Id
		Statistique_motID id;
		@Column
		private String date;

		@Column(name = "nb_tentative")
		private int nb_tentative;
		@Column(name = "nb_erreur")
		private int nb_erreur;
		@Column(name = "commentaire")
		private String commentaire;

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

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

		public Statistique_mot(String date, int nb_tentative, int nb_erreur, 
				String commentaire) {
			super();
			this.date = date;
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
