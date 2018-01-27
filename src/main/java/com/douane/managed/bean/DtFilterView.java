package com.douane.managed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.douane.entite.Agent;
import com.douane.entite.EtatMateriel;
import com.douane.entite.Financement;
import com.douane.entite.Materiel;
import com.douane.entite.OpAttribution;
import com.douane.entite.OpDettachement;
import com.douane.entite.OpEntree;
import com.douane.entite.OpSaisie;
import com.douane.entite.OpSortie;
import com.douane.entite.Operation;
import com.douane.entite.Referentiel;
import com.douane.metier.referentiel.IRefMetier;
import com.douane.metier.user.IUserMetier;
import com.douane.model.EtatOperation;
import com.douane.requesthttp.RequestFilter;

@ManagedBean(name="dtFilterView")
@ViewScoped
public class DtFilterView implements Serializable{
	 private List<Class> operations;
	 private List<EtatOperation> etats;
	 private List<Agent> operateurs;
     
	 private List<Operation> filteredOperations;
	 private List<Materiel> filteredMateriels;
	 
	 @ManagedProperty(value="#{usermetier}")
	IUserMetier usermetierimpl;
	 
	 @ManagedProperty(value="#{refmetier}")
	    IRefMetier refmetierimpl;


	public IRefMetier getRefmetierimpl() {
		return refmetierimpl;
	}

	public void setRefmetierimpl(IRefMetier refmetierimpl) {
		this.refmetierimpl = refmetierimpl;
	}

	public IUserMetier getUsermetierimpl() {
		return usermetierimpl;
	}

	public void setUsermetierimpl(IUserMetier usermetierimpl) {
		this.usermetierimpl = usermetierimpl;
	}

	public List<Class> getOperations() {
		List<Class> ops = new ArrayList<Class>();
		ops.add(OpEntree.class);
		ops.add(OpSortie.class);
		ops.add(OpSaisie.class);
		ops.add(OpAttribution.class);
		ops.add(OpDettachement.class);
		return ops;
	}

	public void setOperations(List<Class> operations) {
		this.operations = operations;
	}

	public List<Operation> getFilteredOperations() {
		return filteredOperations;
	}

	public void setFilteredOperations(List<Operation> filteredOperations) {
		this.filteredOperations = filteredOperations;
	}

	public List<EtatOperation> getEtats() {
		List<EtatOperation> ops = Arrays.asList(EtatOperation.values());
	    return ops;
    }
    

	public void setEtats(List<EtatOperation> etats) {
		this.etats = etats;
	}
	public String getOperationName(Class op) {
		if(op ==OpEntree.class) {
			return "Opération Entrée";
		}else if(op==OpSortie.class) {
			return "Opération Sortie";
		}else if(op==OpSaisie.class) {
			return "Saisie de référentiel";
		}else if(op==OpAttribution.class) {
			return "Opération attribution";
		}else if(op==OpDettachement.class) {
			return "Opération Déttachement";
		}
		return "";
	}
	
	public List<Agent> getAllOperatorByDirection() {
		Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
		
		return usermetierimpl.listAgentByDirection(agent.getDirection());
	}

	public List<Agent> getOperateurs() {
		Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
		
		return usermetierimpl.listAgentByDirection(agent.getDirection());
	}

	public void setOperateurs(List<Agent> operateurs) {
		this.operateurs = operateurs;
	}

	public List<Materiel> getFilteredMateriels() {
		return filteredMateriels;
	}

	public void setFilteredMateriels(List<Materiel> filteredMateriels) {
		this.filteredMateriels = filteredMateriels;
	}

}
