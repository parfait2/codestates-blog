package com.example.day6.service;

import com.example.day6.config.jwt.TokenProvider;
import com.example.day6.domain.token.RefreshToken;
import com.example.day6.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {
    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService; // userService와 UserDetailService는 다르다는 것에 유념한다.

    public String createNewAccessToken(String refreshToken) {
        // 토큰 유효성 검증에 실패하면 예외 발생
        if(!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("unexpected token");
        }

        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        User user = userService.findById(userId);

        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }
}
