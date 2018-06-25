package com.douane.managed.bean.form;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
@SessionScoped
@ManagedBean(name="JournalABean")
public class JournalABean {
	private Date date;
	private List<Object[]> liste;
	private String dateF;
	private String dateD;
	public JournalABean() {
		this.date = new Date();
	}
	public String execute(List<Object[]> l) {
		if (l != null) {
			this.liste = l;
		}
		this.setDateF(this.dateD = this.date.toString());
		return "dialogJournalAdmin";
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
	public String getDateD() {
		return dateD;
	}
	public void setDateD(String dateD) {
		this.dateD = dateD;
	}
	public String getDateF() {
		return dateF;
	}
	public void setDateF(String dateF) {
		this.dateF = dateF;
	}

}
