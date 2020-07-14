package com.stormnet.figuresfx.exceptions;

public class UnknownFigureException extends Exception  {
    int type;
    String message = "Unknown figure type! Type was " + type + ".";

    public UnknownFigureException(int type){
        this.type=type;
    }

    public int getType() {
        return type;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
