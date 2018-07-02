package com.douane.managed.bean.form;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
		DateFormat  df = new SimpleDateFormat("EEE dd MMM yyyy", Locale.FRANCE);
		this.dateF = this.dateD = df.format(this.date);
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
