package de.thb.pizzaPronto.authentication.model.Exception;

public class WrongUserCredentialsException extends Exception{
    public WrongUserCredentialsException(){
        super();
    }

    public WrongUserCredentialsException(String msg){
        super(msg);
    }
}
