package com.douane.repository;

import java.util.List;

import com.douane.model.EtatOperation;
import org.springframework.data.repository.CrudRepository;

import com.douane.entite.Agent;
import com.douane.entite.Direction;
import com.douane.entite.Materiel;
import com.douane.entite.OpEntree;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

public interface OpEntreeRepository extends CrudRepository<OpEntree, Long>{
	public List<OpEntree> findAll();
	public List<OpEntree> findByOperateur(Agent operateur);
	public List<OpEntree> findByDirection(Direction direction);
	public List<OpEntree> findByDirectionOrderByDateDesc(Direction direction);
	public List<OpEntree> findByMat(Materiel m);
	public List<OpEntree> findByMatAndState(Materiel m, EtatOperation e);

}

