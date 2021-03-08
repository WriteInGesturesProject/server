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

import antlr.collections.List;

@Entity
@Table(name = "Statistique_mot")
@IdClass(Statistique_motID.class)
public class Statistique_mot {
	public class ProjectAssociation {
		@Id
		@Embedded
		private String date;

		@Column(name = "nb_tentative")
		private int nb_tentative;
		@Column(name = "nb_erreur")
		private int nb_erreur;
		@Column(name = "ressenti_enfant")
		private String ressenti_enfant;
		@Column(name = "eval_enfant")
		private String eval_enfant;
		@Column(name = "nb_exo")
		private int nb_exo;
		@Column(name = "commentaire")
		private String commentaire;

		@Id
		@ManyToOne
		@JoinColumn(name = "enfantId")

		private Enfant enfant2;
		@Id
		@ManyToOne
		@JoinColumn(name = "motId")
		private Mot mot2;

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

		public String getRessenti_enfant() {
			return ressenti_enfant;
		}

		public void setRessenti_enfant(String ressenti_enfant) {
			this.ressenti_enfant = ressenti_enfant;
		}

		public String getEval_enfant() {
			return eval_enfant;
		}

		public void setEval_enfant(String eval_enfant) {
			this.eval_enfant = eval_enfant;
		}

		public int getNb_exo() {
			return nb_exo;
		}

		public void setNb_exo(int nb_exo) {
			this.nb_exo = nb_exo;
		}

		public String getCommentaire() {
			return commentaire;
		}

		public void setCommentaire(String commentaire) {
			this.commentaire = commentaire;
		}

		public Enfant getEnfant() {
			return enfant2;
		}

		public void setEnfant(Enfant enfant) {
			this.enfant2 = enfant;
		}

		public Mot getMot() {
			return mot2;
		}

		public void setMot(Mot mot) {
			this.mot2 = mot;
		}

		public ProjectAssociation(String date, int nb_tentative, int nb_erreur, String ressenti_enfant,
				String eval_enfant, int nb_exo, String commentaire, Enfant enfant, Mot mot) {
			super();
			this.date = date;
			this.nb_tentative = nb_tentative;
			this.nb_erreur = nb_erreur;
			this.ressenti_enfant = ressenti_enfant;
			this.eval_enfant = eval_enfant;
			this.nb_exo = nb_exo;
			this.commentaire = commentaire;
			this.enfant2 = enfant;
			this.mot2 = mot;
		}

	}
}
