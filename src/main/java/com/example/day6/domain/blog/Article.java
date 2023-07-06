package com.example.day6.domain.blog;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter // lombok : 수많은 Entity를 annotaion 기반으로 관리 가능. lombok 관련 annotation은 위에, Spring 관련 annotation은 아래에 쓰는 게 관리하기 간편하다.
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 접근 제어 명령. 보안성을 위하여 access level을 protected로 준다.
//@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Article {
    // Error : Persistent entity 'Article' should have primary key -> primary key를 지정해줘야 한다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 auto increment
    @Column(name = "id", updatable = false) // column annotation은 지정해주는 편이 낫다. updatable : id는 수정 불가
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    // Log 남길 때 AOP를 많이 사용한다. 로그에서 가장 중요한 게 해당 이슈/내용/에러가 '언제' 발생했는지
    @CreatedDate // api adapting 기능
    @Column(name = "created_at") // Java 영역에서는 snake case 표기, DB에서는 camel case 표기
    private LocalDateTime createdAt; // 등록 시간

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // 수정 시간

    @Builder
    public Article(String title, String content) {
        // 등록 시간, 수정 시간은 자동으로 등록되게 함.
        this.title = title;
        this.content = content;
    }

    // update하는 기능을 명시한다.
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
