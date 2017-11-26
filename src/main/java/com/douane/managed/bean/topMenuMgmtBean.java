package com.douane.managed.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.douane.entite.Agent;
import com.douane.requesthttp.RequestFilter;

import org.springframework.transaction.annotation.Transactional;


@ManagedBean(name="topMenuMB")
@RequestScoped
@Transactional
public class topMenuMgmtBean {

	private Agent curentAgent;


	public Agent getCurentAgent(){
		return (Agent)RequestFilter.getSession().getAttribute("agent");
	}

	public void setCurentAgent(Agent a){
		this.curentAgent = a;
	}
	
	public String goToDashboard(){
		if(((Agent)RequestFilter.getSession().getAttribute("agent")).getRoleAgent().getRole() == "ROLE_ADMIN"){
			return "dashboard"; 
		}

		if(((Agent)RequestFilter.getSession().getAttribute("agent")).getRoleAgent().getRole() == "ROLE_DC" || 
			((Agent)RequestFilter.getSession().getAttribute("agent")).getRoleAgent().getRole() == "ROLE_SISE" ||
			((Agent)RequestFilter.getSession().getAttribute("agent")).getRoleAgent().getRole() == "ROLE_GAC"){
			return "cm"; 
		}

		return "ca";
		
	}
}
