package com.douane.entite;

import javax.persistence.*;

import java.sql.SQLException;

@Entity
public class Bureau extends Referentiel {

	@Column(unique=true)
	private String codeBureau;
	
	public Bureau() {
		this.setLeref("Bureau");
		// TODO Auto-generated constructor stub
	}

	public Bureau(String designation, String code) throws SQLException  {
		super(designation);
		setCodeBureau(code);
		this.setLeref("Bureau");
		// TODO Auto-generated constructor stub
	}

	public String getCodeBureau() {
		return codeBureau;
	}

	public void setCodeBureau(String codeBureau) throws SQLException  {
		this.codeBureau = codeBureau;
	}
	
	

}
