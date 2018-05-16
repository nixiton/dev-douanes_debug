package com.douane.managed.bean;

import com.douane.entite.*;
import com.douane.metier.user.IUserMetier;
import com.douane.model.EtatOperation;
import come.douane.dao.operation.IOperationDAO;

import org.hamcrest.core.IsInstanceOf;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.SessionScoped;
import java.util.*;

import org.springframework.stereotype.Component;

import com.douane.requesthttp.RequestFilter;

/**
 * Created by hasina on 11/3/17.
 */


@ManagedBean(name="suivieditionBean")
@ViewScoped
public class SuiviEditionBean {


    @ManagedProperty(value="#{usermetier}")
    IUserMetier usermetierimpl;

    private Agent agentOperateur;
    private Direction direction;
    private Date startDate;
    private Date endDate;
    
    private Operation curentOperation;

    public IOperationDAO getOperationdao() {
        return operationdao;
    }

    public void setOperationdao(IOperationDAO operationdao) {
        this.operationdao = operationdao;
    }

    @Autowired
    @ManagedProperty(value="#{operationdao}")
    private IOperationDAO operationdao;

    /*private List<OpEntree> listOperationEntree;
    private List<OpSortie> listOperationSortie;
    private List<Operation> listOperatoinByOperateur;
    private List<OpEntree> listOperationEntreeByOperator;
    private List<OpSortie> listOperationSortieByOperator;
    private List<Operation> listOperatoinByDirection;
    private List<OpEntree> listOperationEntreeByDirection;
    private List<OpSortie> listOperationSortieByDirection;
    private List<Operation> listOperationBetween;
    private List<OpEntree> listOperationEntreeByMateriel;
    private List<OpSortie> listOperationSortieByMateriel;
    private List<OpEntree> listOperationEntreeByMaterielByDate;
    private List<OpSortie> listOperationSortieByMaterielByDate;
    private List<OpAttribution> listOperationAttribution;
    private List<OpDettachement> listOperationDetachement;
    private List<OpAttribution> listOperationAttributionByOperator;
    private List<OpDettachement> listOperationDetachementByOperator;
    private List<OpAttribution> listOperationAttributionByDirection;
    private List<OpDettachement> listOperationDeetachementByDirection;
    private List<OpAttribution> listOperationAttributionByMateriel;
    private List<OpDettachement> listOperationDetachementByMateriel;*/

    private Materiel materiel;


    private int annee;


    //----------ALL LIST BY METHOD------------------
    private List<Operation> listAllOperation;
    private List<OpEntree> listOperationEntree;
    private List<OpSortie> listOperationSortie;
    private List<Operation> listOperationByOperateur;
    private List<OpEntree> listOperationEntreeByOperator;
    private List<OpSortie> listOperationSortieByOperator;
    private List<Operation> listOperatoinByDirection;
    private List<OpEntree> listOperationEntreeByDirection;
    private List<OpSortie> listOperationSortieByDirection;
    private List<Operation> listOperationBetween;

    private List<Operation> listOperationByDirectionByYearByDateAsc;

    private List<OpEntree> listOperationEntreeByMateriel;
    private List<OpSortie> listOperationSortieByMateriel;
    private List<OpEntree> listOperationEntreeByMaterielByDate;
    private List<OpSortie> listOperationSortieByMaterielByDate;
    private List<OpAttribution> listOperationAttribution;
    private List<OpDettachement> listOperationDetachement;
    private List<OpAttribution> listOperationAttributionByOperator;
    private List<OpDettachement> listOperationDetachementByOperator;
    private List<OpDettachement> listOperationDetachementByDirection;
    private List<OpAttribution> listOperationAttributionByDirection;
    private List<OpDettachement> listOperationDeetachementByDirection;
    private List<OpAttribution> listOperationAttributionByMateriel;
    private List<OpDettachement> listOperationDetachementByMateriel;
    
    //----------ALL LIST BY METHOD------------------


    private List<Materiel> listMaterielByDet;

    private Float total;


        public void setAnnee(int t){
        this.annee = t;
    }


