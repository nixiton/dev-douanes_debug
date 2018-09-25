package com.douane.entite;

import javax.persistence.Entity;
/*
 * Entity Test pour Adresse 
 * N'est pas utilisé pour le moment
 */
@Entity
public class Adiresy extends Referentiel {

	public Adiresy() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Adiresy(String designation) {
		super(designation);
		this.setLeref("Adiresy");
		// TODO Auto-generated constructor stub
	}
	
	
}
