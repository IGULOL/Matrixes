package com.company;

public class MatrixInputException extends BaseException {

    private static final String prefix =
            "Неккоректное представление матрицы. Причина: ";

    public MatrixInputException(String message) {
        super(prefix + message);
    }
}