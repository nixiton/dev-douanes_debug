package com.douane.repository;


import org.springframework.data.repository.CrudRepository;

import com.douane.entite.Article;


import java.util.List;

public interface ArticleRepository extends CrudRepository<Article,Long>{
    public List<Article> findAll();
}