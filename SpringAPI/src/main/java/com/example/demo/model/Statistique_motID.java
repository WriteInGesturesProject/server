package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Statistique_motID implements Serializable {
	private long mot2;

	private long enfant2;

	public int hashCode() {
	    return (int)(enfant2 + mot2);
	  }

	  public boolean equals(Object object) {
	    if (object instanceof Statistique_motID) {
	    	Statistique_motID otherId = (Statistique_motID) object;
	      return (otherId.enfant2 == this.enfant2) 
	              && (otherId.mot2 == this.mot2);
	    }
	    return false;
	  }
}
