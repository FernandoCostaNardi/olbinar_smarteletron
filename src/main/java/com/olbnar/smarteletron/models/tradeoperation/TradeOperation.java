package com.olbnar.smarteletron.models.tradeoperation;

import com.olbnar.smarteletron.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trade_operation")
public class TradeOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name="operation_type", nullable = false)
    private OperationType operationType;

    @Column(name = "operation_code", nullable = false, unique = true, length = 20)
    private String operationCode;

    @Column(name = "operation_description", nullable = false, unique = true)
    private String operationDescription;
}
