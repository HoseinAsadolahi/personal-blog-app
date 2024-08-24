package com.github.hoseinasadolahi.bloggingapp.controller;

import com.github.hoseinasadolahi.bloggingapp.model.Article;
import com.github.hoseinasadolahi.bloggingapp.services.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping({"", "/"})
    public String index() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String homePage(Model model, @RequestParam(defaultValue = "publishedDate") String sortBy,
                           @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "10") int size) {
        Page<Article> articlePage = articleService.findAllSorted(sortBy, page, size);
        model.addAttribute("articlePage", articlePage);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        return "index";
    }

    @GetMapping("/article/{id}")
    public String getArticle(@PathVariable("id") Long id, Model model) {
        Article article = articleService.getArticleById(id);
        model.addAttribute("article", article);
        return "article"; // The name of your Thymeleaf template
    }
}