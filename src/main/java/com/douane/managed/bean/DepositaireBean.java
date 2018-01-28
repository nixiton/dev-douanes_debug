package com.douane.managed.bean;

import com.douane.entite.*;
import com.douane.metier.fournisseur.IFournisseurMetier;
import com.douane.metier.listeDetenteur.DetenteurMetier;
import com.douane.metier.listeDetenteur.IDetenteurMetier;
import com.douane.metier.marque.IMarqueMetier;
import com.douane.metier.nomenclature.INomenclatureMetier;
import com.douane.metier.referentiel.IRefMetier;
import com.douane.metier.typeMateriel.ITypeMaterielMetier;
import com.douane.metier.user.IUserMetier;
import com.douane.model.DocumentModel;
//import com.douane.model.UploadedFileByte;
import com.douane.requesthttp.RequestFilter;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.JDBCException;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.component.html.HtmlSelectManyCheckbox;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.Part;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


import java.util.Calendar;


/**
 * Created by hasina on 10/29/17.
 */
@ManagedBean(name = "depositaireBean")
@SessionScoped
@PropertySource("classpath:config.properties")
public class DepositaireBean {

	@Autowired
	public Environment env;

	@Value("${fileupload.size}")
	private String fileupload;
	// @Autowired
	// ConfigurableApplicationContext context;

	private String fileUploadSize;

	private static final String SUCCESS = "success";
	private static final String ERROR = "error";

	//@Autowired
	//@ManagedProperty(value="#{typematerielmetier}")
	ITypeMaterielMetier typematerielmetier;

	//@Autowired
	@ManagedProperty(value="#{nomenclaturemetier}")
	INomenclatureMetier nomenclaturemetier;

	//@Autowired
	@ManagedProperty(value="#{marquemetier}")
	IMarqueMetier marquemetier;

	//@Autowired
	@ManagedProperty(value="#{usermetier}")
	IUserMetier usermetierimpl;

	@ManagedProperty(value="#{detenteurmetier}")
	IDetenteurMetier detenteurmetierimpl;

	//@Autowired
	@ManagedProperty(value="#{fournisseurmetier}")
	IFournisseurMetier fournisseurmetierimpl;

	//@Autowired
	@ManagedProperty(value="#{refmetier}")
	IRefMetier refmetierimpl;
	private List<Localite> listLocalite;



	private String anneeAcquisition;


	public String getAnneeAcquisition(){
		return anneeAcquisition;
	}

	public void setAnneeAcquisition(String annee){
		this.anneeAcquisition = annee;
	}




	public IRefMetier getRefmetierimpl() {
		return refmetierimpl;
	}

	public void setRefmetierimpl(IRefMetier refmetierimpl) {
		this.refmetierimpl = refmetierimpl;
	}


	private int imgPosition = 0;

	public int getImgPosition(){
		return imgPosition;
	}

	public void setImgPosition(int i){
		imgPosition =i;
	}

	/* attribute for file upload */
	private static final long serialVersionUID = 1L;

	private String name;
	private UploadedFile document;
	ArrayList<DocumentModel> documentList = new ArrayList<DocumentModel>();
	ArrayList<DocumentModel> imageList = new ArrayList<DocumentModel>();
	ArrayList<DocumentModel> documentFacList = new ArrayList<DocumentModel>();
	private Float unitPrice;

	private String reference;
	private String numSerie;
	private String carac;

	private String renseignement;
	private EtatMateriel etat;

	private Nomenclature typemateriel;
	private String nomencl;
	private Marque marq;

	private ModeAcquisition acquisition;

	private Financement financement;
	private Fournisseur fournisseur;
	private Float montantFac;
	private String refFacture;
	private String nombPerType;
	private String etatMatPresent;

	private String autre;

	private Part file;

	private byte[] byteDoc;

	private String documentPath;

	HashMap<UploadedFile, byte[]> HashMapFileByteContent = new HashMap<UploadedFile, byte[]>();

	HashMap<String, HashMap<UploadedFile, byte[]>> hashOfHashMapFIle = new HashMap<String, HashMap<UploadedFile, byte[]>>();

	private String caracteristique;

	// localisation
	private Bureau bureau;
	private Service service;
	private Direction direction;

	private List<MaterielEx> listMaterielexistant;

	private List<Materiel> listMaterielByDet;

	private List<Materiel> listAllMaterielValideSansDetenteur;

	private OpEntree curentOrdreEntree;

	private OpSortie curentOrdreSortie;

	private Article curentArticle;


	public void setCurentArticle(Article a){
		this.curentArticle =a;
	}

	public void mySetCurentArticle(Article a){
		this.curentArticle =a;
	}

	public Article getCurentArticle(){
		return this.curentArticle;
	}


	private Article article;


	public void setArticle(Article a){
		this.article =a;
	}

	public Article getArticle(){
		return this.article;
	}


	public List<Materiel> getListMaterielByDet() {
		//List<Materiel> listmatcorrespondant;
		if(usermetierimpl.getListMatByDet(getDetenteur())==null){
			return usermetierimpl.getListMat();
		}
		else{
			return usermetierimpl.getListMatByDet(getDetenteur());
		}
	}

	public void setCurentOrdreEntree(OpEntree o){
		this.curentOrdreEntree = o;
	}

	public String exit(){
		//this.setCurentMateriel(null);
		this.curentMateriel = null;
		this.curentArticle = null;
		setCurentNull();
		setAllNull();



		return "success";
	}

	public void exity(){
		//this.setCurentMateriel(null);
		this.curentMateriel = null;
		this.curentArticle = null;
		setCurentNull();
		setAllNull();
	}

	public OpEntree getCurentOrdreEntree(){
		return this.curentOrdreEntree;
	}

	public void setCurentOrdreSortie(OpSortie o){
		this.curentOrdreSortie = o;
	}

	public OpSortie getCurentOrdreSortie(){
		return this.curentOrdreSortie;
	}

	public void setListMaterielByDet(List<Materiel> listMateriel) {
		this.listMaterielByDet= listMateriel;
	}

	public List<MaterielNouv> getListMaterielnouveau() {
		return usermetierimpl.getListMatNouv();
	}

	public void setListMaterielnouveau(List<MaterielNouv> listMaterielnouveau) {
		this.listMaterielnouveau = listMaterielnouveau;
	}

	public List<Materiel> getListAllMateriel() {
		return usermetierimpl.getListMat();
	}

	public void setListAllMateriel(List<Materiel> listAllMateriel) {
		this.listAllMateriel = listAllMateriel;
	}

	private List<MaterielNouv> listMaterielnouveau;
	private List<Materiel> listAllMateriel;



	private List<Materiel> listAllMaterilValide;
	private Materiel curentMateriel;
	private String nom;
	private String prenom;

	// Sortie Materiel
	private Bureau destination;
	private Direction destinationDirec;
	private Service destinationService;



	private MotifSortie motifSortie;
	private List<MotifSortie> listMotifSortie;

	private Agent detenteur;

	private Materiel materiel;
	// private Materiel materiel;

	private String listParamShouldNotBeNull = "";

	private ArrayList<UploadedFile> uploadedFiles = new ArrayList<UploadedFile>();
	private UploadedFile uploadedFile;

	private List<Materiel> currentListMateriel;

	//Service
	private Service serviceforMat;

	//Current Agent
	private Agent currentAgent;





	private String fileZipPath;

	private Long idMat;
	public Long getIdMat()
	{
		return idMat;
	}
	public void setIdMat(Long id)
	{
		this.idMat = id;
	}

	public String getFileZipPath() {

		if(getIdMat()!=null){
			fileZipPath = usermetierimpl.getMatById(getIdMat()).getDocumentPath();//
			//RequestFilter.getSession().setAttribute("fileZipPath",fileZipPath);
			return usermetierimpl.getMatById(getIdMat()).getDocumentPath();
		}
		else{
			return "";
		}
	}


	public void setFileZipPath(String f){
		this.fileZipPath = f;
	}

	public Agent getCurrentAgent() {
		return (Agent) RequestFilter.getSession().getAttribute("agent");
	}

	public void setCurrentAgent(Agent currentAgent) {
		this.currentAgent = currentAgent;
	}

