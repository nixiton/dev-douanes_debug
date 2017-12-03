package com.douane.entite;

import javax.persistence.*;

@Entity
public class Service extends Referentiel{

	@Column(unique=true)
	private String codeService;
	
	public String getCodeService() {
		return codeService;
	}

	public void setCodeService(String codeService) throws SQLException {
		this.codeService = codeService;
	}

	public Service() {
		// TODO Auto-generated constructor stub
	}

	public Service(String designation, String code) {
		super(designation);
		setCodeService(code);
		// TODO Auto-generated constructor stub
	}
	

}
