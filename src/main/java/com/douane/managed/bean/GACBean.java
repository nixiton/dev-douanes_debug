package com.douane.managed.bean;

import com.douane.entite.*;
import com.douane.metier.user.IUserMetier;
import com.douane.repository.OpRepository;
import com.douane.requesthttp.RequestFilter;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.SessionScoped;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.HashMap;
import java.util.List;
@SessionScoped

/**
 * Created by hasina on 11/3/17.
 */


@ManagedBean(name="gacBean")
public class GACBean {
    @ManagedProperty(value="#{usermetier}")
    IUserMetier usermetierimpl;
    
    //@ManagedProperty(value="#{suivieditionBean}")
    //private SuiviEditionBean suivibean;
    
    private List<Agent> listAgent;


    private HashMap<Agent,List<Operation>> AgentOp = new HashMap<Agent, List<Operation>>();
    private Agent gac;


    private Operation curentOperation;



    private List<Operation> listAllOperation;

    private String motif;



    public HashMap<Agent,List<Operation>> getOperationAndDepositaire()
    {
        //first get direction of GAC
        gac = (Agent) RequestFilter.getSession().getAttribute("agent");
        //get all operation request for each agent
        listAgent = getAllAgents();
        for (Agent a: listAgent)
        {
            if(a.getDirection().getCodeDirection().equals(gac.getDirection().getCodeDirection()))
            {
                AgentOp.put(a,usermetierimpl.getListOpByOperator(a));
            }
        }

        return AgentOp;
    }

    public void validatePrisEnChargeEntreMat(Operation op)
    {
        //usermetierimpl.entrerMateriel(op);
        usermetierimpl.entrerMateriel((OpEntree)this.getCurentOperation());
        this.setCurentOperation(null);
        
    }


    public void refusePrisEnChargeEntreMat(Operation op) throws Exception
    {
        //usermetierimpl.entrerMateriel(op);
        usermetierimpl.reqMatRefuser((OpEntree)this.getCurentOperation(), this.getMotif());
        this.setCurentOperation(null);
        this.setMotif(null);
    }


    public void aModifierPrisEnChargeEntreMat(Operation op) throws Exception
    {
        //usermetierimpl.entrerMateriel(op);

        ((OpEntree)this.getCurentOperation()).getMat().setAModifier(true);
        usermetierimpl.reqMatAModifier((OpEntree)this.getCurentOperation(), this.getMotif());

        this.setCurentOperation(null);
        this.setMotif(null);
    }



    public void validateAttributionDetenteur(OpAttribution attr)
    {
        //usermetierimpl.attriuberMateriel(attr);
    	try {
    		usermetierimpl.attriuberMateriel((OpAttribution)this.getCurentOperation());
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    	}
        
        this.setCurentOperation(null);

    }

    public void refuseAttributionDetenteur(OpAttribution attr) throws Exception
    {
        //usermetierimpl.attriuberMateriel(attr);
        usermetierimpl.reqAttrRefuser((OpAttribution)this.getCurentOperation(), this.getMotif());
        this.setCurentOperation(null);
        this.setMotif(null);
    }


    public void aModifierAttributionDetenteur(OpAttribution attr) throws Exception
    {
        //usermetierimpl.attriuberMateriel(attr);
        usermetierimpl.reqAttrAModifier((OpAttribution)this.getCurentOperation(), this.getMotif());
        this.setCurentOperation(null);
        this.setMotif(null);
    }


    public void validateDechargeSortie(OpSortie sortie){
        //usermetierimpl.sortirMateriel(sortie);
    	System.out.println("VALDATION DECHARGE SORTIE");
    	try {
    		usermetierimpl.sortirMateriel((OpSortie)this.getCurentOperation());
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}finally {

	        this.setCurentOperation(null);
		}
        
    }

    public String exit(){
        this.setCurentOperation(null);
        this.setMotif(null);
        return "success";
    }


    public void refuseDechargeSortie(OpSortie sortie) throws Exception {
        //usermetierimpl.sortirMateriel(sortie);
        usermetierimpl.reqSortirRefuser((OpSortie)this.getCurentOperation(), this.getMotif());
        this.setCurentOperation(null);
        this.setMotif(null);
    }

    public void aModifierDechargeSortie(OpSortie sortie) throws Exception {
        //usermetierimpl.sortirMateriel(sortie);
        usermetierimpl.reqSortirAModifier((OpSortie)this.getCurentOperation(), this.getMotif());
        this.setCurentOperation(null);
        this.setMotif(null);
    }



    public void validateDetachement(OpDettachement det){
        System.out.println("VALIDATION DETACHEMENT");
    	//usermetierimpl.sortirMateriel(sortie);
        try {
        	usermetierimpl.detacherMateriel((OpDettachement)this.getCurentOperation());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally {
			this.setCurentOperation(null);
	        this.setMotif(null);
		}
        
        //usermetierimpl.sortirMateriel((OpDettachement)this.getCurentOperation());
        
    }


    public void refuseDetachement(OpDettachement det) throws Exception {
        //usermetierimpl.sortirMateriel(sortie);
        usermetierimpl.reqDetRefuser((OpDettachement)this.getCurentOperation(), this.getMotif());
        this.setCurentOperation(null);
        this.setMotif(null);
    }

    public void aModifierDetachement(OpDettachement det) throws Exception {
        //usermetierimpl.sortirMateriel(sortie);
        //usermetierimpl.reqSortirAModifier((OpDettachement)this.getCurentOperation(), this.getMotif());
        this.setCurentOperation(null);
        this.setMotif(null);
    }




    public void setListAllOperation(List<Operation> listAllOperation) {
        this.listAllOperation = listAllOperation;
    }

    public List<Operation> getListAllOperation()
    {
        return usermetierimpl.getListOp();
    }

    private List<Agent> getAllAgents()
    {
        return usermetierimpl.findAllAgents();
    }

    public IUserMetier getUsermetierimpl() {
        return usermetierimpl;
    }

    public void setUsermetierimpl(IUserMetier usermetierimpl) {
        this.usermetierimpl = usermetierimpl;
    }
    /*
    public void setSuivibean(SuiviEditionBean svbean){
        this.suivibean = svbean;
    }
    */
    public void setCurentOperation(Operation operation){
        this.curentOperation = operation;
    }
    public Operation getCurentOperation(){
        return this.curentOperation;
    }

    public void setMotif(String m){
        this.motif = m;
    }
    public String getMotif(){
        return this.motif;
    }

}
//r
