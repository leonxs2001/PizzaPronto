package de.thb.pizzaPronto.controller;

import de.thb.pizzaPronto.valueObjects.CustomerVO;
import de.thb.pizzaPronto.valueObjects.DishVO;
import de.thb.pizzaPronto.valueObjects.OrderVO;

import java.util.List;

import de.thb.pizzaPronto.businessObjects.exceptions.NoCustomerException;
import de.thb.pizzaPronto.businessObjects.exceptions.NoOrderException;

public interface IOrdering {
	
	public OrderVO startNewOrder(CustomerVO customer) throws NullPointerException;
	
	public void addDish(DishVO dish) throws NoOrderException, IllegalStateException ;
	
	public void deleteDish(DishVO dish) throws NoOrderException, IllegalStateException ;
	
	public float calculateTotalPrice() throws NoOrderException ;
	
	public void confirmOrder() throws NoOrderException, NoCustomerException, IllegalStateException ;
	
	public List<DishVO> sortShoppingBasket() throws NoOrderException;
	
	public List<DishVO> sortShoppingBasketByNumber() throws NoOrderException;
	
	public List<DishVO> sortShoppingBasketByPrice() throws NoOrderException ;

}
