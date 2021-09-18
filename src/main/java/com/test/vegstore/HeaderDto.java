package com.test.vegstore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class HeaderDto {
    private String accessToken;

    public HeaderDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
