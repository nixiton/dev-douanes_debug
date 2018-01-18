package com.douane.entite;

import org.jboss.resteasy.util.Base64;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="typeMateriels", discriminatorType=DiscriminatorType.INTEGER)
public class Materiel implements Serializable{
	@Column(unique=true)
	private String code;
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@SequenceGenerator(allocationSize=1, initialValue=1, sequenceName="account_id_seq", name="account_id_seq")
	@GeneratedValue(generator="account_id_seq", strategy=GenerationType.SEQUENCE)
	private Long idMateriel;

	private Float pu;
	private String reference;
	private String numSerie;
	private String autre;
	//private String codification;
	private boolean validation;
	//private boolean aModifier;
	private String renseignement;
	


	private byte[] image;

	private String documentPath;

	public String getImage() throws IOException
	{
		ByteArrayInputStream bais;
		System.out.println("----------------------begint test");
		if(image != null)
		{
			System.out.println("----------------------not null");
			bais = new ByteArrayInputStream(image);
		}
		else
		{
			System.out.println("---------------------- null");
			return null;
		}
		try {
			//BufferedImage imagebuff = ImageIO.read(bais);
			System.out.println("----------------------not null 1");
			String encodedImage;
			System.out.println("----------------------not null 2");
			//BufferedImage imBuff = ImageIO.read(bais);
			System.out.println("----------------------not null 3");
			BufferedImage resizedImg = resize(ImageIO.read(bais), 275, 75);
			System.out.println("----------------------not null 4");
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			System.out.println("----------------------not null 5");
			if (resizedImg == null) {
				return null;
			}
			ImageIO.write(resizedImg, "jpg", os);
			System.out.println("----------------------not null 6");
			encodedImage = new String(Base64.encodeBytes(os.toByteArray()));
			System.out.println("----------------------not null 7");
			return encodedImage;
		}
		catch(NullPointerException e)
		{
			return "";
		}
		//return imagebuff;
		//return image;
	}

	private  BufferedImage resize(BufferedImage image, int newWidth, int newHeight)
	{
		int currentWidth = image.getWidth();
		int currentHeight = image.getHeight();
		BufferedImage newImage = new BufferedImage(newWidth, newHeight, image.getType());
		Graphics2D graphics2d = newImage.createGraphics();
		graphics2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2d.drawImage(image, 0, 0, newWidth, newHeight, 0, 0,
				currentWidth, currentHeight, null);
		graphics2d.dispose();
		return newImage;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}



	@ManyToOne
	@JoinColumn(name="idNom")
	private Nomenclature nomenMat;
	@ManyToOne
	@JoinColumn(name="idEtat")
	private EtatMateriel etat;
	@ManyToOne
	@JoinColumn(name="idCateg")
	private CategorieMat categorie;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idCar", columnDefinition="integer", nullable = true , insertable=false, updatable=false)
	private TypeMateriel caract;
	@ManyToOne
	@JoinColumn(name="idMarque")
	private Marque marque;

	//localisation
	@ManyToOne
	@JoinColumn(name="idDirection")
	private Direction direc;
	@ManyToOne
	@JoinColumn(name="idSevice")
	private Service serv;
	@ManyToOne
	@JoinColumn(name="idBureau")
	private Bureau bureau;



	private String anneeAcquisition;
	@ManyToOne
	@JoinColumn(name="idTypeMateriel")
	private TypeMateriel typematerieladd;



	/*@ManyToOne
	@JoinColumn(name="idFournisseur")
	private Fournisseur fourni;
	*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="imDetenteur")
	private Agent detenteur;

	@ManyToOne
	@JoinColumn(name="imDepositaire")
	//@Transient
	private Agent dc;

	/*
	 * MANAGE LATER
	Collection Operations
	File documents;
	Photo Blob
	Codification
	List<Agent> Liste des agents detenteurs successifs
	Code Barre
	Localisation
	*/

