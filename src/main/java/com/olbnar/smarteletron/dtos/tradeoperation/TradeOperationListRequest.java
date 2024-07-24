package com.olbnar.smarteletron.dtos.tradeoperation;

import com.olbnar.smarteletron.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradeOperationListRequest {

    @NotBlank
    OperationType operationType;
}
