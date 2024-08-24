package com.github.hoseinasadolahi.bloggingapp.repository;

import com.github.hoseinasadolahi.bloggingapp.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}