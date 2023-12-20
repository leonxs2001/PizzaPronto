package de.thb.pizzaPronto.order.rest;

import de.thb.pizzaPronto.customer.rest.CustomerVO;
import de.thb.pizzaPronto.exception.NoCustomerException;
import de.thb.pizzaPronto.exception.NoOrderException;
import de.thb.pizzaPronto.menu.rest.DishVO;

import java.util.List;

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
