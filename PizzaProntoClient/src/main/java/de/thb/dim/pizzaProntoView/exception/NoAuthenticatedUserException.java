package de.thb.dim.pizzaProntoView.exception;

public class NoAuthenticatedUserException extends Exception{
    public NoAuthenticatedUserException() {
        super();
    }

    public NoAuthenticatedUserException(String message) {
        super(message);
    }
}
