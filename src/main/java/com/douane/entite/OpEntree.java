package com.douane.entite;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import javax.persistence.FetchType;

import javax.persistence.ElementCollection;

@Entity
@Table(name="operationentree")
public class OpEntree extends Operation{
	private static Long numerochronoe;
	
	@ManyToOne
	@JoinColumn(name="idMat")
	private Materiel mat;
	private String numentree;
	
	static {
		numerochronoe = 1L;
	}
	
	public OpEntree(Date date, Date time, String poste, Agent operateur, Materiel mater) {
		super(date, time, poste, operateur);
		this.setMat(mater);
		this.numentree = "";
	}
    public OpEntree(Date date, Date time, String poste, Agent operateur, List<Materiel> listMater) {
        super(date, time, poste, operateur);
        this.setListMat(listMater);
        this.numentree = "";
    }
	public OpEntree() {
		
	}
	public void generateNumEntree() {
		//Date today = new Date();
		
	    int d = Calendar.getInstance().get(Calendar.DAY_OF_MONTH); String dd="x";
	    int m = Calendar.getInstance().get(Calendar.MONTH); String mm="x";
	    int y = Calendar.getInstance().get(Calendar.YEAR); String yy="x";
	    if(d < 10){
	      dd="0"+d;
	    }else {
	    	dd=""+d;
	    }
	    if(m < 10){
	       mm="0"+m;
	    }else {
	    	mm=""+m;
	    }
		yy=""+y%200;
		String codeDirection ="tsy misy";
		
		this.numentree="OE "+ numerochronoe+ "/"+codeDirection+ "/" +dd+ "/" +mm+ "/" +yy;
		numerochronoe+=1;
	}
	public Materiel getMat() {
		return mat;
	}
	public void setMat(Materiel mat) {
		this.mat = mat;
	}
	public String getNumentree() {
		return this.numentree;
	}



	//----CORRECTION---------
	@OneToMany(mappedBy="operationentree", fetch=FetchType.EAGER)
	//@ElementCollection
	private List<Materiel> listMat = new ArrayList<Materiel>();

	private String pathDoc;
	private String refFact;

	public String getPathDoc() {
		return pathDoc;
	}

	public void setPathDoc(String pathDoc) {
		this.pathDoc = pathDoc;
	}

	public String getRefFact() {
		return refFact;
	}

	public void setRefFact(String refFact) {
		this.refFact = refFact;
	}



	public List<Materiel> getListMat() {
		return listMat;
	}

	public void setListMat(List<Materiel> listMat) {
		this.listMat = listMat;
	}
}
