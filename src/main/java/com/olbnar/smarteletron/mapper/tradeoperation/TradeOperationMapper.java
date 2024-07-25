package com.olbnar.smarteletron.mapper.tradeoperation;

import com.olbnar.smarteletron.dtos.tradeoperation.TradeOperationListResponse;
import com.olbnar.smarteletron.dtos.tradeoperation.TradeOperationRequest;
import com.olbnar.smarteletron.dtos.tradeoperation.TradeOperationResponse;
import com.olbnar.smarteletron.enums.OperationType;
import com.olbnar.smarteletron.models.tradeoperation.TradeOperation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TradeOperationMapper {

    public TradeOperation requestToEntity(TradeOperationRequest request) {
        TradeOperation tradeOperation = new TradeOperation();
        tradeOperation.setOperationType(OperationType.valueOf(request.getOperationType()));
        tradeOperation.setOperationCode(request.getOperationCode());
        tradeOperation.setOperationDescription(request.getOperationDescription());
        return tradeOperation;
    }

    public TradeOperationListResponse entityToListResponse(List<TradeOperation> tradeOperations) {
        return new TradeOperationListResponse(tradeOperations);
    }
}
