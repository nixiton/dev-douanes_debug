package com.douane.managed.bean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.render.FacesRenderer;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.hibernate.mapping.Set;
import org.springframework.transaction.annotation.Transactional;

import com.douane.entite.OpAttribution;
import com.douane.entite.OpEntree;
import com.douane.entite.OpSortie;
import com.douane.entite.Operation;
import com.douane.managed.bean.JasperData.JournalMatiereData;
import com.douane.managed.bean.JasperData.LivreAnnuelData;
import com.douane.managed.bean.JasperData.LivreAnnuelData.LivreAnnuel;
import com.douane.managed.bean.JasperData.OrdreEntreeData;
import com.douane.managed.bean.form.GrandLivreBean;
import com.douane.managed.bean.form.PdfFormBean;
import com.douane.managed.bean.saisieRef.JournalFormBean;
import com.douane.managed.bean.saisieRef.OrdreSortieFormBean;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
/*
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

*/
/**
 * @author javaQuery
 * @date 24nd November, 2015
 * @Github: https://github.com/javaquery/Examples
 */
@ManagedBean(name="JasperTableExampleBean")
@RequestScoped
@Transactional
public class JasperTableExampleBean implements Serializable{
	public JasperTableExampleBean() {
		super();
		this.ordreEB = new ordreEntreeBean();
		this.ordreS = new OrdreSortieFormBean();
		this.journal = new JournalFormBean();
		this.livre = new GrandLivreBean();
		this.pdfForm = new PdfFormBean();
	}
	private PdfFormBean pdfForm;
	public PdfFormBean getPdfForm() {
		return pdfForm;
	}
	public void setPdfForm(PdfFormBean pdfForm) {
		this.pdfForm = pdfForm;
	}
	private GrandLivreBean livre;
	public GrandLivreBean getLivre() {
		return livre;
	}
	public void setLivre(GrandLivreBean livre) {
		this.livre = livre;
	}
	private JournalFormBean journal;
	public JournalFormBean getJournal() {
		return journal;
	}
	public void setJournal(JournalFormBean journal) {
		this.journal = journal;
	}
	@ManagedProperty(value="#{ordreEntree}")
	private ordreEntreeBean ordreEB;
	private OrdreSortieFormBean ordreS;
    public OrdreSortieFormBean getOrdreS() {
		return ordreS;
	}
	public void setOrdreS(OrdreSortieFormBean ordreS) {
		this.ordreS = ordreS;
	}
	public ordreEntreeBean getOrdreEB() {
		return ordreEB;
	}
	public void setOrdreEB(ordreEntreeBean ordreEB) {
		this.ordreEB = ordreEB;
	}
	private GACBean gacBean;
    public GACBean getGacBean() {
		return gacBean;
	}
	public void setGacBean(GACBean gacBean) {
		this.gacBean = gacBean;
	}
	public void buildReport() {
    	FacesContext facescontext = FacesContext.getCurrentInstance();
		ExternalContext external = facescontext.getExternalContext();
		HttpSession session = (HttpSession) external.getSession(true);
		HttpServletResponse response = (HttpServletResponse) external.getResponse();
       
    }
    public void saisieMatexReport(OpAttribution op) {
    	String fDepo = "";
    	String fDetent = "";
    	if(op.getOperateur().getPosteny() != null) {
    		fDepo = op.getOperateur().getPosteny().getDesignation();
    	}
    	if(op.getDetenteur().getPosteny() != null) {
    		fDetent = op.getOperateur().getPosteny().getDesignation();
    	}
    	FacesContext facescontext = FacesContext.getCurrentInstance();
		ExternalContext external = facescontext.getExternalContext();
		HttpSession session = (HttpSession) external.getSession(true);
		HttpServletResponse response = (HttpServletResponse) external.getResponse();
		URL url =  this.getClass().getResource("../jasperReport/saisieMatEx.jasper");
		//System.out.println(url.getPath());
        try {
            
            /* Map to hold Jasper report Parameters */
            Map<String, Object> parameters = new HashMap<String, Object>();
            String designation = op.getMat().getDesign().getNomenMat().getNomenclature() + " - "
            		+ op.getMat().getDesign().getTypematerieladd().getDesignation() + " - "
            		+ op.getMat().getDesign().getMarque() + " - "
            		+ op.getMat().getDesign().getRenseignement() + " - "
            		+ op.getMat().getReference()
            		;
            
            //parameters.put("parameter1", htmlString);
            parameters.put("nomDepositaire", op.getOperateur().getNomAgent());
            parameters.put("fonctionDepositaire", fDepo);
            parameters.put("nomDetenteur", op.getDetenteur().getNomAgent());
            parameters.put("fonctionDetenteur", fDetent);
            parameters.put("designationMat", designation);
            parameters.put("especeunite", op.getMat().getDesign().getEspeceUnite());
            parameters.put("pu", op.getMat().getDesign().getPu());
            parameters.put("nbr", 1);
            parameters.put("valeurtotal", op.getMat().getDesign().getPu());
            
            //parameters.put("nomDepositaire", op.getOperateur().getNomAgent());
            /* Using compiled version(.jasper) of Jasper report to generate PDF */
            JasperPrint jasperPrint = JasperFillManager.fillReport(url.getPath(), parameters, new JREmptyDataSource());

            /* outputStream to create PDF */
           // OutputStream outputStream = new FileOutputStream(new File(outputFile));
            /* Write content to PDF file */
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
            response.getOutputStream().close();
            //System.out.println("File Generated");
        }
         catch (JRException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	public void ordreEntreeReport(GACBean gacBean) throws IOException {
		Operation op = gacBean.getCurentOperation1();
		List<Object[]> l = gacBean.getDesingationByOpEntree(op);
		OrdreEntreeData datalist = new OrdreEntreeData(l);
		FacesContext facescontext = FacesContext.getCurrentInstance();
		ExternalContext external = facescontext.getExternalContext();
		HttpSession session = (HttpSession) external.getSession(true);
		HttpServletResponse response = (HttpServletResponse) external.getResponse();
		ServletOutputStream tmp = response.getOutputStream();
		URL url =  this.getClass().getResource("../jasperReport/OrdreEntre.jasper");
		//System.out.println("designation  = "+op.getMat().getOrigine());
		System.out.println("url antsiika :" + url.getPath());
        try {
            
            /* Map to hold Jasper report Parameters */
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put ("dataSource" , datalist.getDataAsDataSource());
            parameters.put("num3", this.ordreEB.getNum3());
            parameters.put("num4", this.ordreEB.getNum4());
            //parameters.put("chap", this.ordreEB.get());
            parameters.put("chap", this.ordreEB.getChap());
            parameters.put("article", this.ordreEB.getArticle());
            parameters.put("paragraphe", this.ordreEB.getParagraphe());
            parameters.put("noumJour", this.ordreEB.getNoumJour());
            parameters.put("approOuService", this.ordreEB.getApproOuService());
            parameters.put("comptable", op.getDirection().getDesignation());
            parameters.put("comptable1", op.getOperateur().getNomAgent());
            parameters.put("matieres", this.ordreEB.getMatieres());
            parameters.put("ordre", this.ordreEB.getOrdre());
            parameters.put("somme", this.ordreEB.getSomme());
            parameters.put("concordance", this.ordreEB.getConcordance());
            parameters.put("date", this.ordreEB.getDate());
            parameters.put("date2", this.ordreEB.getDate2());
            parameters.put("lieu1", this.ordreEB.getLieu1());
            parameters.put("date1", this.ordreEB.getDate1());
            
            /* Using compiled version(.jasper) of Jasper report to generate PDF */
            JasperPrint jasperPrint = JasperFillManager.fillReport(url.getPath(), parameters, new JREmptyDataSource());
            /* outputStream to create PDF */
           // OutputStream outputStream = new FileOutputStream(new File(outputFile));
            /* Write content to PDF file */
            JasperExportManager.exportReportToPdfStream(jasperPrint, tmp);
            //System.out.println("File Generated");
        }
         catch (JRException ex) {
            ex.printStackTrace();
        }
	}
	public void ordreSortieReport(OpSortie op) throws IOException{
		if(op == null) {
			System.out.println("tsy tonga le op an");
		}else {
			System.out.println(op.getOperateur().getIm()+ "\n" + this.ordreS.toString());
		}
		FacesContext facescontext = FacesContext.getCurrentInstance();
		ExternalContext external = facescontext.getExternalContext();
		HttpSession session = (HttpSession) external.getSession(true);
		HttpServletResponse response = (HttpServletResponse) external.getResponse();
		ServletOutputStream tmp = response.getOutputStream();
		URL url =  this.getClass().getResource("../jasperReport/OrdreSortie2.jasper");
		//System.out.println("designation  = "+op.getMat().getOrigine());
        try {
            
            /* Map to hold Jasper report Parameters */
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("materiel", op.getMat());
            parameters.put("num5", this.ordreS.getNum5());
            parameters.put("num6", this.ordreS.getNum6());
            parameters.put("budget", this.ordreS.getBudget());
            parameters.put("chap", this.ordreS.getChapitre());
            parameters.put("article", this.ordreS.getArticle());
            parameters.put("paragraphe", this.ordreS.getParagraphe());
            parameters.put("num2", this.ordreS.getNum2());
            parameters.put("approOuService", this.ordreS.getApproOuService());
            if(op.getDirection().getDesignation() != null)
            	parameters.put("comptable", op.getDirection().getDesignation());
            parameters.put("Comptable", op.getOperateur().getNomAgent());
            parameters.put("matiere",  op.getMat().getDesign().getOrigine());
            //mila alamina ny jsxml sy ny entree
            parameters.put("ordre", this.ordreS.getOrdre());
            parameters.put("somme", this.ordreS.getSomme());
            parameters.put("concordance", this.ordreS.getConformite());
            parameters.put("date1", op.getDate().toString());
            parameters.put("date4", this.ordreS.getNum4());
            parameters.put("lieu", this.ordreS.getLieu());
            parameters.put("date", op.getDate().toString());
            parameters.put("comptable2", op.getOperateur().getNomAgent());
            parameters.put("lieu1", this.ordreS.getLieu2());
            parameters.put("date5", this.ordreS.getDate2());
            
            /* Using compiled version(.jasper) of Jasper report to generate PDF */
            JasperPrint jasperPrint = JasperFillManager.fillReport(url.getPath(), parameters, new JREmptyDataSource());
            /* outputStream to create PDF */
           // OutputStream outputStream = new FileOutputStream(new File(outputFile));
            /* Write content to PDF file */
            JasperExportManager.exportReportToPdfStream(jasperPrint, tmp);
            //System.out.println("File Generated");
        }
         catch (JRException ex) {
            ex.printStackTrace();
        }
	}
	
	public void journalReport(String debut,String fin) throws IOException {	
		this.journal.setDebutDate(debut);
		this.journal.setFinDate(fin);
		FacesContext facescontext = FacesContext.getCurrentInstance();
		ExternalContext external = facescontext.getExternalContext();
		HttpSession session = (HttpSession) external.getSession(true);
		HttpServletResponse response = (HttpServletResponse) external.getResponse();
		ServletOutputStream tmp = response.getOutputStream();
		URL url =  this.getClass().getResource("../jasperReport/Journal.jasper");
		
		try {
            /* Map to hold Jasper report Parameters */
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("budget", this.journal.getBudget());
            parameters.put("chapitre", this.journal.getChapitre());
            parameters.put("article", this.journal.getArticle());
            parameters.put("num2", this.journal.getNum2());
            parameters.put("num3", this.journal.getNum3());
            parameters.put("num4", this.journal.getNum4());
            parameters.put("num5", this.journal.getNum5());
            parameters.put("nbFeuillets", this.journal.getNbFeuillets());
            parameters.put("lieu", this.journal.getLieu());
            parameters.put("date", this.journal.getDate());
            parameters.put("ans", "");
            parameters.put("debutDate", this.journal.getDebutDate());
            parameters.put("date1", "");
            parameters.put("FinDate", this.journal.getFinDate());
            parameters.put("date2", ""); 
            parameters.put("arrete", "");
            parameters.put("lieu2", this.journal.getLieu());
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(url.getPath(), parameters, new JREmptyDataSource());
            JasperExportManager.exportReportToPdfStream(jasperPrint, tmp);
		}
         catch (JRException ex) {
            ex.printStackTrace();
        }
		//return ("#");
	}
	public void journalMatiereReport(String debut,String fin, List<Object[]> l) throws IOException {	
		this.journal.setDebutDate(debut);
		this.journal.setFinDate(fin);
		FacesContext facescontext = FacesContext.getCurrentInstance();
		ExternalContext external = facescontext.getExternalContext();
		HttpSession session = (HttpSession) external.getSession(true);
		HttpServletResponse response = (HttpServletResponse) external.getResponse();
		ServletOutputStream tmp = response.getOutputStream();
		URL url =  this.getClass().getResource("../jasperReport/JournalMatiere.jasper");
		JournalMatiereData data = new JournalMatiereData(l);
		try {
            /* Map to hold Jasper report Parameters */
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("budget", this.journal.getBudget());
            parameters.put("dataSource", data.getDataSource());
            parameters.put("chapitre", this.journal.getChapitre());
            parameters.put("article", this.journal.getArticle());
            parameters.put("num2", this.journal.getNum2());
            parameters.put("num3", this.journal.getNum3());
            parameters.put("num4", this.journal.getNum4());
            parameters.put("num5", this.journal.getNum5());
            parameters.put("nbFeuillets", this.journal.getNbFeuillets());
            parameters.put("lieu", this.journal.getLieu());
            parameters.put("date", this.journal.getDate());
            parameters.put("ans", "");
            parameters.put("debutDate", this.journal.getDebutDate());
            parameters.put("date1", "");
            parameters.put("FinDate", this.journal.getFinDate());
            parameters.put("date2", ""); 
            parameters.put("arrete", "");
            parameters.put("lieu2", this.journal.getLieu());
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(url.getPath(), parameters, new JREmptyDataSource());
            JasperExportManager.exportReportToPdfStream(jasperPrint, tmp);
		}
         catch (JRException ex) {
            ex.printStackTrace();
        }
		//return ("#");
	}
	public void grandLivreReport(List <Object[]> liste) throws IOException {
		FacesContext facescontext = FacesContext.getCurrentInstance();
		ExternalContext external = facescontext.getExternalContext();
		HttpSession session = (HttpSession) external.getSession(true);
		HttpServletResponse response = (HttpServletResponse) external.getResponse();
		ServletOutputStream tmp = response.getOutputStream();
		URL url =  this.getClass().getResource("../jasperReport/GrandLivre.jasper");
		LivreAnnuelData laData = new LivreAnnuelData(liste);
		try {
            /* Map to hold Jasper report Parameters */
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("dataSource",laData.getDataSource());
            parameters.put("budget", this.livre.getBudget());
            parameters.put("chap", this.livre.getChap());
            parameters.put("article", this.livre.getArticle());
            parameters.put("libelle", this.livre.getLibelle());
            parameters.put("subd", this.livre.getSubd());
            parameters.put("materiel", this.livre.getMateriel());
            parameters.put("indication", this.livre.getIndication());
            parameters.put("nbFeuillets", this.livre.getNbFeuillets());
            parameters.put("lieu", this.livre.getLieu());
            parameters.put("date", this.livre.getDate());
            parameters.put("ans", this.livre.getAns());
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(url.getPath(), parameters, new JREmptyDataSource());
            JasperExportManager.exportReportToPdfStream(jasperPrint, tmp);
		}
         catch (JRException ex) {
            ex.printStackTrace();
        }
	}
	public void etatAppreciatifReport() throws IOException {
		//System.out.println(this.pdfForm.toString());
		FacesContext facescontext = FacesContext.getCurrentInstance();
		ExternalContext external = facescontext.getExternalContext();
		HttpSession session = (HttpSession) external.getSession(true);
		HttpServletResponse response = (HttpServletResponse) external.getResponse();
		ServletOutputStream tmp = response.getOutputStream();
		URL url =  this.getClass().getResource("../jasperReport/EtatAppreciatif.jasper");
		try {
            /* Map to hold Jasper report Parameters */
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("materiel", this.pdfForm.getNum1());
            parameters.put("budget", this.pdfForm.getBudget());
            parameters.put("chp", this.pdfForm.getChapitre());
            parameters.put("article", this.pdfForm.getArticle());
            parameters.put("paragraphe", this.pdfForm.getParagraphe());
            parameters.put("num3", this.pdfForm.getNum3());
            parameters.put("num4", this.pdfForm.getNum4());
            parameters.put("num5", this.pdfForm.getNum5());
            parameters.put("num6", this.pdfForm.getNum6());
            parameters.put("num7", this.pdfForm.getNum7());
            parameters.put("num8", this.pdfForm.getNum8());
            parameters.put("num07", this.pdfForm.getNum7());
            parameters.put("num08", this.pdfForm.getNum9());
            parameters.put("somme", this.pdfForm.getSomme());
            parameters.put("somme1", this.pdfForm.getSomme1());
            parameters.put("somme2", this.pdfForm.getSomme2());
            parameters.put("date1", this.pdfForm.getDate());
            
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(url.getPath(), parameters, new JREmptyDataSource());
            JasperExportManager.exportReportToPdfStream(jasperPrint, tmp);
            //tmp.close();
		}
         catch (JRException ex) {
            ex.printStackTrace();
        }
	}
	public void inventaireReport() throws IOException {
		//System.out.println(this.pdfForm.toString());
		FacesContext facescontext = FacesContext.getCurrentInstance();
		ExternalContext external = facescontext.getExternalContext();
		HttpSession session = (HttpSession) external.getSession(true);
		HttpServletResponse response = (HttpServletResponse) external.getResponse();
		ServletOutputStream tmp = response.getOutputStream();
		URL url =  this.getClass().getResource("../jasperReport/Inventaire.jasper");
		try { 
            /* Map to hold Jasper report Parameters */
            Map<String, Object> parameters = new HashMap<String, Object>();;
            parameters.put("budget", this.pdfForm.getBudget());
            parameters.put("exo", this.pdfForm.getNum9());
            parameters.put("chap", this.pdfForm.getChapitre());
            parameters.put("article", this.pdfForm.getArticle());
            parameters.put("paragraphe", this.pdfForm.getParagraphe());
            parameters.put("service", this.pdfForm.getNum1());
            parameters.put("hotel", this.pdfForm.getNum2());
            parameters.put("date", "");
            parameters.put("arret", this.pdfForm.getSomme());
            parameters.put("somme", this.pdfForm.getSomme1());
            parameters.put("lieu", this.pdfForm.getSomme2());
            parameters.put("ans", "0");
            parameters.put("lieu1", this.pdfForm.getLieu());
            parameters.put("date1", this.pdfForm.getDate());
            parameters.put("an1", this.pdfForm.getNum3());
            parameters.put("date3", this.pdfForm.getNum4());
            parameters.put("vu2", "");
            JasperPrint jasperPrint = JasperFillManager.fillReport(url.getPath(), parameters, new JREmptyDataSource());
            JasperExportManager.exportReportToPdfStream(jasperPrint, tmp);
            //tmp.close();
		}
         catch (JRException ex) {
            ex.printStackTrace();
        }
	}
}
