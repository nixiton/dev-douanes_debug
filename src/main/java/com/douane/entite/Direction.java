package com.douane.entite;

import javax.persistence.*;


@Entity
public class Direction extends Referentiel {

	@Column(unique=true)
	private String codeDirection;
	
	public Direction() {
		// TODO Auto-generated constructor stub
		this.setLeref("Direction");
	}
	public Direction(String designation, String code){
		this.setDesignation(designation);
		this.setCodeDirection(code);
		this.setLeref("Direction");
	}

	public String getCodeDirection() throws SQLException {
		return codeDirection;
	}

	public void setCodeDirection(String codeDirection) {
		this.codeDirection = codeDirection;
	}
	

}
