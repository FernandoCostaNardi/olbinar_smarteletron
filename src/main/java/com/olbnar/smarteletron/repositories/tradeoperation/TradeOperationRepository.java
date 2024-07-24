package com.olbnar.smarteletron.repositories.tradeoperation;

import com.olbnar.smarteletron.enums.OperationType;
import com.olbnar.smarteletron.models.tradeoperation.TradeOperation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TradeOperationRepository extends JpaRepository<TradeOperation, Long> {
    List<TradeOperation> findByOperationType(OperationType operationType);
    boolean existsByOperationCode(String operationCode);
}
