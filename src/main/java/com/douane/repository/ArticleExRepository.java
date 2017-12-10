package com.douane.repository;

import org.springframework.data.repository.CrudRepository;

import com.douane.entite.ArticleEx;
import com.douane.entite.CodeArticle;

public interface ArticleExRepository extends CrudRepository<ArticleEx,Long>{

}