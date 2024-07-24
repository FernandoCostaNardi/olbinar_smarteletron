package com.olbnar.smarteletron.services.tradeoperation;

import com.olbnar.smarteletron.dtos.tradeoperation.TradeOperationListRequest;
import com.olbnar.smarteletron.dtos.tradeoperation.TradeOperationListResponse;
import com.olbnar.smarteletron.dtos.tradeoperation.TradeOperationRequest;
import com.olbnar.smarteletron.dtos.tradeoperation.TradeOperationResponse;

public interface TradeOperationService {
    TradeOperationListResponse getTradeOperationsByType(TradeOperationListRequest tradeOperationRequest);

    TradeOperationResponse saveTradeOperation(TradeOperationRequest tradeOperationRequest);

    void deleteTradeOperation(Long id);
}
