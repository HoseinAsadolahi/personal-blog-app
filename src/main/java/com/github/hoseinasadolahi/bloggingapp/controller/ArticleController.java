package com.github.hoseinasadolahi.bloggingapp.controller;

import com.github.hoseinasadolahi.bloggingapp.model.Article;
import com.github.hoseinasadolahi.bloggingapp.model.User;
import com.github.hoseinasadolahi.bloggingapp.services.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    public String getArticle(@PathVariable("id") Long id, Authentication auth, Model model) {
        Article article = articleService.getArticleById(id);
        model.addAttribute("article", article);
        if (auth == null) {
            model.addAttribute("liked", false);
        } else {
            User user = User.builder().email(auth.getName()).build();
            model.addAttribute("liked", article.getLikes().contains(user));
        }
        return "article/article";
    }

    @GetMapping("/dashboard")
    public String getDashboard(@RequestParam(defaultValue = "1") int page, Model model) {
        int pageSize = 10;
        Page<Article> articlePage = articleService.getArticles(PageRequest.of(page - 1, pageSize));
        model.addAttribute("articles", articlePage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", articlePage.getTotalPages());
        model.addAttribute("pageNumbers", IntStream.rangeClosed(1, articlePage.getTotalPages()).boxed().collect(Collectors.toList()));
        return "dashboard";
    }

    @GetMapping("/article/add")
    public String showAddArticleForm(Model model) {
        model.addAttribute("article", new Article());
        return "article/add-article";
    }

    @PostMapping("/article/add")
    public String addArticle(@Validated @ModelAttribute Article article, BindingResult result) {
        if (result.hasErrors()) {
            return "article/add-article";
        }
        articleService.saveArticle(article);
        return "redirect:/dashboard";
    }
}