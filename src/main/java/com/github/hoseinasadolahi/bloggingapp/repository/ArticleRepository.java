package com.github.hoseinasadolahi.bloggingapp.repository;

import com.github.hoseinasadolahi.bloggingapp.model.Article;
import org.hibernate.annotations.NamedNativeQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;

public interface ArticleRepository extends JpaRepository<Article, Long>, CrudRepository<Article, Long> {

    @Query("SELECT a FROM Article a ORDER BY a.publishedDate DESC, SIZE(a.likes) DESC")
    Page<Article> findAllByOrderByPublishedDateDesc(Pageable pageable);

    @Query("SELECT a FROM Article a ORDER BY SIZE(a.likes) DESC, a.publishedDate DESC")
    Page<Article> findAllSortedByLikes(Pageable pageable);
}
