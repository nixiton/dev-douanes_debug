package com.douane.managed.bean;

import com.douane.entite.*;
import com.douane.exception.GetFieldName;
import com.douane.exception.NullPointerAttributeException;
import com.douane.metier.fournisseur.IFournisseurMetier;
import com.douane.metier.marque.IMarqueMetier;
import com.douane.metier.nomenclature.INomenclatureMetier;
import com.douane.metier.referentiel.IRefMetier;
import com.douane.metier.typeMateriel.ITypeMaterielMetier;
import com.douane.metier.user.IUserMetier;
import com.douane.model.DocumentModel;
import com.douane.model.DocumentModelSimple;
//import com.douane.model.UploadedFileByte;
import com.douane.requesthttp.RequestFilter;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.JDBCException;
import org.primefaces.context.ApplicationContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.jws.WebParam;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


import java.util.Calendar;

/**
 * Created by hasina on 10/29/17.
 */
@ManagedBean(name = "depositaireBean")
@ViewScoped
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




	/* attribute for file upload */
	private static final long serialVersionUID = 1L;

	private String name;
	private UploadedFile document;
	ArrayList<DocumentModel> documentList = new ArrayList<DocumentModel>();
	ArrayList<DocumentModel> imageList = new ArrayList<DocumentModel>();

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
		ArrayList<DocumentModel> documentlist = (ArrayList<DocumentModel>) RequestFilter.getSession()
				.getAttribute("documentList");
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

		ArrayList<DocumentModel> imagelist = (ArrayList<DocumentModel>) RequestFilter.getSession()
				.getAttribute("imageList");
		if (imagelist != null)
		{
			this.setImageList(imageList);
		}
		else
		{
			imagelist = this.imageList;
		}

		imagelist.set(imageList.indexOf(docObj), docObj);
		RequestFilter.getSession().setAttribute("imageList", imagelist);
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

		ArrayList<DocumentModel> documentlist = (ArrayList<DocumentModel>) RequestFilter.getSession()
				.getAttribute("documentList");
		if (documentlist != null)
			this.setDocumentList(documentlist);

		documentList.set(documentList.indexOf(docObj), docObj);
		RequestFilter.getSession().setAttribute("documentList", documentList);
		System.out.println("File Uploaded");

		return null;
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
		ArrayList<DocumentModel> documentlist = (ArrayList<DocumentModel>) RequestFilter.getSession()
				.getAttribute("documentList");
		for (DocumentModel d : documentlist) {
			File file = new File("resources_13_11");
			String absolutePath = file.getAbsolutePath();
			String filePath = absolutePath;
			String filePath2 = new File("test").getAbsolutePath();
			byte[] bytes = null;

			if (null != d) {
				bytes = d.getByteArrayImage();
				fileName = FilenameUtils.getName(d.getDocumentUploadedPath());
				System.out.println("file name" + fileName);
				BufferedOutputStream stream;
				stream = new BufferedOutputStream(new FileOutputStream(new File(filePath + fileName)));
				filesTozip.add(filePath + fileName);
				stream.write(bytes);
				stream.close();
			}
		}
		zipFiles(filesTozip);
		return SUCCESS;
	}

	public void zipFiles(List<String> files) {

		FileOutputStream fos = null;
		ZipOutputStream zipOut = null;
		FileInputStream fis = null;
		try {
			Date dNow = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
			String datetime = ft.format(dNow);

			File file = new File(datetime);
			String absolutePath = file.getAbsolutePath();

			RequestFilter.getSession().setAttribute("documentpath",   datetime+".zip");
			// eto miset an le documentpath
			String a = (String) RequestFilter.getSession().getAttribute("documentpath");

			fos = new FileOutputStream(  datetime+".zip");
			setDocumentPath(  datetime+".zip");

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

		this.setNomencl(getTypemateriel().getNomenclature());
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

	public void onMotifSortieChange() {
		
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
	public String addMateriel() {
		System.out.println("ADD MATERIEL");
		try{
			uploadFilesDocument();
			Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
			ArrayList<DocumentModel> imagelist = (ArrayList<DocumentModel>) RequestFilter.getSession()
					.getAttribute("imageList");
			// agent.setIp()
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

			m.setAnneeAcquisition(this.anneeAcquisition);

			m.setDocumentPath((String) RequestFilter.getSession().getAttribute("documentpath"));
			RequestFilter.getSession().removeAttribute("documentpath");
			m.setAutre(getAutre());
			m.setBureau(getBureau());
			// m.setDirec(getDirection());
			//m.setDirec(agent.getDirection());
			m.setEtat(getEtat());
			m.setMarque(getMarq());
			m.setNomenMat(getTypemateriel());
			m.setNumSerie(getNumSerie());
			m.setPu(getUnitPrice());
			m.setReference(getReference());
			m.setRenseignement(getRenseignement());

			m.setServ(getServiceforMat());
			m.setDirec(agent.getDirection());

			// m.setCaract(caract);
			// m.setCategorie(categorie);

			// m.setDocumentPath(documentPath);
			m.setValidation(false);
			// set Operation requete entrer materiel existant
			OpEntree opentree = usermetierimpl.reqEntrerMateriel(m, agent);
			// set Operation valider automatique car ne necessite pas de validation GAC
			usermetierimpl.entrerMateriel(opentree);

			//-----------DESTROY ALL SESSION------------------
			RequestFilter.getSession().setAttribute("documentpath",null);
			RequestFilter.getSession().setAttribute("documentList",null);
			RequestFilter.getSession().setAttribute("imageList",null);
			return SUCCESS;
		}
		catch(JDBCException jdbce){
			jdbce.getSQLException().getNextException().printStackTrace();
			return ERROR;
		} catch (IOException e) {
			e.printStackTrace();
			return ERROR;
		}
		/*catch(Exception e){
			e.printStackTrace();
			e.getNextException();
			return ERROR;
		}*/
	}
	public String addPriseEncharge() {
		try{
		Agent agent = (Agent) RequestFilter.getSession().getAttribute("agent");
		// agent.setIp()

		MaterielNouv m = new MaterielNouv();

		m.setAutre(getAutre());

		m.setAnneeAcquisition((String) ""+Calendar.getInstance().get(Calendar.YEAR)+"");

		m.setBureau(getBureau());
		// m.setDirec(getDirection());
		m.setDirec(agent.getDirection());

		m.setEtat(getEtat());

		m.setMarque(getMarq());

		m.setNomenMat(getTypemateriel());

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

		// m.setRefFacture(refFacture);

		// set Operation requete entrer materiel nouveau
		OpEntree opEntree = usermetierimpl.reqEntrerMateriel(m, agent);

		return SUCCESS;
		}
		catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}

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
			opDet = usermetierimpl.reqDettachement(this.getMaterielSeclected(), agent, getDetenteur());
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
		System.out.println("Operation Decharge "+getDestination().getDesignation()+" "
				+getDestinationDirec().getDesignation()+" "
				+getDestinationService().getDesignation()+" "
				+getMotifSortie().getDesignation());
		try {
			opSort = usermetierimpl.reqSortirMateriel(this.getMateriel(), this.getMotifSortie(),
					this.getDestinationDirec(), this.getDestinationService(), this.getDestination(), agent);
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
	private String numSerieAutom;
	private String nomenclatureAutom;
	private List<Referentiel> listDestinaiton;


	public void onChangeMateriel()
	{
		marqueAutom=getMaterielSeclected().getMarque();
		setReferenceAutom(getMaterielSeclected().getReference());
		setNumSerie(getMaterielSeclected().getNumSerie());
		setNomenclatureAutom(getMaterielSeclected().getNomenMat().getDesignation());
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
		System.out.println("****************************SET LIST ******************************** " +this.getClass().getName());
	}
	private List<MaterielNouv> listMaterielNouveauValide;
	public List<MaterielNouv> getListMaterielNouveauValide() {
		return usermetierimpl.getListMaterielNouvValide();
	}

	public void setListMaterielNouveauValide(List<MaterielNouv> listMaterielNouveauValide) {
		this.listMaterielNouveauValide = listMaterielNouveauValide;
	}

	public String getNomenclatureAutom() {
		return nomenclatureAutom;
	}

	public void setNomenclatureAutom(String nomenclatureAutom) {
		this.nomenclatureAutom = nomenclatureAutom;
	}
}
