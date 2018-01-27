package com.douane.managed.bean;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.douane.entite.Agent;
import com.douane.entite.Operation;
import com.douane.metier.user.IUserMetier;
import com.douane.requesthttp.RequestFilter;

@ManagedBean(name="operationBean")
@RequestScoped
public class OperationBean {
	private static final String SUCCESS = "success";
    private static final String ERROR   = "error";
    
    @ManagedProperty(value="#{usermetier}")
    IUserMetier usermetierimpl;
    
    

	private List<Operation> mesOperations;
	private List<Operation> listOperations;
	private Operation operation;


	
	public IUserMetier getUsermetierimpl() {
		return usermetierimpl;
	}

	public void setUsermetierimpl(IUserMetier usermetierimpl) {
		this.usermetierimpl = usermetierimpl;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public List<Operation> getMesOperations() {
		Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
		this.setMesOperations(usermetierimpl.getListOpByOperator(agent));
		return mesOperations;
	}

	public void setMesOperations(List<Operation> mesOperations) {
		this.mesOperations = mesOperations;
	}

	public List<Operation> getListOperations() {
		//this.setListOperations(usermetierimpl.getListOp());
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		Date sdate = new GregorianCalendar(year-2, Calendar.JANUARY, 1).getTime();
        Date edate = new GregorianCalendar(year+1, Calendar.DECEMBER, 30).getTime();
        Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
		
		this.setListOperations(usermetierimpl.getListAllOperationByDirectionByYearByDateAsc(agent.getDirection(), sdate, edate));
		return listOperations;
	}

	public void setListOperations(List<Operation> listOperations) {
		this.listOperations = listOperations;
	}

}
