package com.example.day6.web.dto;

import com.example.day6.domain.blog.Article;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter // Setter는 필요 시에만 사용
public class ArticleListViewResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public ArticleListViewResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
        this.modifiedAt = article.getUpdatedAt();
    }
}
