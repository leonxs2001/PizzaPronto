package de.thb.dim.pizzaProntoGUI.Exception;

public class FailedRESTCallException extends Exception{
    public FailedRESTCallException() {
        super();
    }

    public FailedRESTCallException(String message) {
        super(message);
    }
}
