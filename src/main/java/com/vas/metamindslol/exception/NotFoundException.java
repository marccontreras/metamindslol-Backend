package com.vas.metamindslol.exception;

public class NotFoundException extends Exception{
    public NotFoundException(String message) {
        super("Not found " +message);
    }

    public NotFoundException() {
        super("Not found");
    }

    @Override
    public String toString() {
        return super.getMessage();
    }


}
