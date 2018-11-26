package com.douane.managed.bean.form;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.douane.managed.bean.SuiviEditionBean;
@SessionScoped
@ManagedBean(name="EtatAppreciatifBean")
public class EtatAppreciatifBean {
	private int actualyear;
	private int annee;
	private String service;
	private Date date;
	private String trois;
	private String quatre;
	private List<Object[]> liste;
	private String sdate;
	private String edate;
	public EtatAppreciatifBean() {
		this.date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(this.date);
		this.annee  = calendar.get(Calendar.YEAR);
		this.actualyear  = calendar.get(Calendar.YEAR);
	}
	public String execute(SuiviEditionBean s) {
		if (s != null) {
			Date sd = new GregorianCalendar(this.annee, Calendar.JANUARY, 1).getTime();
			DateFormat  df = new SimpleDateFormat("dd MMMM yyyy", Locale.FRANCE);
			this.sdate = df.format(sd);
			this.edate  = df.format(this.date);
			this.liste = s.getListInventaire(this.annee);
			this.trois = s.getDirection().getTrois();
			this.quatre = s.getDirection().getQuatre();
			this.service = s.getDirection().getDesignation();
			System.out.println("suivi in etat appreciatif non null");
			return "dialogEtatAppreciatif";
		}else {
			System.out.println("no suivi in etat appreciatif");
			return null;
		}
		//this.liste = l;
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
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	public int getActualyear() {
		return actualyear;
	}
	public void setActualyear(int actualyear) {
		this.actualyear = actualyear;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getEdate() {
		return edate;
	}
	public void setEdate(String edate) {
		this.edate = edate;
	}

}
