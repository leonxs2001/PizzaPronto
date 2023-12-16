package de.thb.dim.pizzaProntoGUI.Exception;

public class NoAuthenticatedUserException extends Exception{
    public NoAuthenticatedUserException() {
        super();
    }

    public NoAuthenticatedUserException(String message) {
        super(message);
    }
}
