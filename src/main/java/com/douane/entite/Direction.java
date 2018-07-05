package com.douane.entite;

import javax.persistence.*;

import java.sql.SQLException;

@Entity
public class Direction extends Referentiel {

	@Column(unique=true)
	private String codeDirection;
	
	public Direction() {
		// TODO Auto-generated constructor stub
		this.setLeref("Direction");
	}
	public Direction(String designation, String code)throws SQLException  {
		this.setDesignation(designation);
		this.setCodeDirection(code);
		this.setLeref("Direction");
	}

	public String getCodeDirection(){
		return codeDirection;
	}

	public void setCodeDirection(String codeDirection) throws SQLException  {
		this.codeDirection = codeDirection;
	}
	
	private String Budget;
	private String trois;
	private String quatre;

	public String getBudget() {
		return Budget;
	}
	public void setBudget(String budget) {
		Budget = budget;
	}
	public String getTrois() {
		return trois;
	}
	public void setTrois(String trois) {
		this.trois = trois;
	}
	public String getQuatre() {
		return quatre;
	}
	public void setQuatre(String quatre) {
		this.quatre = quatre;
	}
	

}
