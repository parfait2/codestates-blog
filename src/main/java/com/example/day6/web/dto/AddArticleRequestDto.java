package com.example.day6.web.dto;

import com.example.day6.domain.blog.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter // @Setter는 안 쓰는 이유. 값을 쓸 수 있다는 건 데이터 변조가 가능하다는 뜻이다. 필요하지 않은 곳에 @Getter와 @Setter를 모두 쓰는 건 지양해야 한다.
public class AddArticleRequestDto {
    private String title;
    private String content;

    // dto를 Entity로 converting
    // data가 의도하지 않게 변조되는 것을 방지한다.
    public Article toEntity() {
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
