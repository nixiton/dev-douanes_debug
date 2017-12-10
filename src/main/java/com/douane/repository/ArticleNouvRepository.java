package com.douane.repository;

import com.douane.entite.Article;
import com.douane.entite.ArticleNouv;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by hasina on 12/11/17.
 */
public interface ArticleNouvRepository extends CrudRepository<ArticleNouv,Long>  {
    public List<ArticleNouv> findAll();
}
