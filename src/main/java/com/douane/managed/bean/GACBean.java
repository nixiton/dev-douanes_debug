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
        usermetierimpl.entrerMateriel(op);
        //usermetierimpl.entrerMateriel((Operation)suivibean.getCurentOperation());
        this.setCurentOperation(null);
        
    }

    public void validatePrisEnChargeEntreMat()
    {
        //usermetierimpl.entrerMateriel(op);
        usermetierimpl.entrerMateriel(this.getCurentOperation());
        this.setCurentOperation(null);
    }

    public void validateAttributionDetenteur(OpAttribution attr)
    {
        //usermetierimpl.attriuberMateriel(attr);
        usermetierimpl.attriuberMateriel(this.getCurentOperation());
        this.setCurentOperation(null);
    }

    public void validateDechargeSortie(OpSortie sortie) throws Exception {
        //usermetierimpl.sortirMateriel(sortie);
        usermetierimpl.sortirMateriel(this.getCurentOperation());
        this.setCurentOperation(null);
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

}
//r
