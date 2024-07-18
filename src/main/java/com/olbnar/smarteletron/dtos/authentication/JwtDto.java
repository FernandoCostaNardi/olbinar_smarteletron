package com.olbnar.smarteletron.dtos.authentication;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class JwtDto {

    @NonNull
    private String token;
}
