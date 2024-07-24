package com.olbnar.smarteletron.enums;

public enum OperationType {
    EXCHANGE("EXCHANGE"),
    SALE("SALE");

    private final String operationType;

    OperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getOperationType() {
        return operationType;
    }
}
