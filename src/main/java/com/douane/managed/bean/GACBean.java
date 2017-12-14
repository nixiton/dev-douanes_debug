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
import java.util.ListIterator;
import java.util.*;



/*__________itext pdf____________*/

import java.io.*;
import java.util.Date;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


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

    private Operation curentOperation1;

    private List<Operation> listAllOperation;


    private List<Materiel> listMaterielByDet;

    private int Annee;

    private List<Integer> listAnnee;



    private String motif;

    private Float total;


    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,Font.BOLD);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,Font.BOLD);



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


    public void setTotal(Float t){
        this.total = t;
    }

    public Float getTotal(){
        return this.total;
    }


    public void setAnnee(int t){
        this.Annee = t;
    }

    public String setAnnee1(int t){
        this.Annee = t;
        return "annee";
    }

    public int getAnnee(){
        return this.Annee;
    }

    public void setListAnnee(List<Integer> listAnnee){
        this.listAnnee = listAnnee;
    }

    public int getListAnnee(){
        this.listAnnee = new ArrayList<Integer>();
        int k = 2017;
        while(k<2022){
            this.listAnnee.add(k);
            k = k+1;
        }
        return this.listAnnee;
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

        //((OpEntree)this.getCurentOperation()).getMat().setAModifier(true);
        usermetierimpl.reqMatAModifier((OpEntree)this.getCurentOperation(), this.getMotif());

        this.setCurentOperation(null);
        this.setMotif(null);
    }



    public void validateAttributionDetenteur(OpAttribution attr)
    {
        //usermetierimpl.attriuberMateriel(attr);
    	try {

            



            /*String FILE = "1.pdf";

            File yourFile = new File("/pages/secure/CM/DC/","1.pdf");

            yourFile.createNewFile(); 


            File file = File.createTempFile("teste", "pdf", new File("pages/secure/CM/DC/"));


            ((OpAttribution)this.getCurentOperation()).setDetenteurEffectif(FILE);

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            addContent(document);

            document.close();*/




    		usermetierimpl.attriuberMateriel((OpAttribution)this.getCurentOperation());
    	}catch(Exception e){
    		System.out.println("EEEEEEEEERRRRRRRRRRRRRRROOOOOOOOOOORRRRRRRRR *************:"+e.getMessage()+"*******");
            //e.printStackTrace();
    	}


        
        this.setCurentOperation(null);

    }


/*++++++++++++++++++++++++++++++++++++++++PDF++++++++++++++++++++++++++++++++++++++++++*/


    private static void addContent(Document document) throws DocumentException {
        Anchor anchor = new Anchor("First Chapter", catFont);
        anchor.setName("First Chapter");

        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

        Paragraph subPara = new Paragraph("Subcategory 1", subFont);
        Section subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("Hello"));

        subPara = new Paragraph("Subcategory 2", subFont);
        subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("Paragraph 1"));
        subCatPart.add(new Paragraph("Paragraph 2"));
        subCatPart.add(new Paragraph("Paragraph 3"));

        // add a table
        createTable(subCatPart);

        // now add all this to the document
        document.add(catPart);

    }

    private static void createTable(Section subCatPart)
            throws BadElementException {
        PdfPTable table = new PdfPTable(3);

        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);

        PdfPCell c1 = new PdfPCell(new Phrase("Table Header 1"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Table Header 2"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Table Header 3"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);

        table.addCell("1.0");
        table.addCell("1.1");
        table.addCell("1.2");
        table.addCell("2.1");
        table.addCell("2.2");
        table.addCell("2.3");

        subCatPart.add(table);

    }




