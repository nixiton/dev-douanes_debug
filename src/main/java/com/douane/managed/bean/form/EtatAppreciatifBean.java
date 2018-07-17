package com.douane.managed.bean.form;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.douane.managed.bean.SuiviEditionBean;
@SessionScoped
@ManagedBean(name="EtatAppreciatifBean")
public class EtatAppreciatifBean {
	private String service;
	private Date date;
	private String trois;
	private String quatre;
	private List<Object[]> liste;
	public EtatAppreciatifBean() {
		this.date = new Date();
	}
	public String execute(SuiviEditionBean s) {
		if (s != null) {
			this.liste = s.getListESForGrandLivreOld();
			this.trois = s.getDirection().getTrois();
			this.quatre = s.getDirection().getQuatre();
			this.service = s.getDirection().getDesignation();
			//System.out.println("etat Appreciatif null");
		}
		//this.liste = l;
		return "dialogEtatAppreciatif";
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<Object[]> getListe() {
		return liste;
	}
	public void setListe(List<Object[]> liste) {
		this.liste = liste;
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
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}

}
