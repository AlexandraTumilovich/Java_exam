package com.stormnet.figuresfx.exceptions;

public class InvalidSizeException extends Exception   {

    double size;

    public InvalidSizeException(String message, double size) {
        super(message);
        this.size = size;
    }

    public double getSize() {
        return size;
    }
}
