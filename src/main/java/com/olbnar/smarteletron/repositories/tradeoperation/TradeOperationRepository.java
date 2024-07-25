package com.olbnar.smarteletron.repositories.tradeoperation;

import com.olbnar.smarteletron.enums.OperationType;
import com.olbnar.smarteletron.models.tradeoperation.TradeOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeOperationRepository extends JpaRepository<TradeOperation, Long> {

    Page<TradeOperation> findByOperationType(OperationType operationType, Pageable pageable);
    boolean existsByOperationCode(String operationCode);
}
