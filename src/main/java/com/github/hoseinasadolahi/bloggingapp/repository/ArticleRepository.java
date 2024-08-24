package com.github.hoseinasadolahi.bloggingapp.repository;

import com.github.hoseinasadolahi.bloggingapp.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("SELECT a FROM Article a ORDER BY a.publishedDate DESC")
    Page<Article> findAllByOrderByPublishedDateDesc(Pageable pageable);

    @Query("SELECT a FROM Article a ORDER BY SIZE(a.likes) DESC")
    Page<Article> findAllSortedByLikes(Pageable pageable);
}
