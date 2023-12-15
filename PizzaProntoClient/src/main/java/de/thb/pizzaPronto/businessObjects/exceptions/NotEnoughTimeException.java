package de.thb.pizzaPronto.businessObjects.exceptions;



public class NotEnoughTimeException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2258170392147132575L;

	/**
	 * Initialize Exception
	 */
	public NotEnoughTimeException(){
		super();
	}
	
	/**
	 * Initialize detailMessage
	 * 
	 * @param message 
	 */
	public NotEnoughTimeException(String message){
		super(message);
	}
}
