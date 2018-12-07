package com.company;

public class VectorInputException extends BaseException {

    private static final String prefix =
            "Неккоректное представление вектора. Причина: ";

    public VectorInputException(String message) {
        super(prefix + message);
    }
}
