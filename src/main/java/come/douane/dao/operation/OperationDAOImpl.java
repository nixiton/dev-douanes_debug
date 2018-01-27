package come.douane.dao.operation;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.douane.entite.*;
import com.douane.model.EtatOperation;
import com.douane.repository.MaterielRepository;
import org.springframework.beans.factory.annotation.Autowired;

import com.douane.repository.MaterielRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class OperationDAOImpl implements IOperationDAO{
	
	@PersistenceContext
	private EntityManager em;
	@Autowired
	private MaterielRepository matrepos;
	
	public OperationDAOImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Agent detacherMat(OpDettachement det) throws Exception{
		// TODO Auto-generated method stub
		//Agent ancienDet = em.find(Agent.class,det.getMat().getDetenteur().getIm()) ;
		TypedQuery<Agent> query = em.createQuery("select a from Agent a "
	       		+ "where a.im=:immatricule"
	       		,Agent.class);
	       query.setParameter("immatricule", det.getMat().getDetenteur().getIm());
	       Agent ancienDet =  query.getSingleResult();
	    System.out.println("denteur will be set"+ det.getMat().getDetenteur().getNomAgent());
		if(ancienDet==null) {
			throw new Exception("materiel non detenu");
		}
		Materiel m = em.find(Materiel.class, det.getMat().getIdMateriel());
		ancienDet.getMatdetenu().remove(m);
		em.merge(ancienDet);
		
		m.setDetenteur(null);
		em.merge(m);
		
		det.valider();
		em.merge(det);
		
		return ancienDet;
	}

	@Override
	public Materiel attribuerMat(OpAttribution attr) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Attribution DAO begin");
		Materiel m = em.find(Materiel.class, attr.getMat().getIdMateriel()) ;
		if(m==null) {
			throw new Exception("Pas de materiel de avec cette Identifiant");
		}
		System.out.println("*************************************AFAKA***************1");
		if(m.getDetenteur()!=null) {
			throw new Exception("Efa attribuer olona io fa mila detachena aloha");
		}
		//m.setCodification("codified"+new Date());
		m.generateCode(countMaterielByTypeByDirect(m.getTypematerieladd(), m.getDirec()));
		System.out.println(m.getCode()+" : code generated ok");
		//m.setDetenteur(attr.getDetenteur());
		//matrepos.save(m);
		//em.persist(m);
		//em.merge(m);
		Agent detent = attr.getDetenteur();
		System.out.println("*************************************AFAKA***************2 "+ attr.getDetenteur().getIm()+"**");
		m.setDetenteur(detent);
		System.out.println("*************************************AFAKA***************3");
		detent.getMatdetenu().add(m);
		System.out.println("*************************************AFAKA***************4");
		//agentrepos.save(detent);
		em.merge(m);
		System.out.println("*************************************AFAKA***************5");
		em.merge(detent);
		System.out.println("*************************************AFAKA***************6");
		attr.valider();
		System.out.println("*************************************AFAKA***************7");
		//oprepos.save(attr);
		em.merge(attr);
		System.out.println("*************************************AFAKA***************8");
		return m;
	}
	
	@Override
	public Materiel attribuerMatEx(MaterielEx matex, Agent detenteur) throws Exception {
		// TODO Auto-generated method stub
				
				Materiel m = em.find(Materiel.class, matex.getIdMateriel()) ;
				System.out.println("Attribution Existant DAO begin for :"+m.getNumSerie());
				if(m.getDetenteur()!=null) {
					throw new Exception("Efa attribuer olona io fa mila detachena aloha");
				}
				//m.setCodification("codified"+new Date());
				m.generateCode(countMaterielByTypeByDirect(m.getTypematerieladd(), m.getDirec()));
				System.out.println(m.getCode()+" : code generated ok");
				//m.setDetenteur(attr.getDetenteur());
				//matrepos.save(m);
				//em.persist(m);
				//em.merge(m);
				TypedQuery<Agent> query = em.createQuery("select a from Agent a "
			       		+ " where a.im=:immatricule"
			       		,Agent.class);
			       query.setParameter("immatricule", detenteur.getIm());
			       Agent detent =  query.getSingleResult();
			    System.out.println("denteur will be set"+ detent.getNomAgent());
				//Agent detent = em.find(Agent.class, detenteur.getIm());
				m.setDetenteur(detent);
				detent.getMatdetenu().add(m);
				//agentrepos.save(detent);
				em.merge(m);
				em.merge(detent);
				return m;
	}


	@Override
	public List<Operation> getListOpByDate(Date startDate, Date endDate, int maxresult) {
		// TODO Auto-generated method stub
		//em.getTransaction().begin();
	       TypedQuery<Operation> query = em.createQuery("select o from Operation o "
	       		+ " where o.date>= :startDate AND o.date<= :endDate"
	       		+ " order by o.date desc",Operation.class);
	       query.setParameter("startDate", startDate, TemporalType.DATE);
	       query.setParameter("endDate", endDate, TemporalType.DATE);
	       query.setMaxResults(maxresult);
	       List<Operation> operations = query.getResultList();
	 
	     //  em.getTransaction().commit();
	 
	       return operations;
	}

	@Override
	public List<OpEntree> getListOpEntreeByByMaterielBDate(Materiel m, Date startDate, Date endDate, int maxresult) {
		// TODO Auto-generated method stub
		TypedQuery<OpEntree> query = em.createQuery("select oe from OpEntree oe "
	       		+ " where oe.date>=:startDate AND oe.date<=:endDate AND "
	       		+ " oe.mat = :mymat",OpEntree.class);
	       query.setParameter("startDate", startDate, TemporalType.DATE);
	       query.setParameter("endDate", endDate, TemporalType.DATE);
	       query.setParameter("mymat", m);
	       query.setMaxResults(maxresult);
	       List<OpEntree> operations = query.getResultList();
		return operations;
	}

	@Override
	public List<OpSortie> getListOpSortieByByMaterielBDate(Materiel m, Date startDate, Date endDate, int maxresult) {
		// TODO Auto-generated method stub
		TypedQuery<OpSortie> query = em.createQuery("select os from OpSortie os "
	       		+ "where os.date>=:startDate AND os.date<=:endDate AND "
	       		+ " os.mat = :mymat",OpSortie.class);
	       query.setParameter("startDate", startDate, TemporalType.DATE);
	       query.setParameter("endDate", endDate, TemporalType.DATE);
	       query.setParameter("mymat", m);
	       query.setMaxResults(maxresult);
	       List<OpSortie> operations = query.getResultList();
		return operations;
	}
	
	//FONCTION UTILE
	@Override
	public List<OpEntree> getListOpEntreeOrderByDate(int maxresult) {
		// TODO Auto-generated method stub
		TypedQuery<OpEntree> query = em.createQuery("select oe from OpEntree oe "
	       		+ " order by date desc "
	       		,OpEntree.class);
		query.setMaxResults(maxresult);
	       
	       List<OpEntree> operations = query.getResultList();
		return operations;
	}
	@Override
	public List<OpSortie> getListOpSortieOrderByDate(int maxresult) {
		// TODO Auto-generated method stub
		TypedQuery<OpSortie> query = em.createQuery("select os from OpSortie os "
	       		+ " order by date desc "
	       		,OpSortie.class);
		query.setMaxResults(maxresult);
	       
	       List<OpSortie> operations = query.getResultList();
		return operations;
	}

	@Override
	public List<Operation> getListOperationOrderByDate(int maxresult) {
		// TODO Auto-generated method stub
		TypedQuery<Operation> query = em.createQuery("select o from Operation o "
	       		+ " order by date desc "
	       		,Operation.class);
		query.setMaxResults(maxresult);
	       
	       List<Operation> operations = query.getResultList();
		return operations;
	}

	@Override
	public List<OpEntree> getListOpEntreeByDirOrder(Direction dirc, int maxresult) {
		// TODO Auto-generated method stub
		TypedQuery<OpEntree> query = em.createQuery("select oe from OpEntree oe "
				+ " where oe.direction =:direct"
	       		+ " order by date desc "
	       		,OpEntree.class);
		query.setParameter("direct", dirc);
		query.setMaxResults(maxresult);
	       
	       List<OpEntree> operations = query.getResultList();
		return operations;
	}

	@Override
	public List<OpSortie> getListOpSortieByDirOrder(Direction dir, int maxresult) {
		// TODO Auto-generated method stub
		TypedQuery<OpSortie> query = em.createQuery("select os from OpSortie os "
				+ "where os.direction =:direct"
	       		+ " order by date desc "
	       		,OpSortie.class);
		query.setParameter("direct", dir);
		query.setMaxResults(maxresult);
	       
	       List<OpSortie> operations = query.getResultList();
		return operations;
	}

	@Override
	public List<Operation> getListOperationByDirOrder(Direction dir, int maxresult) {
		// TODO Auto-generated method stub
		TypedQuery<Operation> query = em.createQuery("select o from Operation o "
				+ " where o.direction =:direct"
	       		+ " order by date desc "
	       		,Operation.class);
		query.setParameter("direct", dir);
		query.setMaxResults(maxresult);
	       
	       List<Operation> operations = query.getResultList();
		return operations;
	}


	@Override
	public List<OpEntree> getListOpEntreeByOperator(Agent operator, int maxresult) {
		// TODO Auto-generated method stub
		TypedQuery<OpEntree> query = em.createQuery("select oe from OpEntree oe "
				+ " where oe.operateur =:operator"
	       		+ " order by date desc "
	       		,OpEntree.class);
		query.setParameter("operator", operator);
		query.setMaxResults(maxresult);
	       
	       List<OpEntree> operations = query.getResultList();
		return operations;
	}

	@Override
	public List<OpSortie> getListOpSortieByOperator(Agent operator, int maxresult) {
		// TODO Auto-generated method stub
		TypedQuery<OpSortie> query = em.createQuery("select os from OpSortie os "
				+ " where os.operateur =:operator"
	       		+ " order by date desc "
	       		,OpSortie.class);
		query.setParameter("operator", operator);
		query.setMaxResults(maxresult);
	       
	       List<OpSortie> operations = query.getResultList();
		return operations;
	
	}

	@Override
	public List<Operation> getListOperationByOperator(Agent operator, int maxresult) {
		// TODO Auto-generated method stub
		TypedQuery<Operation> query = em.createQuery("select o from Operation o "
				+ " where o.operateur =:operator"
	       		+ " order by date desc and by time desc"
	       		,Operation.class);
		query.setParameter("operator", operator);
		query.setMaxResults(maxresult);
	       
	       List<Operation> operations = query.getResultList();
		return operations;
	
	}

	@Override
	public List<OpAttribution> getListOpAttributionOrderByDate(int maxresult) {
		// TODO Auto-generated method stub
		TypedQuery<OpAttribution> query = em.createQuery("select opa from OpAttribution opa "
	       		+ " order by date desc "
	       		,OpAttribution.class);
		query.setMaxResults(maxresult);
	       
	       List<OpAttribution> operations = query.getResultList();
		return operations;
	}

	@Override
	public List<OpDettachement> getListOpDettachementOrderByDate(int maxresult) {
		// TODO Auto-generated method stub
		TypedQuery<OpDettachement> query = em.createQuery("select odet from OpDettachement odet "
	       		+ " order by date desc "
	       		,OpDettachement.class);
		query.setMaxResults(maxresult);
	       
	       List<OpDettachement> operations = query.getResultList();
		return operations;

	}

	@Override
	public List<OpAttribution> getListOpAttributionByDirOrder(Direction dir, int maxresult) {
		// TODO Auto-generated method stub
		TypedQuery<OpAttribution> query = em.createQuery("select oat from OpAttribution oat "
				+ " where oat.direction = :direct"
	       		+ " order by date desc "
	       		,OpAttribution.class);
		query.setParameter("direct", dir);
		query.setMaxResults(maxresult);
	       
	       List<OpAttribution> operations = query.getResultList();
		return operations;
	}

	@Override
	public List<OpDettachement> getListOpDettachementByDirOrder(Direction dir, int maxresult) {
		// TODO Auto-generated method stub
		TypedQuery<OpDettachement> query = em.createQuery("select odet from OpDettachement odet "
				+ " where odet.direction = :direct"
	       		+ " order by date desc "
	       		,OpDettachement.class);
		query.setParameter("direct", dir);
		query.setMaxResults(maxresult);
	       
	       List<OpDettachement> operations = query.getResultList();
		return operations;
	}

	@Override
	public List<OpAttribution> getListOpAttributionByOperator(Agent operator, int maxresult) {
		// TODO Auto-generated method stub
		TypedQuery<OpAttribution> query = em.createQuery("select oat from OpAttribution oat "
				+ " where oat.operateur = :operator"
	       		+ " order by date desc "
	       		,OpAttribution.class);
		query.setParameter("operator", operator);
		query.setMaxResults(maxresult);
	       
	       List<OpAttribution> operations = query.getResultList();
		return operations;
	}

	@Override
	public List<OpDettachement> getListOpDettachementByOperator(Agent operator, int maxresult) {
		// TODO Auto-generated method stub
		TypedQuery<OpDettachement> query = em.createQuery("select odet from OpDettachement odet "
				+ " where odet.operateur = :operator"
	       		+ " order by date desc "
	       		,OpDettachement.class);
		query.setParameter("operator", operator);
		query.setMaxResults(maxresult);
	       
	       List<OpDettachement> operations = query.getResultList();
		return operations;
	}

	@Override
	public List<Operation> getListOpEntreeAndSortieByDirectionByYearByDateAsc(Direction d,  Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("select oe, os from OpEntree oe, OpSortie os "
				+ "where os.date>=:startDate AND os.date<=:endDate AND oe.date>=:startDate AND oe.date<=:endDate "
				+ "and oe.direction =:direct and oe.direction =:direct"
				//+ "order by date asc"
				);
		query.setParameter("direct", d);
		query.setParameter("startDate", startDate, TemporalType.DATE);
		query.setParameter("endDate", endDate, TemporalType.DATE);
		List<Operation> operations = query.getResultList();
		return operations;
	
	}
	
	public Long countMaterielByTypeByDirect(TypeMateriel typemat, Direction dir) {
		// TODO Auto-generated method stub
		
		return matrepos.countByTypematerieladdAndDirec(typemat, dir);
	}

	@Override
	public List<OpEntree> getListOpEntreeByDirectionByYearByDateAsc(Direction d, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
				TypedQuery<OpEntree> query = em.createQuery("select oe from OpEntree oe "
						+ " where oe.date>=:startDate AND oe.date<=:endDate"
						+ " and oe.direction =:direct"
						+ " and oe.state=:etat"
			       		+ " order by oe.date desc "
			       		,OpEntree.class);
				query.setParameter("direct", d);
				query.setParameter("startDate", startDate, TemporalType.DATE);
				query.setParameter("endDate", endDate, TemporalType.DATE);
				query.setParameter("etat", EtatOperation.ACCEPTED);
				   
			    List<OpEntree> operations = query.getResultList();
				return operations;
	}

	@Override
	public List<OpSortie> getListOpSortieByDirectionByYearByDateAsc(Direction d, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		TypedQuery<OpSortie> query = em.createQuery("select os from OpSortie os "
				+ " where os.date>=:startDate AND os.date<=:endDate"
				+ " and os.direction =:direct"
				+ " and os.state=:etat"
	       		+ " order by os.date desc "
	       		,OpSortie.class);
		query.setParameter("direct", d);
		query.setParameter("startDate", startDate, TemporalType.DATE);
		query.setParameter("endDate", endDate, TemporalType.DATE);
		query.setParameter("etat", EtatOperation.ACCEPTED);   
	    List<OpSortie> operations = query.getResultList();
		return operations;
	}

	@Override
	public List<Operation> getListOperationByDirectionByYearByDateAsc(Direction d, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		System.out.println("List OpBYDBYYBDA Refused");
		TypedQuery<Operation> query = em.createQuery("select o from Operation o "
				+ " where o.date>=:startDate AND o.date<=:endDate"
				+ " and o.direction =:direct"
				+ " and o.state=:etat"
	       		+ " order by o.date desc "
	       		,Operation.class);
		query.setParameter("direct", d);
		query.setParameter("startDate", startDate, TemporalType.DATE);
		query.setParameter("endDate", endDate, TemporalType.DATE);
		query.setParameter("etat", EtatOperation.ACCEPTED);
		   
	    List<Operation> operations = query.getResultList();



	    System.out.println("******************************************************************************operations :"+operations.size());
		
	    for(Operation o : operations)
			{
					System.out.println(o.getClass());
			}

		return operations;
	}
	@Override
	public List<Operation> getListAllOperationByDirectionByYearByDateAsc(Direction d, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		System.out.println("List OpBYDBYYBDA Refused");
		TypedQuery<Operation> query = em.createQuery("select o from Operation o "
				+ " where o.date>=:startDate AND o.date<=:endDate"
				+ " and o.direction =:direct"
	       		+ " order by o.date desc "
	       		,Operation.class);
		query.setParameter("direct", d);
		query.setParameter("startDate", startDate, TemporalType.DATE);
		query.setParameter("endDate", endDate, TemporalType.DATE);
		   
	    List<Operation> operations = query.getResultList();



	    System.out.println("******************************************************************************operations :"+operations.size());
		
	    for(Operation o : operations)
			{
					System.out.println(o.getClass());
			}

		return operations;
	}

	@Override
	public List<OpEntreeArticle> getListOpEntreeArtByValideByDirection(EtatOperation etat, Direction direction,
			Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		TypedQuery<OpEntreeArticle> query = em.createQuery("select oeart from OpEntreeArticle oeart "
				+ " where oeart.date>=:startDate AND oeart.date<=:endDate"
				+ " and oeart.direction =:direct"
				+ " and oeart.state=:etat"
	       		+ " order by oeart.date desc "
	       		,OpEntreeArticle.class);
		query.setParameter("direct", direction);
		query.setParameter("startDate", startDate, TemporalType.DATE);
		query.setParameter("endDate", endDate, TemporalType.DATE);
		query.setParameter("etat", etat);   
	    List<OpEntreeArticle> operations = query.getResultList();
		return operations;
	}

	@Override
	public List<OpSortieArticle> getListOpSortieArtByValideByDirection(EtatOperation etat, Direction direction,
			Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		TypedQuery<OpSortieArticle> query = em.createQuery("select osart from OpSortieArticle osart "
				+ " where osart.date>=:startDate AND osart.date<=:endDate"
				+ " and osart.direction =:direct"
				+ " and osart.state=:etat"
	       		+ " order by osart.date desc "
	       		,OpSortieArticle.class);
		query.setParameter("direct", direction);
		query.setParameter("startDate", startDate, TemporalType.DATE);
		query.setParameter("endDate", endDate, TemporalType.DATE);
		query.setParameter("etat", etat);   
	    List<OpSortieArticle> operations = query.getResultList();
		return operations;
	}
	
	@Override
	public Long countOpEntreeByYearByDirection(Date date,Direction d) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		Query q= em.createQuery("SELECT COUNT(oe.id) FROM OpEntree oe"
				+ " where YEAR(oe.date)=:year "
				+ " and oe.direction =:direct"
				+ " and oe.state=:etat");
		q.setParameter("direct", d);
		q.setParameter("year", year);
		q.setParameter("etat", EtatOperation.ACCEPTED);
		
		Long r = (Long) q.getSingleResult();
		return r;
		   
	}
	
	@Override
	public Long countOpSortieByYearByDirection(Date date,Direction d) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		
		Query q= em.createQuery("SELECT COUNT(os.id) FROM OpSortie os"
				+ " where year(os.date)=:years "
				+ " and os.direction =:direct"
				+ " and os.state=:etat");
		q.setParameter("direct", d);
		q.setParameter("years", year);
		q.setParameter("etat", EtatOperation.ACCEPTED);
		
		Long r = (Long) q.getSingleResult();
		return r;
		   
	}
	@Override
	public List<OpEntreeArticle> getListOpEntreeArtByDirection(Direction direction,
			Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		String filtreDirection="";
		if(direction!=null) {
			filtreDirection = " and oeart.direction =:direct ";
		}
		TypedQuery<OpEntreeArticle> query = em.createQuery("select oeart from OpEntreeArticle oeart "
				+ "where "
				+ " oeart.date>=:startDate AND oeart.date<=:endDate"
				+ filtreDirection
	       		+ " order by oeart.date desc "
	       		,OpEntreeArticle.class);
		if(direction!=null) {
			query.setParameter("direct", direction);
		}
		query.setParameter("startDate", startDate, TemporalType.DATE);
		query.setParameter("endDate", endDate, TemporalType.DATE);  
	    List<OpEntreeArticle> operations = query.getResultList();
	   /* System.out.println("QUERRYY : select oeart from OpEntreeArticle oeart "
				+ "where oeart.date>="+startDate+" AND oeart.date<="+endDate
				+ " and oeart.direction =:"
	       		+ " order by oeart.date desc ");*/
		return operations;
	}

	@Override
	public List<OpSortieArticle> getListOpSortieArtByDirection(Direction direction,
			Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		TypedQuery<OpSortieArticle> query = em.createQuery("select osart from OpSortieArticle osart "
				+ "where osart.date>=:startDate AND osart.date<=:endDate"
				+ " and osart.direction =:direct"
	       		+ " order by osart.date desc "
	       		,OpSortieArticle.class);
		query.setParameter("direct", direction);
		query.setParameter("startDate", startDate, TemporalType.DATE);
		query.setParameter("endDate", endDate, TemporalType.DATE); 
	    List<OpSortieArticle> operations = query.getResultList();
		return operations;
	}
	

}
