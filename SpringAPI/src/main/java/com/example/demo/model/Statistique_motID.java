package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.*;
@Embeddable
public class Statistique_motID implements Serializable {
	private static final long serialVersionUID = 1L;
	
	 @ManyToOne
     @JoinColumn(name = "id_mot")
     private Mot mot;

	 @ManyToOne
     @JoinColumn(name = "id_enfant")
     private Enfant enfant;
     
	 @Column
	 private String date;

	public Mot getMot() {
		return mot;
	}

	public void setMot(Mot mot) {
		this.mot = mot;
	}

	public Enfant getEnfant() {
		return enfant;
	}

	public void setEnfant(Enfant enfant) {
		this.enfant = enfant;
	}
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int hashCode() {
	    return (int)(enfant.getId() + mot.getId());
	  }

	  public boolean equals(Object object) {
	    if (object instanceof Statistique_motID) {
	    	Statistique_motID otherId = (Statistique_motID) object;
	      return (otherId.enfant == this.enfant) 
	              && (otherId.mot == this.mot);
	    }
	    return false;
	  }
}
