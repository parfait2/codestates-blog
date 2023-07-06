package com.example.day6.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component // 의존성을 Bean으로 주입
@ConfigurationProperties("jwt")
public class JwtProperties {
    private String issuer;
    private String secret;
}
