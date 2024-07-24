package com.olbnar.smarteletron.dtos.tradeoperation;

import com.olbnar.smarteletron.models.tradeoperation.TradeOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradeOperationListResponse {

    List<TradeOperation> tradeOperations;
}
