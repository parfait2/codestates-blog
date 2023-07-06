package com.example.day6.web;

import com.example.day6.domain.blog.Article;
import com.example.day6.service.BlogService;
import com.example.day6.web.dto.ArticleListViewResponse;
import com.example.day6.web.dto.ArticleViewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleListViewResponse::new)
                .collect(Collectors.toList());
        model.addAttribute("articles", articles);

        return "articleList";
    }

    // 화면 단은 거의 GetMapping 사용. 데이터 통신 시 PostMapping
    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        System.out.println("\n" + "id = " + id);
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleListViewResponse(article));

        // 한 줄로 작성 가능하다.
//        model.addAttribute("article", new ArticleListViewResponse(blogService.findById(id)));

        return "article";
    }

    @GetMapping("/new-article") // 신규 글 작성과 특정 글 수정을 동시에 처리하게 해주는 메서드.
    public String newArticle(@RequestParam(required = false) Long id, Model model) { // required = false : 값이 null로 들어와도 처리해줌.
        if(id == null) {
            // 신규 등록
            model.addAttribute("article", new ArticleViewResponse());
        } else {
            // 수정 작업
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(article));

        }

        return "newArticle";
    }
}
