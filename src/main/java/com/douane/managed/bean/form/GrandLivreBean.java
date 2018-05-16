package com.douane.managed.bean.form;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="GrandLivre")

public class GrandLivreBean {
	private String budget;
	private String chap;
	private String article;
	private String libelle;
	private String subd;
	private String materiel;
	private String indication;
	private String nbFeuillets;
	private String lieu;
	private String date;
	private String ans;
	public String getBudget() {
		return budget;
	}
	public void setBudget(String budget) {
		this.budget = budget;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getSubd() {
		return subd;
	}
	public void setSubd(String subd) {
		this.subd = subd;
	}
	public String getMateriel() {
		return materiel;
	}
	public void setMateriel(String materiel) {
		this.materiel = materiel;
	}
	public String getIndication() {
		return indication;
	}
	public void setIndication(String indication) {
		this.indication = indication;
	}
	public String getNbFeuillets() {
		return nbFeuillets;
	}
	public void setNbFeuillets(String nbFeuillets) {
		this.nbFeuillets = nbFeuillets;
	}
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAns() {
		return ans;
	}
	public void setAns(String ans) {
		this.ans = ans;
	}
	public String getChap() {
		return chap;
	}
	public void setChap(String chap) {
		this.chap = chap;
	}
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}
}
