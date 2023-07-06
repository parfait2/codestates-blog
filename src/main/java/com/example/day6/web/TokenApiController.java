package com.example.day6.web;

import com.example.day6.service.TokenService;
import com.example.day6.web.dto.CreateAccessTokenRequestDto;
import com.example.day6.web.dto.CreateAccessTokenResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TokenApiController {
    private final TokenService tokenService;

    // create
    @PostMapping("/api/latest/token")
    public ResponseEntity<CreateAccessTokenResponseDto> createNewAccessToken(@RequestBody CreateAccessTokenRequestDto createAccessTokenRequestDto) {
        String newAccessToken = tokenService.createNewAccessToken(createAccessTokenRequestDto.getRefreshToken());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateAccessTokenResponseDto(newAccessToken));
    }
}
