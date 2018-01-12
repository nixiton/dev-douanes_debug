package com.douane.entite;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="typeArt", discriminatorType=DiscriminatorType.INTEGER)
public class Article  implements Serializable{

	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@SequenceGenerator(allocationSize=1, initialValue=1, sequenceName="account_idart_seq", name="account_idart_seq")
	@GeneratedValue(generator="account_idart_seq", strategy=GenerationType.SEQUENCE)
	private Long idArticle;

    public void setIdArticle(Long idArticle) {
		this.idArticle = idArticle;
	}

	@ManyToOne
    @JoinColumn(name="idcode")
    private CodeArticle codeArticle;

    private Long nombre;

    private boolean validation;
    
    @ManyToOne
	@JoinColumn(name="idMarqueArt")
    private Marque marqueArticle;
    
    private String caracteristiqueArticle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="imBeneficiaire")
    private Agent beneficiaire;

    @ManyToOne
    @JoinColumn(name="imDepositaire")
    //@Transient
    private Agent dc;

    @ManyToOne
	@JoinColumn(name="idDirectionArt")
	private Direction direcArt;
    
    public Direction getDirecArt() {
		return direcArt;
	}

	public void setDirecArt(Direction direcArt) {
		this.direcArt = direcArt;
	}

	public Agent getDc() {
        return dc;
    }

    public void setDc(Agent dc) {
        this.dc = dc;
    }

    public CodeArticle getCodeArticle() {
        return codeArticle;
    }

    public void setCodeArticle(CodeArticle codeArticle) {
        this.codeArticle = codeArticle;
    }

    public Long getNombre() {
        return nombre;
    }

    public void setNombre(Long nombre) {
        this.nombre = nombre;
    }

    public Long getIdArticle() {
        return idArticle;
    }

    public boolean isValidation() {
        return validation;
    }

    public void setValidation(boolean validation) {
        this.validation = validation;
    }


    public Agent getBeneficiaire() {
        return beneficiaire;
    }

    public void setBeneficiaire(Agent beneficiaire) {
        this.beneficiaire = beneficiaire;
    }

	public Marque getMarqueArticle() {
		return marqueArticle;
	}

	public void setMarqueArticle(Marque marqueArticle) {
		this.marqueArticle = marqueArticle;
	}

	public String getCaracteristiqueArticle() {
		return caracteristiqueArticle;
	}

	public void setCaracteristiqueArticle(String caracteristiqueArticle) {
		this.caracteristiqueArticle = caracteristiqueArticle;
	}

	@Override
	public boolean equals(Object o) {

		System.out.println("herherArticle");
		if (this.getIdArticle().equals(((Article)o).getIdArticle()))
			return true;
		return false;

	}

}
