package com.douane.repository;

import java.util.List;

import com.douane.entite.*;
import com.douane.model.EtatOperation;
import org.springframework.data.repository.CrudRepository;

public interface OpSortieRepository extends CrudRepository<OpSortie, Long>{
	public List<OpSortie> findAll();
	public List<OpSortie> findByOperateur(Agent operateur);
	public List<OpSortie> findByDirection(Direction direction);
	public List<OpSortie> findByMat(Materiel m);
	public List<OpSortie> findByMatAndByState(Materiel m, EtatOperation e);

}

