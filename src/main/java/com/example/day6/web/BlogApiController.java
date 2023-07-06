package com.example.day6.web;

import com.example.day6.domain.blog.Article;
import com.example.day6.service.BlogService;
import com.example.day6.web.dto.AddArticleRequestDto;
import com.example.day6.web.dto.ArticleResponseDto;
import com.example.day6.web.dto.UpdateArticleRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class BlogApiController {
    private final BlogService blogService;

    @PostMapping("/api/latest/articles") // api는 복수형으로 사용.
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequestDto requestDto) {
        // @RequestBody : servlet에 담겨오는 객체임을 명시한다.

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(blogService.save(requestDto));
    }

    // 전체 조회
    @GetMapping("/api/latest/articles")
    public ResponseEntity<List<ArticleResponseDto>> findAllArticles() {
//        blogService.findAll(); // 비효율적 -> stream 사용

        List<ArticleResponseDto> articles = blogService.findAll()
                .stream()
                .map(ArticleResponseDto::new)
                .collect(Collectors.toList()); // 직렬화

        return ResponseEntity.ok().body(articles);
    }

    // 단건 조회
    @GetMapping("api/latest/articles/{id}")
    public ResponseEntity<ArticleResponseDto> findArticle(@PathVariable Long id) {
        return ResponseEntity.ok()
                .body(new ArticleResponseDto(blogService.findById(id))); // 생성자를 통해서 ResponseDto를 바로 반환해줬기 때문.
    }

    @DeleteMapping("api/latest/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        blogService.delete(id);

        return ResponseEntity.ok().build();
    }

    // 수정
    @PutMapping("api/latest/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id,
                                                 @RequestBody UpdateArticleRequestDto requestDto) {
        // dto는 목적에 따라 필요할 때마다 만들어 준다.

        Article updatedArticle = blogService.update(id, requestDto);

        return ResponseEntity.ok().body(updatedArticle);
    }
}
