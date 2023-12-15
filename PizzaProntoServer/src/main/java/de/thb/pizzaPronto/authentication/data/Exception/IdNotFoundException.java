package de.thb.pizzaPronto.authentication.data.Exception;

public class IdNotFoundException extends Exception{
    public IdNotFoundException(){
        super();
    }

    public IdNotFoundException(String msg){
        super(msg);
    }
}
