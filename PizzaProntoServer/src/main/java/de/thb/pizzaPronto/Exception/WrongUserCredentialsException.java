package de.thb.pizzaPronto.Exception;

public class WrongUserCredentialsException extends Exception{
    public WrongUserCredentialsException(){
        super();
    }

    public WrongUserCredentialsException(String msg){
        super(msg);
    }
}
