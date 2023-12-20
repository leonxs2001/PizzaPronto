package de.thb.dim.pizzaProntoView.exception;

public class FailedRESTCallException extends Exception{
    public FailedRESTCallException() {
        super();
    }

    public FailedRESTCallException(String message) {
        super(message);
    }
}