/*++++++++++++++++++++++++++++++++++++++++END PDF++++++++++++++++++++++++++++++++++++++++++*/






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
    		usermetierimpl.sortirMateriel(sortie);
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

    public void setCurentOperation1(Operation operation){
        this.curentOperation1 = operation;

    }

    public String setCurentOperation2(Operation operation){
        setCurentOperation(operation);

        
        if(usermetierimpl.getListMatByDet(((OpAttribution)getCurentOperation()).getDetenteur())!=null){
            this.setListMaterielByDet(usermetierimpl.getListMatByDet(((OpAttribution)getCurentOperation()).getDetenteur()));
            /*ListIterator<Materiel> it = this.getListMaterielByDet().listIterator();
            if (it!=null) {
                this.setTotal(Float.parseFloat("0"));
               while(it.hasNext()){
                 setTotal(this.total+(Float)(it.next().getPu()));
              } 
            }*/ 
            return "dialog";
        }

        return null;

    }


    public String setCurentOperationOrdre(Operation operation){
        setCurentOperation1(operation);
            return "ordre";
    }

    public String setCurentOperationSortie(Operation operation){
        setCurentOperation1(operation);
            return "ordreSortie";
    }

    public void setListMaterielByDet(List<Materiel> listMateriel) {
        this.listMaterielByDet= listMateriel;
    }


    public List<Materiel> getListMaterielByDet() {
        //List<Materiel> listmatcorrespondant;
        if(listMaterielByDet==null){
            return usermetierimpl.getListMat();
        }
        else{
            //return usermetierimpl.getListMatByDet(getDetenteur());
            return listMaterielByDet;
        }
    }

    public Operation getCurentOperation(){
        return this.curentOperation;
    }

    public Operation getCurentOperation1(){
        return this.curentOperation1;
    }


    public void setMotif(String m){
        this.motif = m;
    }
    public String getMotif(){
        return this.motif;
    }


    //----------------GRAND II --------------------
    Fournisseur fournisseur;

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

    Float prix;

    public OpEntreeArticle getOpEntreeArticle() {
        return opEntreeArticle;
    }

    public void setOpEntreeArticle(OpEntreeArticle opEntreeArticle) {
        this.opEntreeArticle = opEntreeArticle;
    }

    public OpSortieArticle getOpSortieArticle() {
        return opSortieArticle;
    }

    public void setOpSortieArticle(OpSortieArticle opSortieArticle) {
        this.opSortieArticle = opSortieArticle;
    }

    private OpEntreeArticle opEntreeArticle;
    private OpSortieArticle opSortieArticle;

    public CodeArticle getCodeArticle() {
        return codeArticle;
    }

    public void setCodeArticle(CodeArticle codeArticle) {
        this.codeArticle = codeArticle;
    }

    private CodeArticle codeArticle;
    public OpEntreeArticle addRequeteOpEntree()
    {
        ArticleNouv a = new ArticleNouv();
        Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
        a.setFournisseur(getFournisseur());
        a.setPrix(getPrix());
        return usermetierimpl.reqEntrerArticle(a,agent);
    }

    public void validateArticleSaisieExistant()
    {
        OpEntreeArticle o = addRequeteOpEntree();
        usermetierimpl.entrerArticle(o);
    }
    public void reqArtAModifier() throws Exception {
        usermetierimpl.reqArtAModifier(getOpEntreeArticle(),getMotif());
    }
    public void reqSortirArtAModifier() throws Exception {
        usermetierimpl.reqSortirArtAModifier(getOpSortieArticle(),getMotif());
    }
    public void reqArtRefuser() throws  Exception{
        usermetierimpl.reqArtRefuser(getOpEntreeArticle(),getMotif());
    }
    public void reqSortirRefuser() throws Exception
    {
        usermetierimpl.reqSortirRefuser(getOpSortieArticle(),getMotif());
    }
    public void entrerArticle() throws  Exception
    {
        usermetierimpl.entrerArticle(getOpEntreeArticle());
    }
    public void sortirArticle() throws  Exception
    {
        usermetierimpl.sortirArticle(getOpSortieArticle());
    }


    public void addArticleEx()
    {
        ArticleEx a = new ArticleEx();

        Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
        a.setCodeArticle(getCodeArticle());
        //a.setTypeObjet(getTypeObjet());
        usermetierimpl.reqEntrerArticle(a,agent);
    }
    Nomenclature nomenclatureP;
    public Nomenclature getNomenclatureP() {
        return nomenclatureP;
    }

    public void setNomenclatureP(Nomenclature nomenclatureP) {
        this.nomenclatureP = nomenclatureP;
    }
    public void onTypeMaterielChange() {

        this.setNomenclatureP(nomenclatureP);
    }
}
//r