	public Long getIdMateriel(){
		return this.idMateriel;
	}
	public void setidMateriel(Long idMateriel){ this.idMateriel = idMateriel;}
	public Float getPu() {
		return pu;
	}
	public void setPu(Float pu) {
		this.pu = pu;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getNumSerie() {
		return numSerie;
	}
	public void setNumSerie(String numSerie) {
		this.numSerie = numSerie;
	}
	public String getAutre() {
		return autre;
	}
	public void setAutre(String autre) {
		this.autre = autre;
	}
	public Nomenclature getNomenMat() {
		return nomenMat;
	}
	public void setNomenMat(Nomenclature nomenMat) {
		this.nomenMat = nomenMat;
	}
	public Agent getDetenteur() {
		return detenteur;
	}
	public void setDetenteur(Agent detenteur) {
		this.detenteur = detenteur;
	}
	public EtatMateriel getEtat() {
		return etat;
	}
	public void setEtat(EtatMateriel etat) {
		this.etat = etat;
	}
	public TypeMateriel getCaract() {
		return caract;
	}
	public void setCaract(TypeMateriel caract) {
		this.caract = caract;
	}
	public Agent getDc() {
		return dc;
	}
	public void setDc(Agent dc) {
		this.dc = dc;
	}

	@Override
	public boolean equals(Object o) {
		/*if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Materiel materiel = (Materiel) o;


		if (!idMateriel.equals(materiel.idMateriel)) return false;

		return leref.equals(materiel.leref);*/
		System.out.println("herherMat");
		if (this.getIdMateriel().equals(((Materiel)o).getIdMateriel()))
			return true;
		return false;

	}


	public String getAnneeAcquisition(){
		return anneeAcquisition;
	}

	public void setAnneeAcquisition(String annee){
		this.anneeAcquisition = annee;
	}


	/*public String getCodification() {
            return codification;

        }
        public void setCodification(String codification) {
            this.codification = codification;
        }*/
	public Marque getMarque() {
		return marque;
	}
	public void setMarque(Marque marque) {
		this.marque = marque;
	}


	public Materiel(Float pu, String reference, String numSerie, String autre, String codification,
					Nomenclature nomenMat, EtatMateriel etat, TypeMateriel caract, Agent dc, Marque m) {
		super();
		this.pu = pu;
		this.reference = reference;
		this.numSerie = numSerie;
		this.autre = autre;
		//this.codification = codification;
		this.nomenMat = nomenMat;
		this.etat = etat;
		this.caract = caract;
		this.dc = dc;
		this.marque = m;
		//setValidation(false);
	}
	public Materiel() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Transient
	protected String leref;


	public String getLeref() {
		return leref;
	}
	public void setLeref(String leref) {
		this.leref = leref;
	}
	public boolean isValidation() {
		return validation;
	}
	public void setValidation(boolean validation) {
		this.validation = validation;
	}

	/*public boolean isAModifier() {
		return aModifier;
	}
	public void setAModifier(boolean aModifier) {
		this.aModifier = aModifier;
	}*/


	public Direction getDirec() {
		return direc;
	}
	public void setDirec(Direction direc) {
		this.direc = direc;
	}
	public Service getServ() {
		return serv;
	}
	public void setServ(Service serv) {
		this.serv = serv;
	}
	public Bureau getBureau() {
		return bureau;
	}
	public void setBureau(Bureau bureau) {
		this.bureau = bureau;
	}
	public String getCode() {
		return code;
	}
	/*
        public Fournisseur getFourni() {
            return fourni;
        }
        public void setFourni(Fournisseur fourni) {
            this.fourni = fourni;
        }
        */
	public void generateCode(Long numerotype) {
		String codeDirection = "xxx";
		String codeTypeMateriel = "xxx";
		String anneeacquisition = "xxx";
		String codeNomenclature = "xxx";
		if(this.getDirec()!=null) {
			codeDirection = this.direc.getCodeDirection();
		}
		if(this.getTypematerieladd()!=null) {
			codeTypeMateriel = this.getTypematerieladd().getCodeTypeMate();
			codeNomenclature = this.getTypematerieladd().getNomenclaureParent().getNomenclature();
		}
		if(this.getAnneeAcquisition()!=null) {
			anneeacquisition = this.getAnneeAcquisition();
		}
		this.code = "COD"+
				"DIR"+codeDirection+
				"NOM"+codeNomenclature+
				"TM"+codeTypeMateriel+
				"INC"+numerotype+
				"AA"+anneeacquisition;
	}

	public CategorieMat getCategorie() {
		return categorie;
	}
	public void setCategorie(CategorieMat categorie) {
		this.categorie = categorie;
	}
	public String getRenseignement() {
		return renseignement;
	}
	public void setRenseignement(String renseignement) {
		this.renseignement = renseignement;
	}

	public String getDocumentPath() {
		return documentPath;
	}

	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}

	public TypeMateriel getTypematerieladd() {
		return typematerieladd;
	}

	public void setTypematerieladd(TypeMateriel typematerieladd) {
		this.typematerieladd = typematerieladd;
	}
	
	//okay
	@ManyToOne
	@JoinColumn(name="opentreeid")
	private OpEntree myoperationEntree;

	public OpEntree getMyoperationEntree() {
		return myoperationEntree;
	}

	public void setMyoperationEntree(OpEntree myoperationEntree) {
		this.myoperationEntree = myoperationEntree;
	}
	
	public String getEspeceUnite() {
		return especeUnite;
	}

	public void setEspeceUnite(String especeUnite) {
		this.especeUnite = especeUnite;
	}



	public String getOrigine() {
		return origine;
	}

	public void setOrigine(String origine) {
		this.origine = origine;
	}



	private String especeUnite;
	private String origine;
	
	

}
