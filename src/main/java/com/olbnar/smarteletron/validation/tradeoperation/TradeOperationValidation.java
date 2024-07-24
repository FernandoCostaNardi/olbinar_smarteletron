package com.olbnar.smarteletron.validation.tradeoperation;

import com.olbnar.smarteletron.enums.OperationType;
import com.olbnar.smarteletron.exception.tradeoperation.OperationCodeException;
import com.olbnar.smarteletron.exception.tradeoperation.OperationTypeException;
import com.olbnar.smarteletron.repositories.tradeoperation.TradeOperationRepository;
import org.springframework.stereotype.Component;

@Component
public class TradeOperationValidation {

    private final TradeOperationRepository tradeOperationRepository;

    public TradeOperationValidation(TradeOperationRepository tradeOperationRepository) {
        this.tradeOperationRepository = tradeOperationRepository;
    }

    public void validateOperationCodeUniqueness(String operationCode) {
        if (tradeOperationRepository.existsByOperationCode(operationCode)) {
            throw new OperationCodeException("Código de operação já existe: " + operationCode);
        }
    }
    public void isOperationTypeValid(String operationType) {
        if (!isValidOperationType(operationType)) {
            throw new OperationTypeException("Tipo de operação inválido: " + operationType);
        }
    }
    private boolean isValidOperationType(String operationType) {
        try {
            OperationType.valueOf(operationType);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
