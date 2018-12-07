package com.company;

public class VectorOperationException extends BaseException {

    private static final String prefix =
            "Операция над данными векторами невозможна. Причина: ";

    public VectorOperationException(String message) {
        super(prefix + message);
    }
}

