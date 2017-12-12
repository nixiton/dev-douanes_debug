package com.douane.entite;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TypeMateriel extends Referentiel{
	public TypeMateriel(){
		this.setTable("TypeMateriel");
	}
	public TypeMateriel(String designation){
		super(designation);
		this.setTable("TypeMateriel");
	}
	@ManyToOne
	@JoinColumn(name="idNomenclature")
	private Nomenclature nomenclaureParent;
	
	private String codeTypeMate;
	public Nomenclature getNomenclaureParent() {
		return nomenclaureParent;
	}
	public void setNomenclaureParent(Nomenclature nomenclaureParent) {
		this.nomenclaureParent = nomenclaureParent;
	}
	public String getCodeTypeMate() {
		return codeTypeMate;
	}
	public void setCodeTypeMate(String codeTypeMate) {
		this.codeTypeMate = codeTypeMate;
	}
	
}
