package com.douane.entite;

import javax.persistence.Entity;

@Entity
public class Devise extends Referentiel {

	public Devise() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Devise(String designation) {
		super(designation);
		this.setLeref("Adresse");
		// TODO Auto-generated constructor stub
	}
	
	
}
