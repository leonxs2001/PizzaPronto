package de.thb.pizzaPronto.exception;

public class NoAuthenticatedUserException extends Exception{
    public NoAuthenticatedUserException() {
        super();
    }

    public NoAuthenticatedUserException(String message) {
        super(message);
    }
}
