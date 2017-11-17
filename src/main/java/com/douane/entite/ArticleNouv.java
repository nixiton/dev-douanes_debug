package com.douane.entite;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("AN")
public class ArticleNouv extends Article{
    @ManyToOne
    @JoinColumn(name="idFournisseur")
    private Fournisseur fournisseur;

    private Float prix;

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public ArticleNouv(Fournisseur fournisseur, Float prix) {
        super();
        this.fournisseur = fournisseur;
        this.prix = prix;
    }

    public ArticleNouv() {
        super();
        // TODO Auto-generated constructor stub
    }



}