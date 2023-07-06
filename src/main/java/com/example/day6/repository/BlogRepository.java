package com.example.day6.repository;

import com.example.day6.domain.blog.Article;
import org.springframework.data.jpa.repository.JpaRepository;

// JPA를 통해서 Database 통신
// implement 필요 x. 웬만한 CRUD는 JPA로 가능.
public interface BlogRepository extends JpaRepository<Article, Long> {
}
