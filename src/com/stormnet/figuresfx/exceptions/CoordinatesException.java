package com.stormnet.figuresfx.exceptions;

public class CoordinatesException extends Exception {
    String message = "Invalid coordinates!";

    @Override
    public String getMessage() {
        return message;
    }
}
