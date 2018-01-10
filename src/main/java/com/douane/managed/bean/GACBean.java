package com.douane.managed.bean;

import com.douane.entite.*;
import com.douane.metier.user.IUserMetier;
import com.douane.repository.OpRepository;
import com.douane.requesthttp.RequestFilter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.SessionScoped;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.*;



/*__________itext pdf____________*/

import java.io.*;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;


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

    public String setAnneeEtatAp(int t){
        this.Annee = t;
        return "anneeEtatAp";
    }


    public String setAnneeInv(int t){
        this.Annee = t;
        return "anneeInv";
    }

    

    public int getAnnee(){
        return this.Annee;
    }

    public void setListAnnee(List<Integer> listAnnee){
        this.listAnnee = listAnnee;
    }

    public List<Integer> getListAnnee(){
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
        try{
        usermetierimpl.reqMatRefuser((OpEntree)this.getCurentOperation(), this.getMotif());
        this.setCurentOperation(null);
        this.setMotif(null);
        }
        catch(Exception e)
        {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur pour attribution de sortie", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
            FacesContext.getCurrentInstance().addMessage(null, message);

        }
    }


    public void aModifierPrisEnChargeEntreMat(Operation op) throws Exception
    {
        //usermetierimpl.entrerMateriel(op);

        //((OpEntree)this.getCurentOperation()).getMat().setAModifier(true);
        try{
        usermetierimpl.reqMatAModifier((OpEntree)this.getCurentOperation(), this.getMotif());

        this.setCurentOperation(null);
        this.setMotif(null);
        }catch(Exception e)
        {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur pour requete de modification materiel", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
            FacesContext.getCurrentInstance().addMessage(null, message);

        }
    }



    public void validateAttributionDetenteur(OpAttribution attr)
    {
        //usermetierimpl.attriuberMateriel(attr);
    	try {
    		usermetierimpl.attriuberMateriel(attr);
            

    	}catch(Exception e)
        {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur pour attribution de materiel existant", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
            FacesContext.getCurrentInstance().addMessage(null, message);
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
        try {
            usermetierimpl.reqAttrRefuser((OpAttribution) this.getCurentOperation(), this.getMotif());
            this.setCurentOperation(null);
            this.setMotif(null);
        }
        catch(Exception e)
        {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur de requete pour refus d'attribution", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
            FacesContext.getCurrentInstance().addMessage(null, message);

        }
    }


    public void aModifierAttributionDetenteur(OpAttribution attr) throws Exception
    {
        //usermetierimpl.attriuberMateriel(attr);
        try {
            usermetierimpl.reqAttrAModifier((OpAttribution) this.getCurentOperation(), this.getMotif());
            this.setCurentOperation(null);
            this.setMotif(null);
        }
        catch(Exception e)
        {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur de modficiation d'attribution", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
            FacesContext.getCurrentInstance().addMessage(null, message);

        }
    }


    public void validateDechargeSortie(OpSortie sortie){
        //usermetierimpl.sortirMateriel(sortie);
    	System.out.println("VALDATION DECHARGE SORTIE");
    	try {
    		usermetierimpl.sortirMateriel(sortie);
		} catch(Exception e)
        {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur pour operation sortie", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
            FacesContext.getCurrentInstance().addMessage(null, message);

        }finally {

	        this.setCurentOperation(null);
		}
        
    }

    public String exit(){
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$   APPEL EXIT $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

        this.curentOperation = null;
        this.motif =null;
        return null;
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
		} catch(Exception e)
        {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur de detachement materiel", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
            FacesContext.getCurrentInstance().addMessage(null, message);
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

        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

    }

    public void setCurentOperation3(Operation operation){
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
        //return this.curentOperation;
        //get opreation by id;
    	/*if(this.curentOperation!=null) {
    		System.out.println("it s not null and id is "+this.curentOperation.getId());
    		OpEntree e = usermetierimpl.getOperationEntreeById(this.curentOperation.getId());
    		System.out.println("Ok we  got it");
    		if(e.getListMat() ==null) {
    			System.out.println("Fucking null");
    		}
    		else {
    			System.out.println("its not null bb ");
    		}
    		//System.out.println("liste en cours = ");
    		return e;
    	}*/
    	//okay
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
        try {
            usermetierimpl.reqArtAModifier(getOpEntreeArticle(), getMotif());
        }
        catch(Exception e)
        {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur de requete d'article à modifier", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    public void reqSortirArtAModifier() throws Exception {
        try{
        usermetierimpl.reqSortirArtAModifier(getOpSortieArticle(),getMotif());
        }
        catch(Exception e)
        {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur de requete de sortie d'article à modifier", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    public void reqArtRefuser() throws  Exception{
        try{
        usermetierimpl.reqArtRefuser(getOpEntreeArticle(),getMotif());
        }
            catch(Exception e)
            {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur de requete d'article à refuser", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
    }
    public void reqSortirRefuser() throws Exception
    {
        try {
            usermetierimpl.reqSortirRefuser(getOpSortieArticle(), getMotif());
        }catch(Exception e)
        {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur de requete, sortie de matériel refusée", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    public void entrerArticle() throws  Exception
    {
        usermetierimpl.entrerArticle(getOpEntreeArticle());
    }
    public void sortirArticle() throws  Exception
    {
        try {
            usermetierimpl.sortirArticle(getOpSortieArticle());
        }
        catch(Exception e)
        {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur de sortie d'article", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
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

    public void validateArticleENouv()
    {
        usermetierimpl.entrerArticle((OpEntreeArticle) curentOperation);
    }

    public void validateSortieArticleNouv() throws Exception {
        try{
            usermetierimpl.sortirArticle((OpSortieArticle) curentOperation);
        }
        catch(Exception e)
        {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur de sortie d'article", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }


    public void validateSortieArticleEx() throws Exception {
        try{
            usermetierimpl.sortirArticle((OpSortieArticle) curentOperation);
        }
        catch(Exception e)
        {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur de sortie d'article", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }


    //------------DEBUG FARANY
    private StreamedContent filedownload;
    private int fileZipSize;
    private String fileZipPath;

    public StreamedContent getFiledownload() throws IOException {
        if(RequestFilter.getSession().getAttribute("fileZipPath") == null || RequestFilter.getSession().getAttribute("fileZipPath") == "")
            return null;
        try
        {
            FileInputStream fstream = new FileInputStream((String) RequestFilter.getSession().getAttribute("fileZipPath"));
            if (fstream == null)
                return null;
            if (fstream.getChannel().size() == 0)
                return null;

            InputStream stream = new FileInputStream((String) RequestFilter.getSession().getAttribute("fileZipPath"));
            fileZipSize = stream.available();
            filedownload = new DefaultStreamedContent(stream,
                    "application/zip", "doc.zip");
        }catch (FileNotFoundException f)
        {
            return null;
        }
        //RequestFilter.getSession().setAttribute("fileZipPath",null);
        return filedownload;
    }

    public String getFileZipPath() {

        List <Materiel> lstM = ((OpEntree)curentOperation).getListMat();
        FileOutputStream fos = null;
        ZipOutputStream zipOut = null;
        FileInputStream fis = null;
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
        String datetime = ft.format(dNow);
        try {


            File file = new File(datetime);
            String absolutePath = file.getAbsolutePath();

            RequestFilter.getSession().setAttribute("documentpath", absolutePath + ".zip");
            // eto miset an le documentpath
            String a = (String) RequestFilter.getSession().getAttribute("documentpath");

            fos = new FileOutputStream(datetime + ".zip");
            RequestFilter.getSession().setAttribute("fileZipPath",datetime + ".zip");
            zipOut = new ZipOutputStream(new BufferedOutputStream(fos));
            for (Materiel m : lstM) {
                File input = new File(m.getDocumentPath());
                fis = new FileInputStream(input);
                ZipEntry ze = new ZipEntry(input.getName());
                System.out.println("Zipping the file: " + input.getName());
                zipOut.putNextEntry(ze);
                byte[] tmp = new byte[4 * 1024];
                int size = 0;
                while ((size = fis.read(tmp)) != -1) {
                    zipOut.write(tmp, 0, size);
                }
                // zipOut.flush();
                fis.close();
                input.delete();
            }
            zipOut.close();


        }catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (fos != null)
                    fos.close();
            } catch (Exception ex) {

            }
        }
        return datetime + ".zip";
    }


}
//r
