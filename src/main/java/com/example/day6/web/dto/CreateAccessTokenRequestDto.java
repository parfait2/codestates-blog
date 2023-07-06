package com.example.day6.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccessTokenRequestDto {
    private String refreshToken;
}
