package com.douane.repository;


import org.springframework.data.repository.CrudRepository;

import com.douane.entite.Agent;
import com.douane.entite.Article;


import java.util.List;

public interface ArticleRepository extends CrudRepository<Article,Long>{
    public List<Article> findAll();

	public List<Article> findByValidation(boolean validation);

	public List<Article> findByValidationByBeneficiaire(boolean b, Agent Beneficiaire);
}