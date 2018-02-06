package com.douane.entite;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.jboss.resteasy.util.Base64;

@Entity
public class Designation {
	@Id
	@SequenceGenerator(allocationSize=1, initialValue=1, sequenceName="designation_id_seq", name="designation_id_seq")
	@GeneratedValue(generator="designation_id_seq", strategy=GenerationType.SEQUENCE)
	private Long idDesignation;
	
	private Float pu;
	private String autre;
	private String renseignement;
	private byte[] image;
	
	@OneToMany(mappedBy="design")
	  private List<Materiel> materiels;
	

	@ManyToOne
	@JoinColumn(name="idNom")
	private Nomenclature nomenMat;
	@ManyToOne
	@JoinColumn(name="idCateg")
	private CategorieMat categorie;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idCar", columnDefinition="integer", nullable = true , insertable=false, updatable=false)
	private TypeMateriel caract;
	@ManyToOne
	@JoinColumn(name="idMarque")
	private Marque marque;
	private String anneeAcquisition;
	@ManyToOne
	@JoinColumn(name="idTypeMateriel")
	private TypeMateriel typematerieladd;
	
	private String especeUnite;
	
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



	public Float getPu() {
		return pu;
	}
	public void setPu(Float pu) {
		this.pu = pu;
	}



	public String getAutre() {
		return autre;
	}
	public void setAutre(String autre) {
		this.autre = autre;
	}



	public String getRenseignement() {
		return renseignement;
	}
	public void setRenseignement(String renseignement) {
		this.renseignement = renseignement;
	}



	public String getAnneeAcquisition() {
		return anneeAcquisition;
	}
	public void setAnneeAcquisition(String anneeAcquisition) {
		this.anneeAcquisition = anneeAcquisition;
	}



	public String getEspeceUnite() {
		return especeUnite;
	}
	public void setEspeceUnite(String especeUnite) {
		this.especeUnite = especeUnite;
	}
	public List<Materiel> getMateriels() {
		return materiels;
	}
	public void setMateriels(List<Materiel> materiels) {
		this.materiels = materiels;
	}
	public void addMateriel(Materiel m) {
		this.materiels.add(m);
		m.setDesign(this);
	}


}