        public int getAnnee(){
        return this.annee;
    }

        public String setAnnee1(int t){
        this.annee = t;
        return "annee";
    }


        public void setTotal(Float t){
        this.total = t;
    }

    public Float getTotal(){
        return this.total;
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






    public List<Operation> getListAllOperation()
    {
        return usermetierimpl.getListOp();
    }

    public void setListAllOperation(List<Operation> l)
    {
        this.listAllOperation = l;
    }

    public List<OpEntree> getListOperationEntree()
    {
        //setListOperationEntree(usermetierimpl.getListOpEntree());
        //return listOperationEntree;
        return usermetierimpl.getListOpEntree();
        
        
    }

    public void setListOperationEntree(List<OpEntree> l)
    {

        this.listOperationEntree = l;
    }

    public List<OpSortie> getListOperationSortie()
    {
        return usermetierimpl.getListOpSortie();
    }

    public void setListOperationSortie(List<OpSortie> l)
    {
        this.listOperationSortie = l;
    }


    //get Agent and set Agent operator
    public Agent getAgentOperateur() {
        return agentOperateur;
    }

    public void setAgentOperateur(Agent agentOperateur) {
        this.agentOperateur = agentOperateur;
    }

    //-------------GET List of operations by OPERATOR --------------------------------
    //get direction
    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public List<Operation> getListOperationByOperateur()
    {
        return usermetierimpl.getListOpByOperator(this.agentOperateur);
    }

    public void setListOperationByOperateur(List<Operation> l )
    {
        this.listOperationByOperateur = l;
    }


    public List<OpEntree> getListOperationEntreeByOperator()
    {
        return usermetierimpl.getListOpEntreeByOperator(this.agentOperateur);
    }

    public void setListOperationEntreeByOperator(List<OpEntree> l)
    {
        this.listOperationEntreeByOperator = l;
    }

    public List<OpSortie> getListOperationSortieByOperator()
    {
        return usermetierimpl.getListOpSortieByOperator(this.agentOperateur);
    }

    public void setListOperationSortieByOperator(List<OpSortie> l)
    {
        this.listOperationSortieByOperator = l;
    }


    //-------------GET List of operations by DIRECTION --------------------------------
    public List<Operation> getListOperatoinByDirection()
    {
        Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
        return usermetierimpl.getListOpByDirection(agent.getDirection());
    }

    public void setListOperatoinByDirection(List<Operation> l)
    {
        this.listOperatoinByDirection = l;
    }

    public List<OpEntree> getListOperationEntreeByDirection()
    {
        Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
        return usermetierimpl.getListOpEntreeByDirection(agent.getDirection());
    }
    public void setListOperationEntreeByDirection(List<OpEntree> l)
    {
        this.listOperationEntreeByDirection = l;
    }

    public List<OpSortie> getListOperationSortieByDirection()
    {
        Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
        return usermetierimpl.getListOpSortieByDirection(agent.getDirection());
    }
    public void setListOperationSortieByDirection(List<OpSortie> l)
    {
        this.listOperationSortieByDirection = l;
    }

    //------------GET List of Operations By DATE-------------------
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Operation> getListOperationBetween(Date startDate, Date endDate)
    {
        return getListOperationBetween(startDate, endDate);
    }

    public void setListOperationBetween(List<Operation> l)
    {
        this.listOperationBetween = l;
    }



    public List<Operation> getListOperationByDirectionByYearByDateAsc()
    {
        //return getListOperationBetween(startDate, endDate);
    	
        Agent cur = (Agent) RequestFilter.getSession().getAttribute("agent");
        Date sdate = new GregorianCalendar(getAnnee(), Calendar.JANUARY, 1).getTime();
        Date edate = new GregorianCalendar(getAnnee(), Calendar.DECEMBER, 30).getTime();
        return usermetierimpl.getListOperationByDirectionByYearByDateAsc(cur.getDirection(), sdate, edate);
    }

    public void setListOperationByDirectionByYearByDateAsc(List<Operation> l)
    {
        this.listOperationByDirectionByYearByDateAsc = l;
    }




    //------------GET List of Operations By Materiel-------------------
    public Materiel getMateriel() {
        return materiel;
    }

    public void setMateriel(Materiel materiel) {

        System.out.print("======================================SET MAT=================================================================");

        this.materiel = materiel;
    }

    public List<OpEntree> getListOperationEntreeByMateriel()
    {
        return usermetierimpl.getListOpEntreeByMat(getMateriel());
    }
    public void setListOperationEntreeByMateriel(List<OpEntree> l)
    {
        this.listOperationEntreeByMateriel = l;
    }

    public List<OpSortie> getListOperationSortieByMateriel()
    {
        return usermetierimpl.getListOpSortieByMat(getMateriel());
    }
    public void setListOperationSortieByMateriel(List<OpSortie> l)
    {
        this.listOperationSortieByMateriel = l;
    }

    //------------GET List of Materiel By Date-------------------
    public List<OpEntree> getListOperationEntreeByMaterielByDate(Date startDate, Date endDate)
    {
        return usermetierimpl.getListOpEntreeByMatBDate(getMateriel(),startDate,endDate);
    }
    public void setListOperationEntreeByMaterielByDate(List<OpEntree> l)
    {
        this.listOperationEntreeByMaterielByDate = l;
    }


    public List<OpSortie> getListOperationSortieByMaterielByDate(Date startDate, Date endDate)
    {
        return usermetierimpl.getListOpSortieByMatBDate(getMateriel(),startDate,endDate);
    }
    public void setListOperationSortieByMaterielByDate(List<OpSortie> l)
    {
        this.listOperationSortieByMaterielByDate = l;
    }

    //------------------liste des operations atributions et dettachements ---------------------
    public List<OpAttribution> getListOperationAttribution()
    {
        return usermetierimpl.getListOpAttribution();
    }
    public void setListOperationAttribution(List<OpAttribution> l)
    {
        this.listOperationAttribution = l;
    }

    public List<OpDettachement> getListOperationDetachement()
    {
        return usermetierimpl.getListOpDettachement();
    }
    public void setListOperationDetachement(List<OpDettachement> l)
    {
        this.listOperationDetachement = l;
    }

    public List<OpAttribution> getListOperationAttributionByOperator()
    {
        return usermetierimpl.getListOpAttrByOperator(getAgentOperateur());
    }
    public void setListOperationAttributionByOperator(List<OpAttribution> l)
    {
        this.listOperationAttributionByOperator = l;
    }

    public List<OpDettachement> getListOperationDetachementByOperator()
    {
        return usermetierimpl.getListOpDettByOperatort(getAgentOperateur());
    }


    public void setListOperationDetachementByOperator(List<OpDettachement> l)
    {
        this.listOperationDetachementByOperator = l;
    }

    public List<OpAttribution> getListOperationAttributionByDirection()
    {
        Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
        return usermetierimpl.getListOpAttrByDirection(agent.getDirection());
    }
    public void setListOperationAttributionByDirection(List<OpAttribution> l)
    {
        this.listOperationAttributionByDirection = l;
    }

        public List<OpDettachement> getListOperationDeetachementByDirection()
    {
        Agent agent = (Agent)RequestFilter.getSession().getAttribute("agent");
        return usermetierimpl.getListOpDettByDirection(agent.getDirection());
    }
    public void setListOperationDeetachementByDirection(List<OpDettachement> l)
    {
        this.listOperationDeetachementByDirection = l;
    }

    public List<OpAttribution> getListOperationAttributionByMateriel()
    {
        return usermetierimpl.getListOpAttrByMat(getMateriel());
    }

    public List<OpAttribution> getListOperationAttributionByMateriel1(Materiel m)
    {
        return usermetierimpl.getListOpAttrByMat(m);
    }

    public void setListOperationAttributionByMateriel(List<OpAttribution> l)
    {
        this.listOperationAttributionByMateriel = l;
    }

    public List<OpDettachement> getListOperationDetachementByMateriel()
    {
        return usermetierimpl.getListOpDettByMat(getMateriel());
    }
    public void setListOperationDetachementByMateriel(List<OpDettachement> l)
    {
        this.listOperationDetachementByMateriel = l;
    }




    //----------------NEW SETTER AND GETTER---------------------

    public IUserMetier getUsermetierimpl() {
        return usermetierimpl;
    }

    public void setUsermetierimpl(IUserMetier usermetierimpl) {
        this.usermetierimpl = usermetierimpl;
    }

    public void setCurentOperation(Operation operation){
        this.curentOperation = operation;
    }
    
    public void setCurentOperation2(Operation operation){
        this.curentOperation = operation;

        if(((OpAttribution)operation).getDetenteur()!=null){
           this.setListMaterielByDet(usermetierimpl.getListMatByDet(((OpAttribution)operation).getDetenteur()));

            ListIterator<Materiel> it = this.getListMaterielByDet().listIterator();
            if (it!=null) {
                this.setTotal(Float.parseFloat("0"));
               while(it.hasNext()){
                 setTotal(this.total+(Float)(it.next().getPu()));
              } 
            }  
        }
        else{
            
        }

    }
    public Operation getCurentOperation(){
        return this.curentOperation;
    }



    //-----------------GETTER AND SETTER FOR OPERATION --------------
    private List<OpEntree> listOpEn;
    private List<OpSortie> listOpSo;
    private List<OpAttribution> listOpAttr;
    private List<OpDettachement> listOpDet;


    public List<OpEntree> getListOpEn() {
        return usermetierimpl.getListOpEntreeByMatAndByState(getMateriel(), EtatOperation.ACCEPTED);
    }

    public void setListOpEn(List<OpEntree> listOpEn) {
        this.listOpEn = listOpEn;
    }

    public List<OpSortie> getListOpSo() {
        return usermetierimpl.getListOpSortieByMatAndByState(getMateriel(), EtatOperation.ACCEPTED);
    }

    public void setListOpSo(List<OpSortie> listOpSo) {
        this.listOpSo = listOpSo;
    }

    public List<OpAttribution> getListOpAttr() {
        return usermetierimpl.getListOpAttrByMatAndByState(getMateriel(), EtatOperation.ACCEPTED);
    }

    public void setListOpAttr(List<OpAttribution> listOpAttr) {
        this.listOpAttr = listOpAttr;
    }

    public List<OpDettachement> getListOpDet() {
        return usermetierimpl.getListOpDettByMatAndByState(getMateriel(), EtatOperation.ACCEPTED);
    }

    public void setListOpDet(List<OpDettachement> listOpDet) {
        this.listOpDet = listOpDet;
    }


    //-----------------TO DO 30 12--------------
    private List<Operation> listOpEntreeAndSortieByDirectionByYearByDateAsc;

    public List<Operation>getListOpEntreeAndSortieByDirectionByYearByDateAsc()
    {
        Agent cur = (Agent) RequestFilter.getSession().getAttribute("agent");
        Date sdate = new GregorianCalendar(2010, Calendar.JANUARY, 1).getTime();
        Date edate = new GregorianCalendar(2018, Calendar.DECEMBER, 30).getTime();
        //List<Operation> l = usermetierimpl.getListOpEntreeAndSortieByDirectionByYearByDateAsc(cur.getDirection(), sdate, edate);
        System.out.print("=======================================================================================================");
            System.out.print("=======================================================================================================");
                System.out.print("=======================================================================================================");
        List<Operation> l = operationdao.getListOpEntreeAndSortieByDirectionByYearByDateAsc(cur.getDirection(), sdate, edate);
        for(Operation o : l)
        {
            System.out.print("listOperation======="+o.getState());
        }
        return usermetierimpl.getListOpEntreeAndSortieByDirectionByYearByDateAsc(cur.getDirection(), sdate, edate);
        //return usermetierimpl.getListOpEntreeAndSortieByDirectionByYearByDateAsc(cur.getDirection(), getStartDate(), getEndDate());
    }

    public void setListOpEntreeAndSortieByDirectionByYearByDateAsc(List<Operation> listOpEntreeAndSortieByDirectionByYearByDateAsc) {
        this.listOpEntreeAndSortieByDirectionByYearByDateAsc = listOpEntreeAndSortieByDirectionByYearByDateAsc;
    }


    //-------TEST FINAL-------

    public List<OpEntree> getListOpEntreeByDirectionByYearByDateAsc(Direction d,  Date startDate, Date endDate)
    {
        return usermetierimpl.getListOpEntreeByDirectionByYearByDateAsc(d,startDate,endDate);
    }
    public List<OpSortie> getListOpSortieByDirectionByYearByDateAsc(Direction d,  Date startDate, Date endDate)
    {
        return usermetierimpl.getListOpSortieByDirectionByYearByDateAsc(d,startDate,endDate);
    }
    
   

	//-----NEW FORM OF GETTER
    private List<OperationES> listOpESForJournal;
    
    public List<OperationES> getListOpESForJournal() {
    	Agent cur = (Agent) RequestFilter.getSession().getAttribute("agent");
    	Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		Date sdate = new GregorianCalendar(year-2, Calendar.JANUARY, 1).getTime();
        Date edate = new GregorianCalendar(year+1, Calendar.DECEMBER, 30).getTime();
		return usermetierimpl.getListOpESForJournal(cur.getDirection(),sdate,edate);
	}

	public void setListOpESForJournal(List<OperationES> listOpESForJournal) {
		this.listOpESForJournal = listOpESForJournal;
	}
	
	private List<OpSortie> listOpSortieValideByDirection;
	
	public List<OpSortie> getListOpSortieValideByDirection() {
		Agent cur = (Agent) RequestFilter.getSession().getAttribute("agent");
		return usermetierimpl.getListOpSortieValideByDirection(cur.getDirection());
	}

	public void setListOpSortieValideByDirection(List<OpSortie> listOpSortieValideByDirection) {
		this.listOpSortieValideByDirection = listOpSortieValideByDirection;
	}
	
	

	//------------EDITION
	
	

	public void setListobjectForInvetaire(List<Object[]> listobjectForInvetaire) {
		this.listobjectForInvetaire = listobjectForInvetaire;
	}

	public List<OpEntree> getListOpentreeForOrdre() {
		Agent cur = (Agent) RequestFilter.getSession().getAttribute("agent");
		return usermetierimpl.listOpentreeByStateByDirection(EtatOperation.ACCEPTED,cur.getDirection());
	}

	public void setListOpentreeForOrdre(List<OpEntree> listOpentreeForOrdre) {
		this.listOpentreeForOrdre = listOpentreeForOrdre;
	}

	

	private List<Object[]> listobjectForInvetaire;
	
	private List<OpEntree> listOpentreeForOrdre;
    
	/*
	 * LISTE OF METHODS FOR CA EDITIONS
	 * 
	 */
	
	private List<Operation> listOpESArtByDirection; 
	public List<Operation> getListOpESArtByDirection() {
		Agent cur = (Agent) RequestFilter.getSession().getAttribute("agent");
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		Date sdate = new GregorianCalendar(year-2, Calendar.JANUARY, 1).getTime();
        Date edate = new GregorianCalendar(year+1, Calendar.DECEMBER, 30).getTime();
		return usermetierimpl.getListOpESArtValideByDirection(cur.getDirection(),sdate,edate);
	}

	public void setListOpESArtByDirection(List<Operation> listOpESArtByDirection) {
		this.listOpESArtByDirection = listOpESArtByDirection;
	}
	
	private List<Object[]> listESForJournal;

	public List<Object[]> getListESForJournal() {
		if(listESForJournal ==null) {
		Agent cur = (Agent) RequestFilter.getSession().getAttribute("agent");
    	Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		Date sdate = new GregorianCalendar(year-2, Calendar.JANUARY, 1).getTime();
        Date edate = new GregorianCalendar(year+1, Calendar.DECEMBER, 30).getTime();
		List<OperationES> listop = usermetierimpl.getListOpESForJournal(cur.getDirection(),sdate,edate);
		List<Object[]>listobjectForJournal = new ArrayList<Object[]>();
		for(OperationES op:listop) {
			Object[] row = new Object[12];
			if(op instanceof OpEntree) {
				List <Object[]> bydesignation1 = (this.getDesingationByOpEntree(op));
				
				for(Object[] nom: bydesignation1) {
					List <Object[]> liste = (List <Object[]>)nom[2];
					for(Object[] des: liste) {
					Designation d = (Designation)des[0];
					//id
					row[0] = op.getId();
					//numero d'ordre
					row[1] = op.getNumoperation();
					//date
					row[2] = op.getDate();
					//origine
					row[3] = d.getOrigine();
					// designation
					row[4] = d.getTypematerieladd().getDesignation() + " - "  + 
							 d.getMarque() + " - "  +
							 d.getRenseignement()	+ " - "  
							//mat.getNumSerie();
							 ;
					//espece unite
					row[5] = d.getEspeceUnite();
					//pu
					row[6] = d.getPu();
					//nombre par desingation entree
					row[7] = des[1];
					//total entree
					row[8] = d.getPu()*(Long)row[7];
					//nombre par desingation sortie
					row[9] = 0;
					//total sortie
					row[10] = 0;
					row[11] = d;
					listobjectForJournal.add(row);
					row = new Object[12];
					}
				}/*
				for(Materiel mat :((OpEntree) op).getListMat()) {
					//id
					row[0] = op.getId();
					//numero d'ordre
					row[1] = op.getNumoperation();
					//date
					row[2] = op.getDate();
					//origine
					row[3] = mat.getDesign().getOrigine();
					// designation
					row[4] = mat.getDesign().getTypematerieladd().getDesignation() + " - "  + 
							 mat.getDesign().getMarque() + " - "  +
							 mat.getDesign().getRenseignement()	+ " - "  +
							mat.getNumSerie();
					//espece unite
					row[5] = mat.getDesign().getEspeceUnite();
					//pu
					row[6] = mat.getDesign().getPu();
					//nombre par desingation entree
					row[7] = 1;
					//total entree
					row[8] = mat.getDesign().getPu()*(Integer)row[7];
					//nombre par desingation sortie
					row[9] = 0;
					//total sortie
					row[10] = 0;
					row[11] = mat;
					listobjectForJournal.add(row);
					row = new Object[12];
				}*/
				
			}
			else if(op instanceof OpSortie) {
				//id
				row[0] = op.getId();
				//numero d'ordre
				row[1] = op.getNumoperation();
				//date
				row[2] = op.getDate();
				//origine
				row[3] = ((OpSortie) op).getMotifsortie().getDesignation();
				// designation
				Materiel mat = op.getMat();
				row[4] = mat.getDesign().getTypematerieladd().getDesignation() + " - "  + 
						 mat.getDesign().getMarque() + " - "  +
						 mat.getDesign().getRenseignement() + " - "  +
						 mat.getNumSerie();
				//espece unite
				row[5] = mat.getDesign().getEspeceUnite();
				//pu
				row[6] = mat.getDesign().getPu();
				//nombre par desingation entree
				row[7] = 0;
				//total entree
				row[8] = 0;
				//nombre par desingation sortie
				row[9] = 1L;
				//total sortie
				row[10] = mat.getDesign().getPu()*(Long)row[9];
				row[11] = mat.getDesign();
				listobjectForJournal.add(row);
				row = new Object[12];
			}
			}
		
		listESForJournal = listobjectForJournal;
			
		}
		
		return listESForJournal;
	}

	public void setListESForJournal(List<Object[]> listESForJournal) {
		this.listESForJournal = listESForJournal;
	}
	
	

	private List<Object[]> listESForGrandLivre;
	public List<Object[]> getListESForGrandLivre() {
		if(listESForGrandLivre ==null) {
			
			Agent cur = (Agent) RequestFilter.getSession().getAttribute("agent");
	    	Date date = new Date();
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			int year = calendar.get(Calendar.YEAR);
			Date sdate = new GregorianCalendar(year-2, Calendar.JANUARY, 1).getTime();
	        Date edate = new GregorianCalendar(year+1, Calendar.DECEMBER, 30).getTime();
			List<OperationES> listop = usermetierimpl.getListOpESForJournal(cur.getDirection(),sdate,edate);
			List<Object[]>listobjectForLivre= new ArrayList<Object[]>();
			for(OperationES op:listop) {
				Object[] row = new Object[11];
				if(op instanceof OpEntree) {
					List <Object[]> bydesignation1 = (this.getDesingationByOpEntree(op));
					
					for(Object[] nom: bydesignation1) {
						List <Object[]> liste = (List <Object[]>)nom[2];
						for(Object[] des: liste) {
						Designation d = (Designation)des[0];
						//id
						row[0] = d;
						//numero d'ordre
						row[1] = op;
						//origine
						row[2] = d.getOrigine();
						//nombre par desingation entree annee X
						row[3] = des[1];
						//total entree annee X
						row[4] = d.getPu()*(Long)row[3];
						//nombre par desingation sortie
						row[5] = 0;
						//total sortie
						row[6] = 0;
						
						//existant X-1
						row[7] = 0L;
						//valeur X-1
						row[8] = (Long)row[7] * d.getPu();
						
						//restant X
						row[9] = (Long)row[3] + (Long)row[7];
						//valeur restant X
						//row[10] = (Float)row[8] + d.getPu()*(Long)row[9];
						row[10] = d.getPu()*(Long)row[9];
						
						listobjectForLivre.add(row);
						row = new Object[11];
						}
					}
					/*for(Materiel mat :((OpEntree) op).getListMat()) {
						row[0] = mat;
						row[1] = op;
						
						//origine
						row[2] = mat.getDesign().getOrigine();
						//nombre par desingation entree
						row[3] = 1;
						//total entree
						row[4] = mat.getDesign().getPu()*(Integer)row[3];
						//nombre par desingation sortie
						row[5] = 0;
						//total sortie
						row[6] = 0;
						
						//existant X-1
						row[7] = 0;
						//valeur X-1
						row[8] = 0;
						
						//restant X
						row[9] = 1;
						//valeur restant X-X
						row[10] = mat.getDesign().getPu();
						
						listobjectForLivre.add(row);
						row = new Object[11];
					}*/
					
				}
				else if(op instanceof OpSortie) {
					Materiel mat = op.getMat();
					row[0] = mat.getDesign();
					row[1] = op;
					
					//origine
					row[2] = ((OpSortie) op).getMotifsortie().getDesignation();
					//nombre par desingation entree
					row[3] = 0;
					//total entree
					row[4] = 0;
					//nombre par desingation sortie
					row[5] = 1L;
					//total sortie	
					row[6] = mat.getDesign().getPu()*(Long)row[5];
					
					//existant X-1
					row[7] = 1L;
					//valeur X-1
					row[8] = mat.getDesign().getPu() * (Long) row[7];
					
					//restant X
					row[9] = (Long)row[7] - (Long)row[5];
					//valeur restant X-X
					row[10] = (Long) row[9] * mat.getDesign().getPu();
					
					
					listobjectForLivre.add(row);
					row = new Object[11];
				}
				}
			
			listESForGrandLivre = listobjectForLivre;
				
			}
		return listESForGrandLivre;
	}

	public void setListESForGrandLivre(List<Object[]> listESForGrandLivre) {
		this.listESForGrandLivre = listESForGrandLivre;
	}
	
	public List<Object[]> getDesingationByOpEntree(Operation op) {
		List<Object[]> results = usermetierimpl.listDesignationByOperationEntree((OpEntree) op);
		HashMap<Nomenclature, Float> bynom = new HashMap<Nomenclature, Float>();
		for (Object[] o : results) {
			Designation a = (Designation) (o[0]);
			Long nbr = (Long) (o[1]);
			if (bynom.isEmpty()) {
				bynom.put(a.getNomenMat(), nbr * a.getPu());
			} else {
				for (Map.Entry<Nomenclature, Float> entry : bynom.entrySet()) {

					if (entry.getKey() == a.getNomenMat()) {
						entry.setValue(entry.getValue() + (nbr * a.getPu()));
					} else {
						bynom.put(a.getNomenMat(), nbr * a.getPu());
					}
				}
			}
		}
		List<Object[]>resultatfinal = new ArrayList<Object[]>();
		for (Map.Entry<Nomenclature, Float> entry : bynom.entrySet()) {
			Object[] item = new Object[3];
			item[0] = entry.getKey();
			item[1] = entry.getValue();
			List<Object[]> ourlist = new ArrayList<Object[]>();
			for(Object[]o:results) {
				Designation a = (Designation) o[0];
				if(a.getNomenMat() == entry.getKey()) {
					ourlist.add(o);
				}
			}
			item[2] = ourlist;
			resultatfinal.add(item);
		}
		return resultatfinal;
	}
	
	public List<Object[]> getListobjectForInvetaire() {
		if(listobjectForInvetaire==null) {
		Agent cur = (Agent) RequestFilter.getSession().getAttribute("agent");
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		Date sdate = new GregorianCalendar(year, Calendar.APRIL, 30).getTime();
        Date edate = new GregorianCalendar(year, Calendar.DECEMBER, 31).getTime();
		System.out.println("RRRRRRRRRRR Begin:");
		List<Object[]> r = usermetierimpl.getListObjectForinvetaire(cur.getDirection(),sdate,edate);
		System.out.println("RRRRRRRRRRR Ending:");
		/*for(Object[] o:r) {
			System.out.println(String.valueOf(o[0]));
			System.out.println(String.valueOf(o[1]));
		}*/
		List<Object[]> resultstable = new ArrayList<Object[]>();
		
			for(Object[] m: r) { 
				Object[] row = new Object[12];
				Materiel mat = (Materiel) m[1];
				OpSortie o = (OpSortie) m[0];
				//Nomenclature
				row[0] = mat.getDesign().getNomenMat().getNomenclature();
				//Numéros du folio du  grand  livre
				row[1] = mat.getIdMateriel();
				//Désignation du matériel
				row[2] = mat.getDesign().getTypematerieladd().getDesignation() + " - "
						+ mat.getDesign().getRenseignement() + " - "
						+ mat.getDesign().getMarque().getDesignation() + " - "
						+ mat.getNumSerie()	
						;
				//Espèce des unités
				row[3] = mat.getDesign().getEspeceUnite();
				//Prix de l’unité
				row[4] = mat.getDesign().getPu();
				//Existantes au 1er Janvier X
				row[5] = 0;
				//Entrées pendant l’année X
				if(mat.getMyoperationEntree() == null || mat.getMyoperationEntree().getDate().compareTo(sdate) <0 ) {
					row[6] = "Materiel Existant";
					row[5] =1;
				}
				else {
					row[6] = mat.getMyoperationEntree().getNumoperation();
				}
				
				//Sortie pendant l’année X
				if(o ==null) {
					row[7] = "Aucune sortie";
				}
				else {
					row[7] = o.getNumoperation();
				}
				//Reste au 31 déc. X
				row[8] = "reste";
				//Décompte
				row[9] = "decompte";
				row[10] = mat.getDesign().getTypematerieladd();
				row[11] = mat.getDesign();
				resultstable.add(row);
			}
			//group by designation
			Map<Designation, List<Object[]>> map = new HashMap<Designation, List<Object[]>>();

            for (Object[] o : resultstable) {
            	Designation key  = (Designation)o[11];
                if(map.containsKey(key)){
                    List<Object[]> list = map.get(key);
                    list.add(o);

                }else{
                    List<Object[]> list = new ArrayList<Object[]>();
                    list.add(o);
                    map.put(key, list);
                }

            }
            for (Map.Entry<Designation, List<Object[]>> entry : map.entrySet()) {
                System.out.println(entry.getKey().getIdDesignation() + ":" + entry.getValue().size());
            }
			
			
			listobjectForInvetaire= resultstable;
		}
		
		return listobjectForInvetaire;	
	}

}
