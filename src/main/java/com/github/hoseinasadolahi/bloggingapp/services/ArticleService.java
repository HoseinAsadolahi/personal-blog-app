package com.github.hoseinasadolahi.bloggingapp.services;

import com.github.hoseinasadolahi.bloggingapp.model.Article;
import com.github.hoseinasadolahi.bloggingapp.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Page<Article> findAllSorted(String sortBy, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (sortBy.equals("likes")) {
            return articleRepository.findAllSortedByLikes(pageable);
        }
        return articleRepository.findAllByOrderByPublishedDateDesc(pageable);
    }

    public Article getArticleById(long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Page<Article> getArticles(PageRequest of) {
        return articleRepository.findAllByOrderByPublishedDateDesc(of);
    }

    public void saveArticle(Article article) {
        articleRepository.save(article);
    }

    public void updateArticle(Article article) {
        articleRepository.save(article);
    }

    public void deleteArticle(long id) {
        articleRepository.deleteById(id);
    }
}
