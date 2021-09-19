package com.test.vegstore.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HeaderDto {
    private String accessToken;

    public HeaderDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