	public void onGetService()
	{
		setServiceforMat(getServiceforMat());
	}

	public List<TypeMateriel> getListTypeMateriel() {
		return typematerielmetier.findAllTypeMateriel();
	}

	// get nomenclature
	public List<Nomenclature> getNomenclature() {
		return nomenclaturemetier.findAllNomenclatures();
	}

	// get marque
	public List<Marque> getMarque() {
		return marquemetier.findAllMarque();
	}

	// get list detenteur
	public List<Agent> getDetenteurs() {
		return usermetierimpl.findAllAgents();
	}

	public List<Fournisseur> getFOurnisseur() {
		return fournisseurmetierimpl.findAllFournisseur();
	}

	public Float getUnitPrice() {
		return this.unitPrice;
		//return (Float) RequestFilter.getSession().getAttribute("unitPrice");
	}

	public void setUnitPrice(Float unitPrice) {
		//RequestFilter.getSession().setAttribute("unitPrice", unitPrice);
		this.unitPrice = unitPrice;
	}

	public ITypeMaterielMetier getTypematerielmetier() {
		return typematerielmetier;
	}

	public void setTypematerielmetier(ITypeMaterielMetier typematerielmetier) {
		this.typematerielmetier = typematerielmetier;
	}

	public INomenclatureMetier getNomenclaturemetier() {
		return nomenclaturemetier;
	}

	public void setNomenclaturemetier(INomenclatureMetier nomenclaturemetier) {
		this.nomenclaturemetier = nomenclaturemetier;
	}

	public IMarqueMetier getMarquemetier() {
		return marquemetier;
	}

	public void setMarquemetier(IMarqueMetier marquemetier) {
		this.marquemetier = marquemetier;
	}

	public String getReference() {
		return this.reference;
		//return (String) RequestFilter.getSession().getAttribute("reference");
	}

	public void setReference(String reference) {

		//RequestFilter.getSession().setAttribute("reference", reference);
		this.reference = reference;
	}

	public String getNumSerie() {
		return numSerie;
	}

	public void setNumSerie(String numSerie) {

		//RequestFilter.getSession().setAttribute("numSerie", numSerie);
		this.numSerie = numSerie;
	}

	public String getCarac() {
		return carac;
	}

	public void setCarac(String carac) {
		this.carac = carac;
	}

	public IUserMetier getUsermetierimpl() {
		return usermetierimpl;
	}

	public void setUsermetierimpl(IUserMetier usermetierimpl) {
		this.usermetierimpl = usermetierimpl;
	}

	public String getRenseignement() {
		return this.renseignement;
		//return (String) RequestFilter.getSession().getAttribute("renseignement");
	}

	public void setRenseignement(String renseignement) {
		//RequestFilter.getSession().setAttribute("renseignement", renseignement);

		this.renseignement = renseignement;
	}

	public EtatMateriel getEtat() {
		return this.etat;
		//return (EtatMateriel) RequestFilter.getSession().getAttribute("etat");
	}

	public void setEtat(EtatMateriel etat) {

		//RequestFilter.getSession().setAttribute("etat", etat);
		this.etat = etat;
	}

	public Nomenclature getTypemateriel() {
		return this.typemateriel;
		//return (Nomenclature) RequestFilter.getSession().getAttribute("typemateriel");
	}

	public void setTypemateriel(Nomenclature typemateriel) {
		//RequestFilter.getSession().setAttribute("typemateriel", typemateriel);
		this.typemateriel = typemateriel;
	}

	public String getNomencl() {
		return nomencl;
	}

	public void setNomencl(String nomencl) {
		this.nomencl = nomencl;
	}

	public Marque getMarq() {
		return this.marq;
		//return (Marque) RequestFilter.getSession().getAttribute("marq");

	}

	public void setMarq(Marque marq) {

		//RequestFilter.getSession().setAttribute("marq", marq);
		this.marq = marq;
	}

	public ModeAcquisition getAcquisition() {
		return this.acquisition;
		//return (ModeAcquisition) RequestFilter.getSession().getAttribute("acquisition");
	}

	public void setAcquisition(ModeAcquisition acquisition) {
		//RequestFilter.getSession().setAttribute("acquisition", acquisition);
		this.acquisition = acquisition;
	}

	public Financement getFinancement() {
		return this.financement;
		//return (Financement) RequestFilter.getSession().getAttribute("financement");
	}

	public void setFinancement(Financement financement) {
		//RequestFilter.getSession().setAttribute("financement", financement);
		this.financement = financement;
	}

	public Fournisseur getFournisseur() {
		return this.fournisseur;
		//return (Fournisseur) RequestFilter.getSession().getAttribute("fournisseur");
	}

	public void setFournisseur(Fournisseur fournisseur) {
		//RequestFilter.getSession().setAttribute("fournisseur", fournisseur);
		this.fournisseur = fournisseur;
	}

	public Float getMontantFac() {
		return this.montantFac;
		//return (Float) RequestFilter.getSession().getAttribute("montantFac");
	}

	public void setMontantFac(Float montantFac) {
		//RequestFilter.getSession().setAttribute("montantFac", montantFac);
		this.montantFac = montantFac;
	}

	public String getRefFacture() {
		return refFacture;
	}

	public void setRefFacture(String refFacture) {
		this.refFacture = refFacture;
	}

	public String getNombPerType() {
		return nombPerType;
	}

	public void setNombPerType(String nombPerType) {
		this.nombPerType = nombPerType;
	}

	public String getEtatMatPresent() {
		return etatMatPresent;
	}

