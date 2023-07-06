package com.example.day6.web.dto;

import com.example.day6.domain.blog.Article;
import lombok.Getter;

@Getter
public class ArticleResponseDto {
    private String title;
    private String content;

    public ArticleResponseDto(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
