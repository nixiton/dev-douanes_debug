package com.douane.metier.user;

import java.util.Date;
import java.util.List;

import com.douane.entite.*;
import com.douane.model.EtatOperation;

public interface IUserMetier {
	
	public Useri addUser(Useri u);

	public void remUser(Useri u);

	public Agent addAgent(Agent a);
	public Agent changeAgentPass(Agent a, String codedPassword);
	public void remAgent (Agent a);



	public Agent addAgentUser(Agent a, Useri u);

	public Agent findAgentByIm (Long im_agent);

	//les requetes
	public OpEntree reqEntrerMateriel(List<Materiel> m, Agent dc, String facturePath, String refFacture);
	public OpSortie reqSortirMateriel(Materiel m, MotifSortie motif, Direction d, Service s, Bureau b, Agent op)throws Exception;

	public OpAttribution reqAttribution(Materiel m, Agent oper, Agent detenteur) throws Exception;

	public OpDettachement reqDettachement(Materiel mat1, Agent agent2, Agent agent1,MotifSortie m)throws Exception;
	//les validations

	public Materiel entrerMateriel(OpEntree op);
	public Materiel sortirMateriel(OpSortie sortie) throws Exception;


	public OpEntree reqMatAModifier(OpEntree entree, String motif)throws Exception;
	public OpSortie reqSortirAModifier(OpSortie sort, String motif);
	public OpEntree reqMatRefuser(OpEntree entree, String string)throws Exception;
	public OpSortie reqSortirRefuser(OpSortie sortie, String string);

	public Agent detacherMateriel(OpDettachement det) throws Exception;
	public OpDettachement reqDetRefuser(OpDettachement det, String string);


	//public Materiel attriuberMateriel (Long idMat, Long im);
	//public Materiel attriuberMateriel (Materiel m, Agent d);
	public Materiel attriuberMateriel(OpAttribution attr) throws Exception;
	public Materiel attribuerMaterielEx (MaterielEx matex, Agent detenteur)throws Exception;
	public OpAttribution reqAttrAModifier(OpAttribution attr, String motif)throws Exception;
	public OpAttribution reqAttrRefuser(OpAttribution attr, String motif)throws Exception;


	//public Materiel dettacherMateriel (Materiel m);
	public void delMat(Materiel m);


	/*
	 * GETTERS
	 *
	 */

	public List<ArticleEx> getListArticleEx();


	public Materiel getMatById(Long idmat);
	public List<Materiel> getListMatByDet(Agent detenteur);
	public List<Materiel> getListMatByNom(Nomenclature nomenclature);
	public List<Materiel> getListMatByDirection(Direction direction);
	public List<Materiel> getListMatByService(Service service);
	public List<Materiel> getListMatByBureau(Bureau bureau);
	public List<Materiel> getListMat();
	public List<MaterielEx> getListMatEx();
	public List<MaterielNouv> getListMatNouv();
	public List<Materiel> getMatByValidation(boolean validation);
	public List<Materiel> getMatByDetenteurAndValidation(Agent detenteur,boolean validation);
	public List<Materiel> getMatByDetenteurAndDirection(Agent detenteur,Direction direction);
	public List<MaterielNouv> getListMaterielNouvValide();

	public List<Operation> getListOp();
	//public List<Operation> getListOpWithMat();
	public List<OpEntree> getListOpEntree();
	public List<OpSortie> getListOpSortie();


	public List<OpAttribution> getListOpAttribution();
	public List<OpDettachement> getListOpDettachement();

	public List<Operation> getListOpByOperator(Agent operator);
	public List<OpEntree> getListOpEntreeByOperator(Agent operator);
	public List<OpSortie> getListOpSortieByOperator(Agent operator);

	public List<Operation> getListOpByDirection(Direction direction);
	public List<OpEntree> getListOpEntreeByDirection(Direction direction);
	public List<OpSortie> getListOpSortieByDirection(Direction direction);

	public List<OpAttribution> getListOpAttrByOperator(Agent operator);
	public List<OpDettachement> getListOpDettByOperatort(Agent operator);
	public List<OpAttribution> getListOpAttrByDirection(Direction direction);
	public List<OpDettachement> getListOpDettByDirection(Direction direction);


	public List<Operation> getListOpBetween(Date startDate, Date endDate);
	public List<OpEntree> getListOpEntreeByMat(Materiel m);
	public List<OpSortie> getListOpSortieByMat(Materiel m);

	public List<OpAttribution> getListOpAttrByMat(Materiel m);
	public List<OpDettachement> getListOpDettByMat(Materiel m);