	public void setEtatMatPresent(String etatMatPresent) {
		this.etatMatPresent = etatMatPresent;
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

	public IFournisseurMetier getFournisseurmetierimpl() {
		return fournisseurmetierimpl;
	}

	public void setFournisseurmetierimpl(IFournisseurMetier fournisseurmetierimpl) {
		this.fournisseurmetierimpl = fournisseurmetierimpl;
	}

	public ArrayList<UploadedFile> getUploadedFiles() {
		ArrayList<UploadedFile> uploadedfiles = (ArrayList<UploadedFile>) RequestFilter.getSession()
				.getAttribute("uploadedFiles");
		if (uploadedfiles != null)
			return uploadedfiles;
		return uploadedFiles;
	}

	public void setUploadedFiles(ArrayList<UploadedFile> uploadedFiles) {
		this.uploadedFiles = uploadedFiles;
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	/*---------------------method for uploading file--------------------------------*/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UploadedFile getDocument() {
		return document;
	}

	public void setDocument(UploadedFile document) {
		this.document = document;
	}

	public ArrayList<DocumentModel> getDocumentList() {
		// PropertyConf dbConfig = (PropertyConf) context.getBean("propertyConf");
		// System.out.println(dbConfig.getFileupload());
		// env = context.getEn;
		//System.out.println(env.getProperty("fileupload.size"));
		//System.out.println(fileupload);
		ArrayList<DocumentModel> documentlist = this.documentList;
		if (documentlist != null) {
			this.setDocumentList(documentlist);
			return documentlist;
		}
		return documentList;
	}

	public void setDocumentList(ArrayList<DocumentModel> documentList) {
		this.documentList = documentList;
	}

	public DepositaireBean() {
		try {
			documentList = initialize();
			imageList = initializeImageFile();
			documentFacList = initializeFacFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<DocumentModel> initialize() throws IOException {

		ArrayList<DocumentModel> list = new ArrayList<DocumentModel>();
		DocumentModel obj = new DocumentModel();
		obj.setSrNo(1);
		obj.setDocumentName("test filename1");
		obj.setRemovable(false);
		list.add(obj);
		obj = new DocumentModel();
		obj.setSrNo(2);
		obj.setDocumentName("test filename2");
		obj.setRemovable(false);
		list.add(obj);
		return list;
	}
	public ArrayList<DocumentModel> initializeImageFile() throws IOException {

		ArrayList<DocumentModel> list = new ArrayList<DocumentModel>();
		DocumentModel obj = new DocumentModel();
		obj.setSrNo(1);
		obj.setDocumentName("test imagefilename1");
		obj.setRemovable(false);
		list.add(obj);

		return list;
	}
	public ArrayList<DocumentModel> initializeFacFile() throws IOException {

		ArrayList<DocumentModel> list = new ArrayList<DocumentModel>();
		DocumentModel obj = new DocumentModel();
		obj.setSrNo(1);
		obj.setDocumentName("test imagefac1");
		obj.setRemovable(false);
		list.add(obj);

		return list;
	}

	public String addNewDoc() throws IOException {

		ArrayList<DocumentModel> list = getDocumentList();
		System.out.println("list count= " + list.size() + list.get(list.size() - 1).getSrNo());
		DocumentModel obj = new DocumentModel();
		obj.setSrNo(list.get(list.size() - 1).getSrNo() + 1);
		obj.setDocumentName("Type Document Name here");
		obj.setInstitute("Type Institute here");
		list.add(obj);

		setDocumentList(list);

		return null;

	}

	public String removeRow(DocumentModel row) {
		documentList.remove(row);
		// updates serial no.
		int i = 0;
		for (DocumentModel fl : documentList) {

			i++;
			fl.setSrNo(i);
		}
		return null;
	}

	public String removeDoc(DocumentModel row) {

		System.out.println("filename to be removed " + row.getDocumentUploadedPath());
		// You can write logic to remove uploaded file from the device. not written
		// here.
		System.out.println(row.getDocumentName());
		row.setDocumentUploadedPath("");
		row.setUploaded(false);

		return null;
	}

	public String removeDocFac(DocumentModel row) {

		System.out.println("filename to be removed " + row.getDocumentUploadedPath());
		// You can write logic to remove uploaded file from the device. not written
		// here.

		//delete uploadedfile from path

		System.out.println(row.getDocumentName());
		row.setDocumentUploadedPath("");
		row.setUploaded(false);

		return null;
	}

	public String upload_image(FileUploadEvent e) throws IOException {
		RequestFilter.getSession().setAttribute("imageMat", e.getFile().getContents());
		return null;
	}
	public String uploadIm_Advanced(FileUploadEvent e) throws IOException
	{
		this.setByteDoc(e.getFile().getContents());

		DocumentModel docObj = (DocumentModel) e.getComponent().getAttributes().get("docObj");

		if (e.getFile().getContents() != null)
			docObj.setByteArrayImage(e.getFile().getContents());

		//document = e.getFile();

		docObj.setUploaded(true);
		docObj.setByteArrayImage(e.getFile().getContents());
		docObj.setDocumentUploadedPath("" + e.getFile().getFileName());

		ArrayList<DocumentModel> imagelist = this.imageList;
		if (imagelist != null)
		{
			this.setImageList(imageList);
		}
		else
		{
			imagelist = this.imageList;
		}

		imageList.set(imageList.indexOf(docObj), docObj);
		//RequestFilter.getSession().setAttribute("imageList", imagelist);
		System.out.println("File Uploaded");

		return null;
	}
	public String uploadDoc_Advanced(FileUploadEvent e) throws IOException {

		DocumentModel docObj = (DocumentModel) e.getComponent().getAttributes().get("docObj");

		if (e.getFile().getContents() != null)
			docObj.setByteArrayImage(e.getFile().getContents());

		document = e.getFile();

		docObj.setUploaded(true);
		docObj.setDocumentUploadedPath("" + e.getFile().getFileName());

		//ArrayList<DocumentModel> documentlist = (ArrayList<DocumentModel>) RequestFilter.getSession()
		//		.getAttribute("documentList");

		ArrayList<DocumentModel> documentlist = documentList;
		if (documentlist != null)
			this.setDocumentList(documentlist);

		documentList.set(documentList.indexOf(docObj), docObj);
		//RequestFilter.getSession().setAttribute("documentList", documentList);
		System.out.println("File Uploaded");

		return null;
	}

	public String uploadFac_Advanced(FileUploadEvent e) throws IOException {

		DocumentModel docObj = (DocumentModel) e.getComponent().getAttributes().get("docObj");

		if (e.getFile().getContents() != null)
			docObj.setByteArrayImage(e.getFile().getContents());

		docFacture = e.getFile();

		docObj.setUploaded(true);
		docObj.setDocumentUploadedPath("" + e.getFile().getFileName());

		ArrayList<DocumentModel> documentFaclist = documentFacList;
		if (documentFaclist != null)
			this.setDocumentList(documentFaclist);

		documentFacList.set(documentFacList.indexOf(docObj), docObj);
		RequestFilter.getSession().setAttribute("documentFacList", documentFacList);
		System.out.println("Facture Uploaded");
		saveFacFile();
		return null;
	}

	public void saveFacFile() throws IOException {
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
		String datetime = ft.format(dNow);

		ArrayList<DocumentModel> documentlist = documentFacList;
		String fileName;
		try{
			if(documentlist != null) {
				for (DocumentModel d : documentlist)
				{
					InputStream is = docFacture.getInputstream();
					fileName = FilenameUtils.getName(d.getDocumentUploadedPath());
					File fileFac = new File(datetime +"_"+ fileName);
					OutputStream out = new FileOutputStream(fileFac);
					facturePath = fileFac.getAbsolutePath();
					int read = 0;
					byte[] bytes = new byte[1024];

					while ((read = is.read(bytes)) != -1) {
						out.write(bytes, 0, read);
					}

					is.close();
					out.flush();
					out.close();


				/*System.out.println("file name" + fileName);
				BufferedOutputStream stream;
				stream = new BufferedOutputStream(new FileOutputStream(new File(datetime +"_"+ fileName)));
				//stream.write(bytes);
				stream.close();*/
				}
			}
		}catch (IOException e){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error file not found", "Facture's file not found ");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Facture's file not found "));
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public String updatePage() throws IOException {

		ArrayList<DocumentModel> list = getDocumentList();

		int i = 0;

		StringBuffer resultTemp = new StringBuffer();

		for (DocumentModel fl : list) {

			i++;

			resultTemp.append(
					i + ". Document Name : " + fl.getDocumentName() + ", File Path: " + fl.getDocumentUploadedPath());
		}

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(resultTemp.toString()));

		// setResult(resultTemp.toString());

		return "success";

	}

	public String uploadFilesDocument() throws IOException {
		String fileName = "";
		/*ArrayList<UploadedFileByte> uploadedfiles = (ArrayList<UploadedFileByte>) RequestFilter.getSession()
				.getAttribute("uploadedFiles");

		HashMap<String, HashMap<UploadedFile, byte[]>> hashOfhashmapfilebytecontent = (HashMap<String, HashMap<UploadedFile, byte[]>>) RequestFilter
				.getSession().getAttribute("hashmapFileBytecontent");*/
		ArrayList<String> filesTozip = new ArrayList<String>();
		ArrayList<DocumentModel> documentlist = this.documentList;
		if(documentlist != null)
		{
			for (DocumentModel d : documentlist) {
				File file = new File("resources_13_11");
				String absolutePath = file.getAbsolutePath();
				String filePath = absolutePath;
				String filePath2 = new File("test").getAbsolutePath();
				byte[] bytes = null;

				if (null != d) {
					bytes = d.getByteArrayImage();
					if(bytes != null)
					{
						fileName = FilenameUtils.getName(d.getDocumentUploadedPath());
						System.out.println("file name" + fileName);
						BufferedOutputStream stream;
						stream = new BufferedOutputStream(new FileOutputStream(new File(filePath + fileName)));
						filesTozip.add(filePath + fileName);
						stream.write(bytes);
						stream.close();
					}
				}
			}
		}
		else
		{
			RequestFilter.getSession().setAttribute("documentpath","");
			filesTozip.add("");
		}

		zipFiles(filesTozip);
		return SUCCESS;
	}

	public void zipFiles(List<String> files) {

		FileOutputStream fos = null;
		ZipOutputStream zipOut = null;
		FileInputStream fis = null;
		try {
			if(files.size() == 0)
			{
				RequestFilter.getSession().setAttribute("documentpath",   null);
			}

			else
			{
				Date dNow = new Date();
				SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
				String datetime = ft.format(dNow);

				File file = new File(datetime);
				String absolutePath = file.getAbsolutePath();

				RequestFilter.getSession().setAttribute("documentpath", absolutePath + ".zip");
				// eto miset an le documentpath
				String a = (String) RequestFilter.getSession().getAttribute("documentpath");

				fos = new FileOutputStream(datetime + ".zip");
				setDocumentPath(datetime + ".zip");

				zipOut = new ZipOutputStream(new BufferedOutputStream(fos));
				for (String filePath : files) {
					File input = new File(filePath);
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
				System.out.println("Done... Zipped the files...");
				RequestFilter.getSession().removeAttribute("documentList");
				//RequestFilter.getSession().removeAttribute("documentpath");
			}
			// response.setHeader("Refresh", "0; URL=http://your-current-page");
		} catch (FileNotFoundException e) {
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
	}

	public byte[] getByteDoc() {
		return this.byteDoc;
	}

	public void setByteDoc(byte[] byteDoc) {
		this.byteDoc = byteDoc;
	}

	public String getAutre() {
		return this.autre;
		//return (String) RequestFilter.getSession().getAttribute("autre");
	}

	public void setAutre(String autre) {
		//RequestFilter.getSession().setAttribute("autre", autre);
		this.autre = autre;
	}

	public String getCaracteristique() {
		return caracteristique;
	}

	public void setCaracteristique(String caracteristique) {
		this.caracteristique = caracteristique;
	}

	public Bureau getBureau() {
		//RequestFilter.getSession().getAttribute("bureau");
		return bureau;
	}

	public void setBureau(Bureau bureau) {
		//RequestFilter.getSession().setAttribute("bureau", bureau);
		this.bureau = bureau;
	}

	public Service getService() {
		return this.service;
		//return (Service) RequestFilter.getSession().getAttribute("service");
	}

	public void setService(Service service) {
		//RequestFilter.getSession().setAttribute("service", service);
		this.service = service;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public List<MaterielEx> getListMaterielexistant() {
		return usermetierimpl.getListMatEx();
	}

	public void setListMaterielexistant(List<MaterielEx> listMaterielexistant) {
		this.listMaterielexistant = listMaterielexistant;
	}

	public Materiel getCurentMateriel() {
		return curentMateriel;
	}

	public void setCurentMateriel(Materiel curentMateriel) {
		RequestFilter.getSession().setAttribute("fileZipPath",curentMateriel.getDocumentPath());
		this.curentMateriel = curentMateriel;
		this.setIdMat(curentMateriel.getIdMateriel());
	}


	public void mySetCurentMateriel(Materiel curentMateriel) {
		System.out.println("SET CURENT MATERIEL  ID = "+curentMateriel.getIdMateriel());
		setCurentMateriel(curentMateriel);
		this.setIdMat(curentMateriel.getIdMateriel());
		//this.curentMateriel = curentMateriel;
	}




	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Bureau getDestination() {
		return destination;
	}

	public void setDestination(Bureau destination) {
		this.destination = destination;
	}

	public Direction getDestinationDirec() {
		return destinationDirec;
	}

	public void setDestinationDirec(Direction destinationDirec) {
		this.destinationDirec = destinationDirec;
	}

	public Service getDestinationService() {
		return destinationService;
	}

	public void setDestinationService(Service destinationService) {
		this.destinationService = destinationService;
	}



	public void setListMotifSortie(List<MotifSortie> listMotifSortie) {
		this.listMotifSortie = listMotifSortie;
	}

	public Agent getDetenteur() {
		return this.detenteur;
	}
	public void setDetenteur(Agent detenteur) {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		System.out.println(FacesContext.getCurrentInstance().toString());
		String userId = ec.getRequestParameterMap().get(1);
		Map<String, String> map = ec.getRequestParameterMap();
		for (Map.Entry<String, String> entry : map.entrySet())
		{
			if(entry.getKey().equals("j_idt58:det_input"))
				System.out.println(entry.getKey() + "/" + entry.getValue());
		}
		String userId1 = ec.getRequestParameterMap().getClass().getName();
		this.detenteur = detenteur;
	}

	public Materiel getMateriel() {
		return materiel;
	}

	public void setMateriel(Materiel materiel) {
		this.materiel = materiel;
	}

	public void onTypeMaterielChange() {

		//this.setNomencl(getTypemateriel().getNomenclature());
		this.setNomencl(getTypematerielToAdd().getNomenclaureParent().getNomenclature());

	}

	public String onDetenteurChange(){
		this.setNom(getDetenteur().getNomAgent());
		this.setPrenom(getDetenteur().getPrenomAgent());
		return null;
	}

	public void onDetenteurDetChange(){
		this.setNom(getDetenteur().getNomAgent());
		this.setPrenom(getDetenteur().getPrenomAgent());
	}

	public void onMaterielChange() {
		this.setMarq(this.getMateriel().getMarque());
		this.setReference(this.getMateriel().getReference());
		this.setNumSerie(this.getMateriel().getNumSerie());
	}

	public void onMotifSortieChange(){

	}



	//list services
	public List<Service> getListServices()
	{
		ArrayList<Referentiel> r = (ArrayList<Referentiel>)refmetierimpl.listRef(new Service());
		List<Service> ds = new ArrayList<Service>();
		for (Object d :  r)
		{
			if(d instanceof Service) {
				ds.add((Service)d);
			}
		}
		return ds;
	}

	public String addIntoListMateriel() throws IOException
	{
		System.out.println("Add it into list materiel");
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");

		uploadFilesDocument();
		ArrayList<DocumentModel> imagelist = this.imageList;
		// agent.setIp()
		MaterielNouv m = new MaterielNouv();
		//System.out.println("---------------SIZE IMAGE BYTE ARRAY="+imagelist.get(0).getByteArrayImage().length);
		if(imagelist !=null)
		{
			m.setImage(imagelist.get(0).getByteArrayImage());
		}
		else
		{
			m.setImage(null);
		}


		m.setAnneeAcquisition(this.getAnneeAcquisition());

		m.setDocumentPath((String) RequestFilter.getSession().getAttribute("documentpath"));
		RequestFilter.getSession().removeAttribute("documentpath");
		m.setAutre(getAutre());
		m.setBureau(getBureau());
		m.setFournisseur(getFournisseur());
		// m.setDirec(getDirection());
		//m.setDirec(agent.getDirection());
		m.setEtat(getEtat());
		m.setMarque(getMarq());
		//m.setNomenMat(getTypemateriel());
		m.setNumSerie(getNumSerie());
		m.setPu(getUnitPrice());
		m.setReference(getReference());
		m.setRenseignement(getRenseignement());
		m.setTypematerieladd(this.getTypematerielToAdd());
		m.setNomenMat(this.getTypematerielToAdd().getNomenclaureParent());


		m.setServ(getServiceforMat());
		m.setDirec(agent.getDirection());

		m.setEspeceUnite(getEspeceUnite());
		m.setOrigine(getOrigine());

		m.setRefFacture(getRefFacture());

		m.setDirec(agent.getDirection());

		// m.setCaract(caract);
		// m.setCategorie(categorie);

		//m.setImage((byte[]) RequestFilter.getSession().getAttribute("imageMat"));

		//m.setDocumentPath((String) RequestFilter.getSession().getAttribute("documentpath"));

		// proprietes propre aux materiels nouveaux

		m.setFinancement(getFinancement());

		m.setFournisseur(getFournisseur());

		m.setModAcq(getAcquisition());

		m.setMontant_facture(getMontantFac());

		// m.setCaract(caract);
		// m.setCategorie(categorie);

		// m.setDocumentPath(documentPath);
		m.setValidation(false);
		if(listMaterielForOpEntree == null)
			listMaterielForOpEntree = new ArrayList<Materiel>();

		listMaterielForOpEntree.add(m);
		System.out.println("added to list");
		clearPriseEnCharge();
		this.documentList = initialize();
		this.imageList = initializeImageFile();

		setAllNull();

		return null;
		//PrimeFaces.current().resetInputs("form:panel");
		//listMaterielForOpEntree.add(getMatForEntree());
	}

	

	public void clear() throws IOException {

		this.setAgentDest(null);
		this.setPrix(null);
		this.setNombre(null);
		this.setRenseignement(null);
		this.setCodeArticle(null);


		this.setDetenteur(null);
		this.setMaterielSeclected(null);
		this.setNom(null);
		this.setReferenceAutom(null);
		this.setNumSerie(null);
		this.setNomenclatureAutom(null);
		this.setFinancement(null);
		this.setAcquisition(null);
		this.setUnitPrice(null);
		this.setAnneeAcquisition(null);
		this.setTypematerielToAdd(null);
		this.setNomencl(null);
		this.setMarq(null);
		this.setReference(null);
		this.setNumSerie(null);
		this.setRenseignement(null);
		this.setAutre(null);
		this.setEtat(null);
		this.setOrigine(null);
		this.setEspeceUnite(null);
		this.setMontantFac(null);
		this.setRefFacture(null);
		this.setFournisseur(null);
		
		documentList = initialize();
		imageList = initializeImageFile();
		documentFacList = initializeFacFile();
	}

	public void clearPriseEnCharge() throws IOException {
		this.setDetenteur(null);
		this.setMaterielSeclected(null);
		this.setNom(null);
		this.setReferenceAutom(null);
		this.setNumSerie(null);
		this.setNomenclatureAutom(null);
		this.setFinancement(null);
		this.setAcquisition(null);
		this.setUnitPrice(null);
		this.setAnneeAcquisition(null);
		this.setTypematerielToAdd(null);
		this.setNomencl(null);
		this.setMarq(null);
		this.setReference(null);
		this.setNumSerie(null);
		this.setRenseignement(null);
		this.setAutre(null);
		this.setEtat(null);
		this.setOrigine(null);
		this.setEspeceUnite(null);
		this.setMontantFac(null);
		this.setRefFacture(null);
		this.setFournisseur(null);
		documentList = initialize();
		imageList = initializeImageFile();
	}
	public String addMateriel() throws IOException
	{
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");


		System.out.println("ADD MATERIEL");
		try{
			uploadFilesDocument();
			//saveFacFile();


			//ArrayList<DocumentModel> imagelist = (ArrayList<DocumentModel>) RequestFilter.getSession()
			//		.getAttribute("imageList");
			// agent.setIp()

			ArrayList<DocumentModel> imagelist = imageList;
			MaterielEx m = new MaterielEx();
			//System.out.println("---------------SIZE IMAGE BYTE ARRAY="+imagelist.get(0).getByteArrayImage().length);
			if(imagelist !=null)
			{
				m.setImage(imagelist.get(0).getByteArrayImage());
			}
			else
			{
				m.setImage(null);
			}


			m.setAnneeAcquisition((String) ""+Calendar.getInstance().get(Calendar.YEAR)+"");

			m.setDocumentPath((String) RequestFilter.getSession().getAttribute("documentpath"));
            System.out.println("DOCUMENTPATH========================="+(String) RequestFilter.getSession().getAttribute("documentpath"));
			RequestFilter.getSession().removeAttribute("documentpath");
			m.setAutre(getAutre());
			//m.setBureau(getBureau());
			// m.setDirec(getDirection());
			//m.setDirec(agent.getDirection());
			m.setDirec(agent.getDirection());
			m.setEtat(getEtat());
			m.setMarque(getMarq());
			//m.setNomenMat(getTypemateriel());
			m.setNumSerie(getNumSerie());
			m.setPu(getUnitPrice());
			m.setReference(getReference());
			m.setRenseignement(getRenseignement());
			m.setTypematerieladd(this.getTypematerielToAdd());
			m.setNomenMat(this.getTypematerielToAdd().getNomenclaureParent());
			m.setEspeceUnite(getEspeceUnite());
			m.setOrigine(getOrigine());


			//m.setServ(getServiceforMat());
			//m.setDirec(agent.getDirection());

			// m.setCaract(caract);
			// m.setCategorie(categorie);

			// m.setDocumentPath(documentPath);
			m.setValidation(false);
			if(listMaterielForOpEntree == null)
				listMaterielForOpEntree = new ArrayList<Materiel>();

			listMaterielForOpEntree.add(m);


			// set Operation requete entrer materiel existant
			OpEntree opentree = usermetierimpl.reqEntrerMateriel(listMaterielForOpEntree, agent, getFacturePath(), getRefFacture());
			// set Operation valider automatique car ne necessite pas de validation GAC
			usermetierimpl.entrerMateriel(opentree);

			//miboucle list op entree
			for(Materiel ma : listMaterielForOpEntree)
			{
				if(getDetenteurMatEx() !=null)
				{
					//usermetierimpl.attribuerMaterielEx((MaterielEx) ma,getDetenteurMatEx());
					System.out.println("Begin Attribution");
					OpAttribution oa= usermetierimpl.reqAttribution((MaterielEx) ma, agent, getDetenteurMatEx());
					usermetierimpl.attriuberMateriel(oa);
					System.out.println("End Attribution");
					//m.setDetenteur(getDetenteurMatEx());
				}
			}
			clear();

			//-----------DESTROY ALL SESSION------------------
			RequestFilter.getSession().setAttribute("documentpath",null);
			RequestFilter.getSession().setAttribute("documentList",null);
			RequestFilter.getSession().setAttribute("imageList",null);
			listMaterielForOpEntree = null;
			this.documentList = initialize();
			this.imageList = initializeImageFile();
			this.documentFacList = initializeFacFile();

			setAllNull();

			return SUCCESS;
		}
		catch(JDBCException jdbce){
			jdbce.getSQLException().getNextException().printStackTrace();
			listMaterielForOpEntree = null;
			return ERROR;
		} catch (IOException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error file not found", "Facture's file not found ");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Facture's file not found "));
			listMaterielForOpEntree = null;
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}catch (NullPointerException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error validating materiel", "Error operation");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error operation valeur null"));
			listMaterielForOpEntree = null;
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error validating materiel", "Error operation");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error operation exception :"+e.getMessage()));
			listMaterielForOpEntree = null;
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}
		/*catch(Exception e){
			e.printStackTrace();
			e.getNextException();
			return ERROR;
		}*/
	}



	public void setAllNull(){
		this.typemateriel = null;
		this.anneeAcquisition = null;
		this.autre = null;
		this.bureau = null;
		this.fournisseur =null;
		this.etat =null;
		this.marq =null;
		this.numSerie = null;
		this.unitPrice = null;
		this.reference =null;
		this.renseignement = null;
		this.typematerielToAdd =null;
		this.serviceforMat =null;
		this.especeUnite = null;
		this.origine = null;
		this.refFacture =null;
		this.direction = null;
		this.financement = null;
		this.acquisition = null;
		this.montantFac  = null;

		this.materielSeclected = null;
		this.nomenclatureAutom = null;
		this.marqueAutom = null;
		this.referenceAutom = null;
		this.detenteur = null;

		this.nom = null;

		this.codificationAutom = null;
		this.motifSortie =null;

		this.materiel = null;

	}


	public String addPriseEncharge() {
		try{
			uploadFilesDocument();
			Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
			// agent.setIp()
			ArrayList<DocumentModel> imagelist = this.imageList;

			MaterielNouv m = new MaterielNouv();

			if(imagelist !=null)
			{
				m.setImage(imagelist.get(0).getByteArrayImage());
			}
			else
			{
				m.setImage(null);
			}

			m.setDocumentPath((String) RequestFilter.getSession().getAttribute("documentpath"));
			RequestFilter.getSession().removeAttribute("documentpath");

			m.setAutre(getAutre());

			m.setAnneeAcquisition((String) ""+Calendar.getInstance().get(Calendar.YEAR)+"");

			m.setBureau(getBureau());
			// m.setDirec(getDirection());
			m.setDirec(agent.getDirection());

			m.setEtat(getEtat());



			m.setMarque(getMarq());

			//m.setNomenMat(getTypemateriel());
			m.setTypematerieladd(this.getTypematerielToAdd());
			m.setNomenMat(this.getTypematerielToAdd().getNomenclaureParent());

			m.setEspeceUnite(getEspeceUnite());
			m.setOrigine(getOrigine());


			m.setNumSerie(getNumSerie());

			m.setPu(getUnitPrice());

			m.setReference(getReference());

			m.setRenseignement(getRenseignement());

			m.setServ(getService());

			m.setRefFacture(getRefFacture());

			m.setDirec(agent.getDirection());

			// m.setCaract(caract);
			// m.setCategorie(categorie);

			//m.setImage((byte[]) RequestFilter.getSession().getAttribute("imageMat"));

			//m.setDocumentPath((String) RequestFilter.getSession().getAttribute("documentpath"));

			// proprietes propre aux materiels nouveaux

			m.setFinancement(getFinancement());

			m.setFournisseur(getFournisseur());

			m.setModAcq(getAcquisition());

			m.setMontant_facture(getMontantFac());

			listMaterielForOpEntree.add(m);
			// m.setRefFacture(refFacture);

			// set Operation requete entrer materiel nouveau
			System.out.println("ready to send request");
			for(Materiel a:listMaterielForOpEntree) {
				System.out.println("materiel: "+ a.getNumSerie());
			}

			OpEntree opEntree = usermetierimpl.reqEntrerMateriel(listMaterielForOpEntree, agent, getFacturePath(), getRefFacture());

			listMaterielForOpEntree = null;
			clear();

			setAllNull();

			return SUCCESS;
		}
		catch(Exception e){
			e.printStackTrace();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error validating materiel", e.getMessage());
			//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error operation "));
			listMaterielForOpEntree = null;
			FacesContext.getCurrentInstance().addMessage(null, message);
			return ERROR;
		}

	}


	public String addPriseEnchargeOp(){
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
		OpEntree opEntree = usermetierimpl.reqEntrerMateriel(listMaterielForOpEntree, agent, getFacturePath(), getRefFacture());
		return SUCCESS;
	}

	public String addAttribution() {
		System.out.println("****************************ADD ATTR**ERRORR********************************");
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
		System.out.println("****************************ADD1 ATTR**ERRORR********************************");
		// agent.setIp()

		try {
			// getCurrent Materiel ve?????
			System.out.println("****************************ADD2 ATTR**ERRORR*****NULL*************************** "+
					getMaterielSeclected().getNomenMat().getDesignation());

			OpAttribution opAt = usermetierimpl.reqAttribution(getMaterielSeclected(), agent, getDetenteur());
			System.out.println("****************************ADD3 ATTR**ERRORR********************************");
			clear();
			setAllNull();
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception

			e.printStackTrace();
			System.out.println(e.getMessage());
			return ERROR;
		}

	}
	public String addDetachement1(Agent dettest)
	{
		Agent a = dettest;
		return SUCCESS;
	}
	public String addDetachement() {
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
		// agent.setIp()
		OpDettachement opDet = null;
		System.out.println("****************************ADD DETACHEMENT********************************");
		try {
			// getCurrent Materiel ve?????
			opDet = usermetierimpl.reqDettachement(this.getMaterielSeclected(), agent, getDetenteur(), this.getMotifSortie());
			setAllNull();
			return SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception

			System.out.println(e.getMessage());
			return ERROR;
		}

	}

	public String addDecharge() {
		System.out.println("Decharge begin");
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
		// agent.setIp()
		OpSortie opSort = null;
		try {
			if(getDestinationDirec()==null){
				opSort = usermetierimpl.reqSortirMateriel(this.getMateriel(), this.getMotifSortie(),agent);
			}
			else {
				opSort = usermetierimpl.reqSortirMateriel(this.getMateriel(), this.getMotifSortie(),
						this.getDestinationDirec(), agent);
			}

			setAllNull();

			return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block

			System.out.println(e.getMessage());
			return ERROR;
		}



	}

	public String getDocumentPath() {
		return documentPath;
	}

	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}

	public Service getServiceforMat() {
		return serviceforMat;
	}

	public void setServiceforMat(Service serviceforMat) {
		this.serviceforMat = serviceforMat;
	}




	//------------------------------PRIME FACES---------------------------------------
	//list service
	//list nomenclature
	//list marque
	//list etat



	private Service servicePrim;
	private List<Service> servicesPrim;

	private Nomenclature nomenclaturePrim;
	private  List<Nomenclature> nomenclaturesPrim;

	private Marque marquePrim;
	private List<Marque> marquesPrim;

	private EtatMateriel etatMaterielPrim;
	private List<EtatMateriel> etatMaterielsPrim;



	private TypeObjet typeObjet;
	private CodeArticle codeArticle;

	public List<Article> getListArticle() {
		return listArticle;
	}

	public void setListArticle(List<Article> listArticle) {
		this.listArticle = listArticle;
	}

	public List<ArticleNouv> getListArticleNouv() {
		return listArticleNouv;
	}

	public void setListArticleNouv(List<ArticleNouv> listArticleNouv) {
		this.listArticleNouv = listArticleNouv;
	}

	private List<Article> listArticle;
	private List<ArticleNouv> listArticleNouv;




	public List<Service> getServicesPrim() {
		return servicesPrim;
	}

	public void setServicesPrim(List<Service> servicesPrim) {
		this.servicesPrim = servicesPrim;
	}
	public EtatMateriel getEtatMaterielPrim() {
		return etatMaterielPrim;
	}

	public void setEtatMaterielPrim(EtatMateriel etatMaterielPrim) {
		this.etatMaterielPrim = etatMaterielPrim;
	}



	public Marque getMarquePrim() {
		return marquePrim;
	}

	public void setMarquePrim(Marque marquePrim) {
		this.marquePrim = marquePrim;
	}



	public Service getServicePrim() {
		return servicePrim;
	}

	public void setServicePrim(Service servicePrim) {
		this.servicePrim = servicePrim;
	}

	public Nomenclature getNomenclaturePrim() {
		return nomenclaturePrim;
	}

	public void setNomenclaturePrim(Nomenclature nomenclaturePrim) {
		this.nomenclaturePrim = nomenclaturePrim;
	}

	public List<Nomenclature> getNomenclaturesPrim() {
		return nomenclaturesPrim;
	}

	public void setNomenclaturesPrim(List<Nomenclature> nomenclaturesPrim) {
		this.nomenclaturesPrim = nomenclaturesPrim;
	}

	public List<Marque> getMarquesPrim() {
		return marquesPrim;
	}

	public void setMarquesPrim(List<Marque> marquesPrim) {
		this.marquesPrim = marquesPrim;
	}

	public List<EtatMateriel> getEtatMaterielsPrim() {
		return etatMaterielsPrim;
	}

	public void setEtatMaterielsPrim(List<EtatMateriel> etatMaterielsPrim) {
		this.etatMaterielsPrim = etatMaterielsPrim;
	}




	//-------------NEW  EDIT------------------
	//Affichage automatique de la marque, de la référence et du numéro de série
	private Materiel materielSeclected;
	private Marque marqueAutom;
	private String referenceAutom;
	private String codificationAutom;
	private String origine;
	private String especeUnite;
	private String numSerieAutom;
	private String nomenclatureAutom;
	private List<Referentiel> listDestinaiton;


	public void onChangeMateriel()
	{
		marqueAutom=getMaterielSeclected().getMarque();
		setReferenceAutom(getMaterielSeclected().getReference());
		setNumSerie(getMaterielSeclected().getNumSerie());
		setNomenclatureAutom(getMaterielSeclected().getNomenMat().getDesignation());
		setCodificationAutom(getMaterielSeclected().getCode());
	}
	public List<Referentiel> getListDestinaiton() {
		this.listDestinaiton.add(new Bureau());
		this.listDestinaiton.add(new Direction());
		this.listDestinaiton.add(new Service());
		return listDestinaiton;
	}

	public void setListDestinaiton(List<Referentiel> listDestinaiton) {
		this.listDestinaiton = listDestinaiton;
	}

	public String getReferenceAutom() {
		return this.referenceAutom;
	}

	public void setReferenceAutom(String referenceAutom) {
		this.referenceAutom = referenceAutom;
	}


	public String getOrigine() {
		return this.origine;
	}

	public void setOrigine(String o) {
		this.origine = o;
	}


	public String getEspeceUnite() {
		return this.especeUnite;
	}

	public void setEspeceUnite(String e) {
		this.especeUnite = e;
	}



	public String getNumSerieAutom() {
		return this.numSerieAutom;
	}

	public void setNumSerieAutom(String numSerieAutom) {
		this.numSerieAutom = numSerieAutom;
	}

	public Marque getMarqueAutom() {
		return this.marqueAutom;
	}

	public void setMarqueAutom(Marque marqueAutom) {
		this.marqueAutom = marqueAutom;
	}







	public Materiel getMaterielSeclected() {
		return materielSeclected;
	}

	public void setMaterielSeclected(Materiel materielSeclected) {
		this.materielSeclected = materielSeclected;
	}

	public MotifSortie getMotifSortie() {
		return motifSortie;
	}

	public void setMotifSortie(MotifSortie motifSortie) {
		this.motifSortie = motifSortie;
	}

	public List<Materiel> getListAllMaterilValide()
	{
		//System.out.println("----size------"+usermetierimpl.getMatByValidation(true).size());
		List<Materiel> listM = usermetierimpl.getListMat();
		System.out.println("----size listal------"+listM.size());
		List<Materiel> l =  new ArrayList<Materiel>();
		for(Materiel m : listM)
		{
			System.out.println("----validation each list------"+m.isValidation());
			if(m.isValidation())
			{
				l.add(m);
			}
		}
		System.out.println("----size l------"+l.size());
		return l;
		//return usermetierimpl.getMatByValidation(true);
	}

	public void setListAllMaterilValide(List<Materiel> listAllMaterilValide) {
		this.listAllMaterilValide = listAllMaterilValide;
	}

	public ArrayList<DocumentModel> getImageList() {
		return imageList;
	}

	public void setImageList(ArrayList<DocumentModel> imageList) {
		this.imageList = imageList;
	}

	public List<Materiel> getListAllMaterielValideSansDetenteur() {
		return usermetierimpl.getMatByDetenteurAndValidation(null, true);
	}

	public void setListAllMaterielValideSansDetenteur(List<Materiel> listAllMaterielValideSansDetenteur) {
		this.listAllMaterielValideSansDetenteur = listAllMaterielValideSansDetenteur;
	}
	public List<Materiel> getCurrentListMateriel() {

		//System.out.println("****************************SET LIST ******************************** " +this.getClass().getName());
		Long idAg = Long.valueOf(1);
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		System.out.println(FacesContext.getCurrentInstance().toString());
		String userId = ec.getRequestParameterMap().get(1);
		Map<String, String> map = ec.getRequestParameterMap();
		String input = ":det_input";
		for (Map.Entry<String, String> entry : map.entrySet())
		{
			if(entry.getKey().toLowerCase().contains(input.toLowerCase()))
				idAg = Long.parseLong(entry.getValue());
			//System.out.println(entry.getKey() + "/" + entry.getValue());
		}

		if(!idAg.equals(Long.valueOf(1)))
			return (List<Materiel>)usermetierimpl.getMatByDetenteurAndValidation(usermetierimpl.findAgentByIm(idAg), true);
		System.out.println("****************************SET LIST ******************************** " +this.getClass().getName());

		return currentListMateriel;


	}

	public void setCurrentListMateriel(List<Materiel> currentListMateriel) {
		this.currentListMateriel = currentListMateriel;
	}
	public void mySetCurrentListMateriel1(ValueChangeEvent evt)
	{
		System.out.println("****************************SET LIST ******************************** " +this.getClass().getName());
		ValueChangeEvent e = evt;
		this.setDetenteur((Agent) evt.getNewValue());
		this.setCurrentListMateriel((List<Materiel>)usermetierimpl.getMatByDetenteurAndValidation(this.getDetenteur(), true));
	}
	//ActionEvent actionEvent
	public void mySetCurrentListMateriel(){
		this.detenteur = this.getDetenteur();
		this.setNom(detenteur.getNomAgent());
		this.setPrenom(detenteur.getPrenomAgent());
		System.out.println("****************************SET LIST ******************************** " +this.getDetenteur().getNomAgent());
		this.setCurrentListMateriel((List<Materiel>)usermetierimpl.getMatByDetenteurAndValidation(this.getDetenteur(), true));
		System.out.println("****************************SET LIST ******************************** " +this.getDetenteur().getIm());
	}

	public void setListLocalite(List<Localite> listLocalite) {
		this.listLocalite = listLocalite;
	}
	public List<MotifSortie> getListMotifSortie() {
		ArrayList<Referentiel> r = (ArrayList<Referentiel>)refmetierimpl.listRef(new MotifSortie());
		List<MotifSortie> ds = new ArrayList<MotifSortie>();
		for (Object d :  r)
		{
			if(d instanceof MotifSortie) {
				ds.add((MotifSortie)d);
			}
		}
		return ds;
	}
	public void test()
	{
		System.out.println("***************************SET LIST ******************************** " +this.getClass().getName());
	}
	private List<MaterielNouv> listMaterielNouveauValide;
	public List<MaterielNouv> getListMaterielNouveauValide(){
		return usermetierimpl.getListMaterielNouvValide();
	}

	public void setListMaterielNouveauValide(List<MaterielNouv> listMaterielNouveauValide) {
		this.listMaterielNouveauValide = listMaterielNouveauValide;
	}

	public String getNomenclatureAutom() {
		return nomenclatureAutom;
	}

	private int fileZipSize;

	public int getFileZipSize() throws IOException {
		if(RequestFilter.getSession().getAttribute("fileZipPath") == null)
			return 0;
		InputStream stream = new FileInputStream((String )RequestFilter.getSession().getAttribute("fileZipPath"));
		if(stream == null)
			return 0;
		fileZipSize = stream.available();
		return fileZipSize;
	}

	public void setFileZipSize(int fileZipSize) {
		this.fileZipSize = fileZipSize;
	}


	private InputStream fileDownloadStream;

	public InputStream getFileDownloadStream() throws IOException
	{
		if(RequestFilter.getSession().getAttribute("fileZipPath") == null || RequestFilter.getSession().getAttribute("fileZipPath") == "")
			return null;
		FileInputStream stream = new FileInputStream((String )RequestFilter.getSession().getAttribute("fileZipPath"));
		if(stream == null)
			return null;
		if(stream.getChannel().size() == 0)
			return null;
		return stream;
	}

	public void setFileDownloadStream(InputStream fileZipSize) {
		this.fileDownloadStream = fileZipSize;
	}
	public void setNomenclatureAutom(String nomenclatureAutom) {
		this.nomenclatureAutom = nomenclatureAutom;
	}

	//-------DOWNLOAD FILE ZIP-----------
	private StreamedContent filedownload;


	public void beforeDown()
	{
		InputStream stream = this.getClass().
				getResourceAsStream("/chapter7/PFSamplePDF.pdf");
		filedownload = new DefaultStreamedContent(stream,
				"application/pdf", "PFSample.pdf");
	}

	public StreamedContent getFiledownload() throws IOException {
		if(getFileZipPath() == null || getFileZipPath() == "")
			return null;
		try
		{
			FileInputStream fstream = new FileInputStream(getFileZipPath());
			if (fstream == null)
				return null;
			if (fstream.getChannel().size() == 0)
				return null;

			InputStream stream = new FileInputStream(getFileZipPath());
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

	public String getFilePathsecond() {
		return filePathsecond;
	}

	public void setFilePathsecond(String filePathsecond) {
		this.filePathsecond = filePathsecond;
	}

	private String filePathsecond;

	/*public ArticleNouv addArticleNouv(CodeArticle cde, Agent ben, Agent depo, Fournisseur fourn, Float prix, Long nombre)
	{
		ArticleNouv an = usermetierimpl.addArticleNouv( cde,  ben,  depo,  fourn,  prix,  nombre);
		return an;
	}

	public ArticleEx ticleEx(CodeArticle cde, Agent ben, Agent depo, Float prix, Long nombre)
	{
		ArticleEx ae = usermetierimpl.addArticleEx( cde,  ben,  depo,  prix,  nombre);
		return ae;
	}*/


	public TypeObjet getTypeObjet() {
		return typeObjet;
	}

	public void setTypeObjet(TypeObjet typeObjet) {
		this.typeObjet = typeObjet;
	}

	public CodeArticle getCodeArticle() {
		return codeArticle;
	}

	public void setCodeArticle(CodeArticle codeArticle) {
		//test
		this.codeArticle = codeArticle;
	}
	TypeMateriel typematerielToAdd;


	public TypeMateriel getTypematerielToAdd() {
		return typematerielToAdd;
	}

	public void setTypematerielToAdd(TypeMateriel typematerielToAdd) {
		this.typematerielToAdd = typematerielToAdd;
	}




	//-----------------GRAND II-----------------------------
	Float prix;

	public Float getPrix() {
		return prix;
	}

	public void setPrix(Float prix) {
		this.prix = prix;
	}




	Long nombre;

	public Long getNombre() {
		return nombre;
	}

	public void setNombre(Long n) {
		this.nombre = n;
	}

	public String addArticleEx()
	{
		try {
			clear();
			ArticleEx a = new ArticleEx();

			Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
			a.setCodeArticle(getCodeArticle());
			a.setMarqueArticle(getMarq());
			a.setCaracteristiqueArticle(getRenseignement());

			a.setDirecArt(agent.getDirection());
			a.setValidation(true);

			a.setNombre(getNombre());

			//a.setTypeObjet(getTypeObjet());
			OpEntreeArticle oeart=usermetierimpl.reqEntrerArticle(a,agent);
			usermetierimpl.entrerArticle(oeart);

		}
			catch (IOException e) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error file not found", "Facture's file not found ");
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Facture's file not found "));
				listMaterielForOpEntree = null;
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			}

		return SUCCESS;
	}


	public String addArticleNouv()
	{
		try
		{
		ArticleNouv a = new ArticleNouv();

		Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
			a.setCodeArticle(getCodeArticle());
			a.setFournisseur(getFournisseur());
			a.setPrix(getPrix());
			a.setFinancementArt(getFinancement());

			a.setDirecArt(agent.getDirection());

			a.setMarqueArticle(getMarq());
			a.setCaracteristiqueArticle(getRenseignement());
			//a.setModAcq(getAcquisition());

			a.setNombre(getNombre());

			usermetierimpl.reqEntrerArticle(a,agent);
			clear();
		}
			catch (IOException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error file not found", "Facture's file not found ");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Facture's file not found "));
			listMaterielForOpEntree = null;
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}
		return SUCCESS;
	}


	//sortie
	private Agent agentDest;

	public Agent getAgentDest() {
		return agentDest;
	}

	public void setAgentDest(Agent agentDest) {
		this.agentDest = agentDest;
	}


	private ArticleNouv articleNouv;

	public void setArticleNouv(ArticleNouv a){
		this.articleNouv = a;
	}

	public ArticleNouv getArticleNouv(){
		return articleNouv;
	}

	public String addRequeteSortieNouv() throws Exception {
		//ArticleNouv a = new ArticleNouv();

		Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
		//a.setFournisseur(getFournisseur());
		//a.setPrix(getPrix());



		usermetierimpl.reqSortirArticle(this.getArticle(),agent,getAgentDest());
		return SUCCESS;
	}



	public List<Materiel> getListAllMaterielValideSansDetenteurByDirection() {
		Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
		//return usermetierimpl.getMatByDetenteurAndDirection(null, agent.getDirection());
		return usermetierimpl.getMatByValidationAndDetenteurAndDirection(true,null, agent.getDirection());
	}

	public void setListAllMaterielValideSansDetenteurByDirection(List<Materiel> listAllMaterielValideSansDetenteurByDirection) {
		this.listAllMaterielValideSansDetenteurByDirection = listAllMaterielValideSansDetenteurByDirection;
	}

	private List<Materiel> listAllMaterielValideSansDetenteurByDirection;


	//------TODO BY PRIORITE---------------
	private List<Agent> listDetenteurMatEx;

	private Agent detenteurMatEx;


	private ArrayList<Materiel> listMaterielForOpEntree;

	@PostConstruct
	public void init(){
		listMaterielForOpEntree = new ArrayList<Materiel>();
	}


	private String facturePath;

	private UploadedFile docFacture;

	private Materiel matForEntree;

	public List<Agent> getListDetenteurMatEx() {
		return detenteurmetierimpl.findAllDetenteur();
	}

	public void setListDetenteurMatEx(List<Agent> listDetenteurMatEx) {
		this.listDetenteurMatEx = listDetenteurMatEx;

	}

	public Agent getDetenteurMatEx() {
		return detenteurMatEx;
	}

	public void setDetenteurMatEx(Agent detenteurMatEx) {
		this.detenteurMatEx = detenteurMatEx;
	}

	public IDetenteurMetier getDetenteurmetierimpl() {
		return detenteurmetierimpl;
	}

	public void setDetenteurmetierimpl(IDetenteurMetier detenteurmetierimpl) {
		this.detenteurmetierimpl = detenteurmetierimpl;
	}


	public List<Materiel> getListMaterielForOpEntree() {
		return listMaterielForOpEntree;
	}

	public void setListMaterielForOpEntree(ArrayList<Materiel> listMaterielForOpEntree) {
		this.listMaterielForOpEntree = listMaterielForOpEntree;
	}

	public String getFacturePath() {
		return facturePath;
	}

	public void setFacturePath(String facturePath) {
		this.facturePath = facturePath;
	}

	public UploadedFile getDocFacture() {
		return docFacture;
	}

	public void setDocFacture(UploadedFile docFacture) {
		this.docFacture = docFacture;
	}


	public ArrayList<DocumentModel> getDocumentFacList() {
		return documentFacList;
	}

	public void setDocumentFacList(ArrayList<DocumentModel> documentFacList) {
		this.documentFacList = documentFacList;
	}


	public Materiel getMatForEntree() {
		return matForEntree;
	}

	public void setMatForEntree(Materiel matForEntree) {
		this.matForEntree = matForEntree;
	}

	private List<Article> listArticleValide;

	private List<Article> listArticleValideByDirecNondetenu;

	private List<Article> listArticleValideByDirec;

	public List<Article> getListArticleValide() {
		return usermetierimpl.getListArticleValideByDirection(getCurrentAgent().getDirection());
	}

	public void setListArticleValide(List<Article> listArticleValide) {
		this.listArticleValide = listArticleValide;
	}


	public List<Article> getListArticleValideByDirecNondetenu() {
		return usermetierimpl.getListArticleNonDetenuValideByDirection(getCurrentAgent().getDirection());
	}

	public void setListArticleValideByDirecNondetenu(List<Article> listArticleValide) {
		this.listArticleValideByDirecNondetenu = listArticleValide;
	}

	public List<Article> getListArticleValideByDirec() {
		return usermetierimpl.getListArticleByValidationByDirection(true, getCurrentAgent().getDirection());
	}

	public void setListArticleValideByDirec(List<Article> listArticleValideByDirec) {
		this.listArticleValideByDirec = listArticleValideByDirec;
	}


	//DEBUG ERREUR CURRENT MATERIEL
	private MaterielEx curentMaterielEx;
	public Materiel getCurentMaterielEx()
	{
		return curentMaterielEx;
	}

	public void setCurentMaterielEx(MaterielEx curentMaterielEx) {
		RequestFilter.getSession().setAttribute("fileZipPath",curentMaterielEx.getDocumentPath());
		this.curentMaterielEx = curentMaterielEx;
		this.setIdMat(curentMaterielEx.getIdMateriel());
	}


	private MaterielNouv curentMaterielNouv;
	public Materiel getCurentMaterielNouv()
	{
		return curentMaterielNouv;
	}

	public void setCurentMaterielNouv(MaterielNouv curentMaterielNouv) {
		RequestFilter.getSession().setAttribute("fileZipPath",curentMaterielNouv.getDocumentPath());
		this.curentMaterielNouv = curentMaterielNouv;
		this.setIdMat(curentMaterielNouv.getIdMateriel());
	}

	public String getCodificationAutom() {
		return codificationAutom;
	}

	public void setCodificationAutom(String codificationAutom) {
		this.codificationAutom = codificationAutom;
	}


	public void setCurentNull(){
		this.curentMaterielNouv = null;

		this.curentMaterielEx = null;

		this.idMat = null;

		




	}

}
