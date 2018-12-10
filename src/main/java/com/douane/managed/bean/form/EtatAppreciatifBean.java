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

import com.douane.entite.Direction;
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
	private Direction dir;
	private String section;
	public EtatAppreciatifBean() {
		this.date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(this.date);
		this.annee  = calendar.get(Calendar.YEAR);
		this.actualyear  = calendar.get(Calendar.YEAR);
	}
	public String execute(SuiviEditionBean s,Direction d) {
		Date sd = new GregorianCalendar(this.annee, Calendar.JANUARY, 1).getTime();
		if (this.annee< this.actualyear) {
			this.date = new GregorianCalendar(this.annee, Calendar.DECEMBER, 31).getTime();
		}
		DateFormat  df = new SimpleDateFormat("dd MMMM yyyy", Locale.FRANCE);
		this.sdate = df.format(sd);
		this.edate  = df.format(this.date);
		if (d == null) {
			this.liste = s.getListESExForEtatAppr(s.getDirection(),sd,this.date);
			this.trois = s.getDirection().getTrois();
			this.service = s.getDirection().getDesignation();
			this.dir = s.getDirection();
			this.section = s.getDirection().getBudget();
			
		}else {
			this.liste = s.getListESExForEtatAppr(d,sd,this.date);
			this.trois = d.getTrois();
			this.service = d.getDesignation();
			this.dir = d;
			this.section = d.getBudget();
		}
		return "dialogEtatAppreciatif";
		//this.liste = l;
	}
	public Direction getDir() {
		return dir;
	}
	public void setDir(Direction dir) {
		this.dir = dir;
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
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}

}
