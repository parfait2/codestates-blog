package com.example.day6.service;

import com.example.day6.domain.blog.Article;
import com.example.day6.repository.BlogRepository;
import com.example.day6.web.dto.AddArticleRequestDto;
import com.example.day6.web.dto.UpdateArticleRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor // final이 붙은 field를 자동으로 초기화 해준다.
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    // save
    public Article save(AddArticleRequestDto requestDto) {
        return blogRepository.save(requestDto.toEntity()); // dto 형태로 저장할 수 없다.
    }

    // all read - 추후 paging 처리
//    @Transactional(readOnly = ) // 조회 기능에서 Transactional 사용 시 속도를 빠르게 하기 위함.
    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    // read only 1
    public Article findById(Long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("article not exist! : " + id));
    }

    // update
    // 영속성 컨텍스트의 개념을 알고 있어야 한다.
    @Transactional // 조회 후 수정 -> 작업의 순서가 있다. // business logic에서 처리의 순서를 설정하고 하는 일을 명세한다.
    public Article update(Long id, UpdateArticleRequestDto requestDto) {
        // step 1. 기존 게시글을 가져온다.
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("article not exist! : " + id));

        // step 2. 원하는 제목, 내용으로 수정
        // 서비스 단에서 조회된 데이터를 업데이트하는 코드
        // service layer에서 하는 역할 : 작업의 흐름과 하는 일을 정의하는 데서의 의의가 있다.
        article.update(requestDto.getTitle(), requestDto.getContent());

        // 업데이트될 객체는 만들었는데 이걸 리파지토리로 넘기는 무언가가 없음.
        // -> 특정 시점에 자동으로 반영되기 때문이다. (persistence context의 특징 - 3. 변경 감지)

        return article;
    }

    // delete
    public void delete(Long id) {
        blogRepository.deleteById(id);
    }
}
