package com.olbnar.smarteletron.services.tradeoperation;

import com.olbnar.smarteletron.dtos.tradeoperation.TradeOperationListRequest;
import com.olbnar.smarteletron.dtos.tradeoperation.TradeOperationListResponse;
import com.olbnar.smarteletron.dtos.tradeoperation.TradeOperationRequest;
import com.olbnar.smarteletron.dtos.tradeoperation.TradeOperationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface TradeOperationService {
    Page<TradeOperationListResponse> getTradeOperationsByType(TradeOperationListRequest tradeOperationRequest, PageRequest pageRequest);

    TradeOperationResponse saveTradeOperation(TradeOperationRequest tradeOperationRequest);

    void deleteTradeOperation(Long id);
}
