package com.douane.managed.bean;

import com.douane.entite.*;
import com.douane.exception.GetFieldName;
import com.douane.exception.NullPointerAttributeException;
import com.douane.metier.bureau.IBureauMetier;
import com.douane.metier.referentiel.IRefMetier;
import com.douane.metier.user.IUserMetier;
import com.douane.metier.utilisateur.IUtilisateurMetier;
import com.douane.requesthttp.RequestFilter;
import org.aspectj.apache.bcel.classfile.Code;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import java.sql.SQLException;

/**
 * Created by hasina on 10/25/17.
 */
@ManagedBean(name="siseBean")
@RequestScoped
public class SISEformBean {
    private static final String SUCCESS = "success";
    private static final String ERROR   = "error";



    @ManagedProperty(value="#{usermetier}")
    IUserMetier usermetierimpl;
    
    @ManagedProperty(value="#{bureaumetier}")
    IBureauMetier bureaumetierimpl;
    
    @ManagedProperty(value="#{refmetier}")
    IRefMetier refmetierimpl;
    
    @ManagedProperty(value="#{utilisateurmetier}")
    IUtilisateurMetier utilisateurmetierimpl;

    private String designation = null;
    private String nomenclature = null;
    private Nomenclature nomen;
    private TypeMateriel tymat;
    private EtatMateriel etmat;
    private Marque marque;



    private String name;
    private String username;
    private String password;
    private Long im;
    private String role;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private ModeAcquisition modeAcquisition;
    private MotifDecharge motifDecharge;
    private Financement financement;



    private Fournisseur fournisseur;
    
    private String codeBureau;
    private String codeService;
    private String codeDirection;


    
    /*private String adresse;
    private String localite;
    
    private String motifSortie;
    private String poste;
    private String service;
                    direction;
                    bureau;
    */
    
    
    //List des objets
    private List<Bureau> listBureau;
    private List<Nomenclature> listNomenclature;
    private List<Direction> listDirection;
    private List<EtatMateriel> listEtatMateriel;
    private List<Financement> listFinancement;
    private List<Fournisseur> listFournisseur;
    private List<Marque> listMarque;
    private List<ModeAcquisition> listModeAcquisition;
    private List<MotifDecharge> listMotifDecharge;
    private List<TypeMateriel> listTypeMateriel;
    
    
    private List<Adresse> listAdresse;
    private List<Localite> listLocalite;
    private List<MotifSortie> listMotifSortie;
    private List<Poste> listPoste;
    private List<Service> listService;
    private List<Useri> listUseri;
    private List<TypeObjet> listTypeObjet;
    
    public List<Direction> getListDirection() {
        ArrayList<Referentiel> r = (ArrayList<Referentiel>)refmetierimpl.listRef(new Direction());
        List<Direction> ds = new ArrayList<Direction>();
        for (Object d :  r)
        {
            if(d instanceof Direction) {
                ds.add((Direction)d);
            }
        }
        return ds;
    }
    public void setListDirection(List<Direction> listDirection) {
        this.listDirection = listDirection;
    }
    public List<EtatMateriel> getListEtatMateriel() {
        ArrayList<Referentiel> r = (ArrayList<Referentiel>)refmetierimpl.listRef(new EtatMateriel());
        List<EtatMateriel> ds = new ArrayList<EtatMateriel>();
        for (Object d :  r)
        {
            if(d instanceof EtatMateriel) {
                ds.add((EtatMateriel)d);
            }
        }
        return ds;
    }
    
