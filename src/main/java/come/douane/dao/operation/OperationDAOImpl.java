package come.douane.dao.operation;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import com.douane.entite.Agent;
import com.douane.entite.Direction;
import com.douane.entite.Materiel;
import com.douane.entite.MaterielEx;
import com.douane.entite.OpAttribution;
import com.douane.entite.OpDettachement;
import com.douane.entite.OpEntree;
import com.douane.entite.OpSortie;
import com.douane.entite.Operation;

public class OperationDAOImpl implements IOperationDAO{
	
	@PersistenceContext
	private EntityManager em;
	
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
		System.out.println("*************************************AFAKA***************1");
		if(m.getDetenteur()!=null) {
			throw new Exception("Efa attribuer olona io fa mila detachena aloha");
		}
		//m.setCodification("codified"+new Date());
		m.generateCode();
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
				m.generateCode();
				System.out.println(m.getCode()+" : code generated ok");
				//m.setDetenteur(attr.getDetenteur());
				//matrepos.save(m);
				//em.persist(m);
				//em.merge(m);
				TypedQuery<Agent> query = em.createQuery("select a from Agent a "
			       		+ "where a.im=:immatricule"
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
	       		+ "where o.date>= :startDate AND o.date<= :endDate"
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
	       		+ "where oe.date>=:startDate AND oe.date<=:endDate AND "
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
				+ "where oe.direction =:direct"
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
		TypedQuery<Operation> query = em.createQuery("select oe, os from OpEntree oe, OpSortie os "
				+ "where os.date>=:startDate AND os.date<=:endDate AND oe.date>=:startDate AND oe.date<=:endDate "
				+ "and oe.direction = : direct and oe.direction = : direct"
				+ "order by date asc"
				,Operation.class);
		query.setParameter("direct", d);
		List<Operation> operations = query.getResultList();
		return operations;
	
	}
	

}
