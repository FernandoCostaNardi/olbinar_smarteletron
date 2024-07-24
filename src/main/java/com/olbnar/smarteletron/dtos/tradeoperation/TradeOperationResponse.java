package com.olbnar.smarteletron.dtos.tradeoperation;

import com.olbnar.smarteletron.models.tradeoperation.TradeOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradeOperationResponse {

    private TradeOperation tradeOperation;

}
