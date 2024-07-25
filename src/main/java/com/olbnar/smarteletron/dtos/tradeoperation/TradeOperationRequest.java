package com.olbnar.smarteletron.dtos.tradeoperation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradeOperationRequest {

    @NotBlank
    private String operationType;

    @NotBlank
    private String operationCode;

    @NotBlank
    private String operationDescription;
}
