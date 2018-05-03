package com.douane.managed.bean.form;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
@SessionScoped
@ManagedBean(name="JournalBean")
public class JournalBean {
	private String dateD;
	private String dateF;
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
	public String execute() {
		return "dialogJournal";
	}
}
