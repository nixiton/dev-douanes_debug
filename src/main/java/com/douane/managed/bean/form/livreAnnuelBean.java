package com.douane.managed.bean.form;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
@SessionScoped
@ManagedBean(name="livreAnnuelBean")
public class livreAnnuelBean {
	private Date d;
	private List<Object[]> liste;
	public livreAnnuelBean() {
		this.d = new Date();
	}
	public String execute(List<Object[]> liste) {
		this.setListe(liste);
		return "dialogLivre";
	}
	public Date getD() {
		return d;
	}
	public void setD(Date d) {
		this.d = d;
	}
	public List<Object[]> getListe() {
		return liste;
	}
	public void setListe(List<Object[]> liste) {
		this.liste = liste;
	}

}
