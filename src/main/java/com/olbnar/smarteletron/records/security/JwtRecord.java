package com.olbnar.smarteletron.records.security;

import javax.validation.constraints.NotNull;

public record JwtRecord(@NotNull(message = "Token not be null") String token) {
}