	public List<OpEntree> getListOpEntreeByMatAndByState(Materiel m, EtatOperation e);
	public List<OpSortie> getListOpSortieByMatAndByState(Materiel m, EtatOperation e);
	public List<OpAttribution> getListOpAttrByMatAndByState(Materiel m, EtatOperation e);
	public List<OpDettachement> getListOpDettByMatAndByState(Materiel m, EtatOperation e);

	public List<OpEntree> getListOpEntreeByMatBDate(Materiel m, Date startDate, Date endDate);
	public List<OpSortie> getListOpSortieByMatBDate(Materiel m, Date startDate, Date endDate);
	public List<Operation> getListOpEntreeAndSortieByDirectionByYearByDateAsc(Direction d,  Date startDate, Date endDate);
	
	public List<OpEntree> getListOpEntreeByDirectionByYearByDateAsc(Direction d,  Date startDate, Date endDate);
	public List<OpSortie> getListOpSortieByDirectionByYearByDateAsc(Direction d,  Date startDate, Date endDate);
	public List<Operation> getListOperationByDirectionByYearByDateAsc(Direction d,  Date startDate, Date endDate);


	public List<Useri> listUser();
	//temporary
	public List<Agent> findAllAgents();
	public List<Agent> findAgentByNom(String name);
	public List<Agent> listAgentByDirection(Direction direction);
	//okay
	public OpEntree getOperationEntreeById(Long idopentree);
	/**
	 * Affichage
	 */
	public void seeMat(Materiel m);
	public void seeAgent(Agent a);


	//FOR CA
	public CodeArticle addCodeArticle(CodeArticle codeArticle);
	public void removeCodeArticle(CodeArticle codearticle);
	public List<CodeArticle> listCodeArticle();
	public List<CodeArticle> listCodeArticleByTypeObj(TypeObjet typeObj);

	public OpEntreeArticle reqEntrerArticle(Article article, Agent dc);
	public OpSortieArticle reqSortirArticle(Article article, Agent op, Agent destinataire)throws Exception;


	public OpEntreeArticle reqArtAModifier(OpEntreeArticle entreeArt, String motif)throws Exception;
	public OpSortieArticle reqSortirArtAModifier(OpSortieArticle sortArt, String motif) throws Exception;
	public OpEntreeArticle reqArtRefuser(OpEntreeArticle entreeArt, String motif)throws Exception;
	public OpSortieArticle reqSortirRefuser(OpSortieArticle sortArt, String motif)throws Exception;



	public Article entrerArticle(OpEntreeArticle opentreeart);
	public Article sortirArticle(OpSortieArticle sortieart) throws Exception;

	public List<ArticleNouv> getListAllArticleNouv();
	public List<Article> getListAllArticle();
	public List<Article> getListArticleValideByDirection(Direction d);
	public List<Article> getListArticleNonDetenuValideByDirection(Direction d);
	public List<Article> getListArticleByDetenteurByValida(boolean valide, Agent detenteur);
	public List<Article> getListArticleByValidationByDirection(boolean valide, Direction d);
	
	
	public List<OpEntreeArticle> getListOpEntreeArtByValideByDirection(EtatOperation etat, Direction direction, Date startDate, Date endDate);
	public List<OpEntreeArticle> getListOpEntreeArtByDirection(Direction direction, Date startDate, Date endDate);
	
	public List<OpSortieArticle> getListOpSortieArtByValideByDirection(EtatOperation etat,Direction direction, Date startDate, Date endDate);
	public List<OpSortieArticle> getListOpSortieArtByDirection(Direction direction, Date startDate, Date endDate);

	

	public ArticleNouv addArticleNouv(CodeArticle cde, Agent ben, Agent depo, Fournisseur fourn, Float prix, Long nombre, Marque marqueArt,String caraArt);
	public ArticleEx addArticleEx(CodeArticle cde, Agent ben, Agent depo, Float prix, Long nombre, Marque marqueArt,String caraArt);
	
	
	public OpSortie reqSortirMateriel(Materiel m, MotifSortie motif, Direction d, Agent oper)throws Exception;;
	public OpSortie reqSortirMateriel(Materiel m, MotifSortie motif, Agent oper)throws Exception;;
	
	public List<ArticleNouv> getListArtNouvValideByDirection(Direction d);
	public List<ArticleNouv> getListArtNouvByValidationByDirection(boolean val,Direction d);
	public List<ArticleEx> getListArtExByDirction(Direction d);
	
	public Article getArticleById(Long id);

	List<Materiel> getMatByValidationAndDetenteurAndDirection(boolean val, Agent detenteur, Direction direction);

	List<Operation> getListAllOperationByDirectionByYearByDateAsc(Direction d, Date startDate, Date endDate);
	
}
