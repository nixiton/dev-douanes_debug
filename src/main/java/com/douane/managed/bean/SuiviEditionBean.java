package com.douane.managed.bean;

import com.douane.entite.*;
import com.douane.metier.user.IUserMetier;
import com.douane.model.EtatOperation;
import come.douane.dao.operation.IOperationDAO;
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
        Date sdate = new GregorianCalendar(2010, Calendar.JANUARY, 1).getTime();
        Date edate = new GregorianCalendar(2018, Calendar.DECEMBER, 30).getTime();
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
     
    

}
