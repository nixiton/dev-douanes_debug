package com.douane.managed.bean.form;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
@SessionScoped
@ManagedBean(name="JournalBean")
public class JournalBean {
	private String dateD;
	private String dateF;
	private Date dat;
	private List<Object[]> li;
	public JournalBean() {
		this.dat = new Date();
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
	public String execute(List <Object[]> l) {
		this.li = l;
		this.dateF = this.dateD = this.dat.toString();
		return "dialogJournal";
	}
	public Date getDat() {
		return dat;
	}
	public void setDat(Date d) {
		dat = d;
	}
	public List<Object[]> getLi() {
		return li;
	}
	public void setLi(List<Object[]> li) {
		this.li = li;
	}
}
