package com.stormnet.figuresfx.exceptions;

public class FigureException extends Exception {

    int type;

    public FigureException(String message, int type){
        super(message);
        this.type = type;
    }
}
