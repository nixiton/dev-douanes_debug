package com.douane.managed.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.douane.entite.OpAttribution;
import com.douane.entite.OpSortie;
import com.douane.entite.Operation;

@ManagedBean(name = "docbean")
@SessionScoped
public class DocumentsBean {
	
	private OpAttribution curentOperationAttrToPdf;
	
	private OpSortie curentOpeationSortieToPdf;
	
	public String generateDetenteur(Operation operation){
		setCurentOperationAttrToPdf((OpAttribution)operation);
            return "dialog6";
    }
	public String generateSortie(Operation operation) {
		setCurentOpeationSortieToPdf((OpSortie)operation);
		return "ordreSortie";
	}

	public OpAttribution getCurentOperationAttrToPdf() {
		return curentOperationAttrToPdf;
	}

	public void setCurentOperationAttrToPdf(OpAttribution curentOperationAttrToPdf) {
		this.curentOperationAttrToPdf = curentOperationAttrToPdf;
	}

	public OpSortie getCurentOpeationSortieToPdf() {
		return curentOpeationSortieToPdf;
	}

	public void setCurentOpeationSortieToPdf(OpSortie curentOpeationSortieToPdf) {
		this.curentOpeationSortieToPdf = curentOpeationSortieToPdf;
	}

}