    public void setListEtatMateriel(List<EtatMateriel> listEtatMateriel) {
        this.listEtatMateriel = listEtatMateriel;
    }
    public List<Financement> getListFinancement() {
        ArrayList<Referentiel> r = (ArrayList<Referentiel>)refmetierimpl.listRef(new Financement());
        List<Financement> ds = new ArrayList<Financement>();
        for (Object d :  r)
        {
            if(d instanceof Financement) {
                ds.add((Financement)d);
            }
        }
        return ds;
    }
    public void setListFinancement(List<Financement> listFinancement) {
        this.listFinancement = listFinancement;
    }
    public List<Fournisseur> getListFournisseur() {
        ArrayList<Referentiel> r = (ArrayList<Referentiel>)refmetierimpl.listRef(new Fournisseur());
        List<Fournisseur> ds = new ArrayList<Fournisseur>();
        for (Object d :  r)
        {
            if(d instanceof Fournisseur) {
                ds.add((Fournisseur)d);
            }
        }
        return ds;
    }
    public void setListFournisseur(List<Fournisseur> listFournisseur) {
        this.listFournisseur = listFournisseur;
    }
    public List<Marque> getListMarque() {
        ArrayList<Referentiel> r = (ArrayList<Referentiel>)refmetierimpl.listRef(new Marque());
        List<Marque> ds = new ArrayList<Marque>();
        for (Object d :  r)
        {
            if(d instanceof Marque) {
                ds.add((Marque)d);
            }
        }
        return ds;
    }
    public void setListMarque(List<Marque> listMarque) {
        this.listMarque = listMarque;
    }
    public List<ModeAcquisition> getListModeAcquisition() {
        ArrayList<Referentiel> r = (ArrayList<Referentiel>)refmetierimpl.listRef(new ModeAcquisition());
        List<ModeAcquisition> ds = new ArrayList<ModeAcquisition>();
        for (Object d :  r)
        {
            if(d instanceof ModeAcquisition) {
                ds.add((ModeAcquisition)d);
            }
        }
        return ds;
    }
    public void setListModeAcquisition(List<ModeAcquisition> listModeAcquisition) {
        this.listModeAcquisition = listModeAcquisition;
    }
    public List<MotifDecharge> getListMotifDecharge() {
        ArrayList<Referentiel> r = (ArrayList<Referentiel>)refmetierimpl.listRef(new MotifDecharge());
        List<MotifDecharge> ds = new ArrayList<MotifDecharge>();
        for (Object d :  r)
        {
            if(d instanceof MotifDecharge) {
                ds.add((MotifDecharge)d);
            }
        }
        return ds;
    }
    public void setListMotifDecharge(List<MotifDecharge> listMotifDecharge) {
        this.listMotifDecharge = listMotifDecharge;
    }
    public List<TypeMateriel> getListTypeMateriel() {
        ArrayList<Referentiel> r = (ArrayList<Referentiel>)refmetierimpl.listRef(new TypeMateriel());
        List<TypeMateriel> ds = new ArrayList<TypeMateriel>();
        for (Object d :  r)
        {
            if(d instanceof TypeMateriel) {
                ds.add((TypeMateriel)d);
            }
        }
        return ds;
    }
    public void setListTypeMateriel(List<TypeMateriel> listTypeMateriel) {
        this.listTypeMateriel = listTypeMateriel;
    }
    public List<Adresse> getListAdresse() {
        ArrayList<Referentiel> r = (ArrayList<Referentiel>)refmetierimpl.listRef(new Adresse());
        List<Adresse> ds = new ArrayList<Adresse>();
        for (Object d :  r)
        {
            if(d instanceof Adresse) {
                ds.add((Adresse)d);
            }
        }
        return ds;
    }
    public void setListAdresse(List<Adresse> listAdresse) {
        this.listAdresse = listAdresse;
    }
    public List<Localite> getListLocalite() {
        ArrayList<Referentiel> r = (ArrayList<Referentiel>)refmetierimpl.listRef(new Localite());
        List<Localite> ds = new ArrayList<Localite>();
        for (Object d :  r)
        {
            if(d instanceof Localite) {
                ds.add((Localite)d);
            }
        }
        return ds;
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
    public void setListMotifSortie(List<MotifSortie> listMotifSortie) {
        this.listMotifSortie = listMotifSortie;
    }
    public List<Poste> getListPoste() {
        ArrayList<Referentiel> r = (ArrayList<Referentiel>)refmetierimpl.listRef(new Poste());
        List<Poste> ds = new ArrayList<Poste>();
        for (Object d :  r)
        {
            if(d instanceof Poste) {
                ds.add((Poste)d);
            }
        }
        return ds;
    }
    public void setListPoste(List<Poste> listPoste) {
        this.listPoste = listPoste;
    }
    public List<Service> getListService() {
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
    public void setListService(List<Service> listService) {
        this.listService = listService;
    }
    public List<Useri> getListUseri() {
        
        return utilisateurmetierimpl.findAllUtilisateur() ;
    }
    public void setListUseri(List<Useri> listUseri) {
        this.listUseri = listUseri;
    }
    

    public String addNomenclature()
    {
        nomen = new Nomenclature(getDesignation() , getNomenclature());
        Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
        //check if there is nomenclature duplicate
        System.out.println("NOMENCLATURE ADD"+ nomen.getDesignation());
        System.out.println("AGENT ADD"+ agent.getNomAgent());

        refmetierimpl.addRef(nomen,agent);
        return SUCCESS;
    }
    public String addTypeMateriel()
    {
        tymat = new TypeMateriel(getDesignation());
        Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
        //check if there is nomenclature duplicate

        refmetierimpl.addRef(tymat,agent);
        return SUCCESS;
    }
    public String addEtatMateriel() throws NullPointerAttributeException, NoSuchMethodException {
        if(getDesignation()==null || getDesignation().equals(""))
        {

                throw new NullPointerAttributeException("", new GetFieldName(), this.getClass().getMethod("getDesignation"));

        }
        etmat = new EtatMateriel(getDesignation());
        Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
        //check if there is nomenclature duplicate

        refmetierimpl.addRef(etmat,agent);
        return SUCCESS;
    }
    public String addMarque()
    {
        marque = new Marque(getDesignation());
        Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
        refmetierimpl.addRef(marque,agent);
        return SUCCESS;
    }

    public String addMarqueCA()
    {
        marque = new Marque(getDesignation());
        Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
        refmetierimpl.addRef(marque,agent);
        return SUCCESS;
    }


    public String addUser()
    {
        try
        {
            Agent user = new Agent();
            Useri useri = new Useri();
            //user.setName(getName());

            user.setNomAgent(getName());

            //user.setUsername(getUsername());

            user.setPrenomAgent(getName());
            user.setIm(getIm());

            String hashedPassword = passwordEncoder.encode(getPassword());
            user.setPassword(hashedPassword);
            user.setPassword(hashedPassword);
            useri.setDesignation(designation);
            useri.setRole(role);
            user.setRoleAgent(useri);
            //getUsermetierimpl().addAgent(user);
            //refmetierimpl.addRef(new Useri(designation,role), new Agent(getIm(),getName(),hashedPassword,new Useri(designation,role)));
            //refmetierimpl.addRef(useri,user);
            usermetierimpl.addUser(useri);
            usermetierimpl.addAgent(user);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Data Saved"));
            return SUCCESS;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        FacesContext.getCurrentInstance().addMessage("myForm:password1", new FacesMessage("Password Doesnt Match"));
        return ERROR;
    }

    /*Mbola tsy vita bureau, porte, adresse*/

    public String addModeAcquisition()
    {
        modeAcquisition = new ModeAcquisition(getDesignation());
        Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
        refmetierimpl.addRef(modeAcquisition,agent);
        return SUCCESS;
    }

    public String addModeAcquisitionCA()
    {
        modeAcquisition = new ModeAcquisition(getDesignation());
        Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
        refmetierimpl.addRef(modeAcquisition,agent);
        return SUCCESS;
    }

    public String addModeDecharge()
    {
        motifDecharge = new MotifDecharge(getDesignation());
        Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
        refmetierimpl.addRef(motifDecharge,agent);
        return SUCCESS;
    }

    public String addFinancement()
    {
        financement = new Financement(getDesignation());
        Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
        refmetierimpl.addRef(financement,agent);
        return SUCCESS;
    }

    public String addFournisseur()
    {
        fournisseur = new Fournisseur(getDesignation());
        Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
        refmetierimpl.addRef(fournisseur,agent);
        return SUCCESS;
    }

    public String addFournisseurCA()
    {
        fournisseur = new Fournisseur(getDesignation());
        Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
        refmetierimpl.addRef(fournisseur,agent);
        return SUCCESS;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(String nomenclature) {
        this.nomenclature = nomenclature;
    }

    public IRefMetier getRefmetierimpl() {
        return refmetierimpl;
    }

    public void setRefmetierimpl(IRefMetier refmetierimpl) {
        this.refmetierimpl = refmetierimpl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getIm() {
        return im;
    }

    public void setIm(Long im) {
        this.im = im;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public IUserMetier getUsermetierimpl() {
        return usermetierimpl;
    }

    public void setUsermetierimpl(IUserMetier usermetierimpl) {
        this.usermetierimpl = usermetierimpl;
    }
    
    public String addBureau() throws SQLException  {
        Bureau b = new Bureau(this.getDesignation(), this.getCodeBureau());
        Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
        refmetierimpl.addRef(b,agent);
        return SUCCESS;
    }

     public String addBureauCA() throws SQLException  {
        Bureau b = new Bureau(this.getDesignation(), this.getCodeBureau());
        Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
        refmetierimpl.addRef(b,agent);
        return SUCCESS;
    }
    
    public String addLocalite() {

        Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
        refmetierimpl.addRef(new Localite(this.getDesignation()), agent);
        return SUCCESS;
    }
    
    public String addPoste() {

        Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
        refmetierimpl.addRef(new Poste(this.getDesignation()), agent);
        return SUCCESS;
    }
    
    public String addMotifSortie() {

        Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
        refmetierimpl.addRef(new MotifSortie(this.getDesignation()), agent);
        return SUCCESS;
    }
    
    public String addAdresse() {
        Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
        refmetierimpl.addRef(new Adresse(this.getDesignation()), agent);
        return SUCCESS;
    }

    public String addAdresseCA() {
        Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
        refmetierimpl.addRef(new Adresse(this.getDesignation()), agent);
        return SUCCESS;
    }

    public String addTypeObjet() {
        Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
        refmetierimpl.addRef(new TypeObjet(getDesignation(), getCaracteristique()), agent);
        return SUCCESS;
    }
    
    public String getCodeBureau() {
        return codeBureau;
    }
    public void setCodeBureau(String codeBureau) {
        this.codeBureau = codeBureau;
    }
    public List<Bureau> getListBureau() {
        return bureaumetierimpl.findAllBureaus();
    }
    public void setListBureau(List<Bureau> listBureau) {
        this.listBureau = listBureau;
    }
    public List<Nomenclature> getListNomenclature() {
        ArrayList<Referentiel> r = (ArrayList<Referentiel>)refmetierimpl.listRef(new Nomenclature());
        List<Nomenclature> ds = new ArrayList<Nomenclature>();
        for (Object d :  r)
        {
            if(d instanceof Nomenclature) {
                ds.add((Nomenclature)d);
            }
        }
        return ds;
    }
    public List<TypeObjet> getListTypeObjet() {
        ArrayList<Referentiel> r = (ArrayList<Referentiel>)refmetierimpl.listRef(new Nomenclature());
        List<TypeObjet> ds = new ArrayList<TypeObjet>();
        for (Object d :  r)
        {
            if(d instanceof TypeObjet) {
                ds.add((TypeObjet)d);
            }
        }
        return ds;
    }
    public void setListTypeObjet(List<TypeObjet> listTypeObjet) {
        this.listTypeObjet = listTypeObjet;
    }
    
    
    public String addService() throws SQLException 
    {
        Service service = new Service(this.getDesignation(), this.getCodeService());
        Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
        refmetierimpl.addRef(service,agent);
        return SUCCESS;
    }
    
    public String addDirection() throws SQLException 
    {
        Direction direction = new Direction(this.getDesignation(), this.getCodeDirection());
        Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
        refmetierimpl.addRef(direction,agent);
        return SUCCESS;
    }
    
    public void setListNomenclature(List<Nomenclature> listNomenclature) {
        this.listNomenclature = listNomenclature;
    }
    public String getCodeService() {
        return codeService;
    }
    public void setCodeService(String codeService) {
        this.codeService = codeService;
    }
    public String getCodeDirection() {
        return codeDirection;
    }
    public void setCodeDirection(String codeDirection) {
        this.codeDirection = codeDirection;
    }
    public IBureauMetier getBureaumetierimpl() {
        return bureaumetierimpl;
    }
    public void setBureaumetierimpl(IBureauMetier bureaumetierimpl) {
        this.bureaumetierimpl = bureaumetierimpl;
    }
    public IUtilisateurMetier getUtilisateurmetierimpl() {
        return utilisateurmetierimpl;
    }
    public void setUtilisateurmetierimpl(IUtilisateurMetier utilisateurmetierimpl) {
        this.utilisateurmetierimpl = utilisateurmetierimpl;
    }
    public String addRole() {
        //Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
        Useri useri = new Useri();
        useri.setDesignation(designation);
        useri.setRole(role);
        usermetierimpl.addUser(useri);
        return SUCCESS;
    }


    public String addRoleCA() {
        //Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
        Useri useri = new Useri();
        useri.setDesignation(designation);
        useri.setRole(role);
        usermetierimpl.addUser(useri);
        return SUCCESS;
    }




    //-------------------------GRAND II--------------------------
    private String caracteristique;
    private List<TypeObjet> listTypeObject;
    private TypeObjet typeObjet;
    private List<CodeArticle> listCodeArticle;
    private List<CodeArticle> listCodeArticleByTypeObject;
    private Float prix;
    private Agent  agentDest;



    private List<OpEntreeArticle> listOpEntreeArticle;
    private List<OpSortieArticle> listOpSortieArticle;

    private List<Agent> listAgentDestinataire;

    private OpEntreeArticle opEntreeArticle;
    private OpSortieArticle opSortieArticle;
    private String motif;




    private List<ArticleEx> listArticleEx;



    public void setCaracteristiqueObjet()
    {
        TypeObjet t = new TypeObjet();
        Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
        t.setCaracteristique(getCaracteristique());
        t.setDesignation(getDesignation());
        refmetierimpl.addRef(t,agent);
    }



    public String getCaracteristique() {
        return caracteristique;
    }

    public void setCaracteristique(String caracteristique) {
        this.caracteristique = caracteristique;
    }

    public List<TypeObjet> getListTypeObject() {
        ArrayList<Referentiel> r = (ArrayList<Referentiel>)refmetierimpl.listRef(new TypeObjet());
        List<TypeObjet> ds = new ArrayList<TypeObjet>();
        for (Object d :  r)
        {
            if(d instanceof MotifSortie) {
                ds.add((TypeObjet)d);
            }
        }
        return ds;
    }

    public void setListTypeObject(List<TypeObjet> listTypeObject) {
        this.listTypeObject = listTypeObject;
    }


    public TypeObjet getTypeObjet() {
        return typeObjet;
    }

    public void setTypeObjet(TypeObjet typeObjet) {
        this.typeObjet = typeObjet;
    }

    public void addCodeArticle()
    {
        CodeArticle c = new CodeArticle();
        c.setDesignation(getDesignation());
        c.setTypeObjet(getTypeObjet());
        usermetierimpl.addCodeArticle(c);
    }




    public List<ArticleEx> getListArticleEx() {
        return usermetierimpl.listCodeArticle();
    }

    public void setListArticleEx(List<ArticleEx> list) {
        this.listArticleEx = list;
    }








    public List<CodeArticle> getListCodeArticle() {
        return usermetierimpl.listCodeArticle();
    }

    public void setListCodeArticle(List<CodeArticle> listCodeArticle) {
        this.listCodeArticle = listCodeArticle;
    }


    public List<CodeArticle> getListCodeArticleByTypeObject() {
        return usermetierimpl.listCodeArticleByTypeObj(getTypeObjet());
    }

    public void setListCodeArticleByTypeObject(List<CodeArticle> listCodeArticleByTypeObject) {
        this.listCodeArticleByTypeObject = listCodeArticleByTypeObject;
    }

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

    public OpEntreeArticle addRequeteOpEntree()
    {
        ArticleNouv a = new ArticleNouv();
        Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
        a.setFournisseur(getFournisseur());
        a.setPrix(getPrix());
        return usermetierimpl.reqEntrerArticle(a,agent);
    }

    public OpSortieArticle addRequeteSortie() throws Exception {
        ArticleNouv a = new ArticleNouv();
        Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
        a.setFournisseur(getFournisseur());
        a.setPrix(getPrix());
        return usermetierimpl.reqSortirArticle(a,agent,getAgentDest());
    }

    public List<OpEntreeArticle> getListOpEntreeArticle() {
        return listOpEntreeArticle;
    }

    public void setListOpEntreeArticle(List<OpEntreeArticle> listOpEntreeArticle) {
        this.listOpEntreeArticle = listOpEntreeArticle;
    }


    public List<OpSortieArticle> getListOpSortieArticle() {
        return listOpSortieArticle;
    }

    public void setListOpSortieArticle(List<OpSortieArticle> listOpSortieArticle) {
        this.listOpSortieArticle = listOpSortieArticle;
    }
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


    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }


    public List<Agent> getListAgentDestinataire() {
        return listAgentDestinataire;
    }

    public void setListAgentDestinataire(List<Agent> listAgentDestinataire) {
        this.listAgentDestinataire = listAgentDestinataire;
    }


    public Agent getAgentDest() {
        return agentDest;
    }

    public void setAgentDest(Agent agentDest) {
        this.agentDest = agentDest;
    }


    //validation
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







}
