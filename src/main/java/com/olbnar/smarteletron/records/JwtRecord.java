package com.olbnar.smarteletron.records;

import javax.validation.constraints.NotNull;

public record JwtRecord(@NotNull(message = "Token not be null") String token) {
}
