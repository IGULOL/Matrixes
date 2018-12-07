package com.company;

public class MatrixOperationException extends BaseException {

    private static final String prefix =
            "Операция над данными матрицами невозможна. Причина: ";

    public MatrixOperationException(String message) {
        super(prefix + message);
    }
}
