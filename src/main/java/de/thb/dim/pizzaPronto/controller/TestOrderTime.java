package de.thb.dim.pizzaPronto.controller;

import java.time.LocalDate;

import de.thb.dim.pizzaPronto.businessObjects.*;
import de.thb.dim.pizzaPronto.businessObjects.exceptions.NoCustomerException;
import de.thb.dim.pizzaPronto.businessObjects.exceptions.NotEnoughTimeException;
import de.thb.dim.pizzaPronto.valueObjects.*;
import de.thb.dim.pizzaPronto.valueObjects.exceptions.CustomerTooYoungException;

public class TestOrderTime {
	public static void main(String[] args) {
		Kitchen kitchen = new Kitchen();
		Delivery delivery = new Delivery();
		Ordering ordering = new Ordering();
		EmployeeManagement hr = new EmployeeManagement();
		
		try {
			System.out.println("Two dishes are ordered.");
			System.out.println("=======================");
			CustomerVO customer = new CustomerVO("Customer", "Test", LocalDate.of(1997, 12, 2));
			OrderVO order = ordering.startNewOrder(customer);
			for(int i= 0; i< 2; i++) {
				order.addDish(new PizzaVO());
			}
			order.setState(StateOfOrderVO.CONFIRMED);
			kitchen.startService(order);
			delivery.startService(order);
			System.out.println("Services ok.");
		}catch(CustomerTooYoungException| NotEnoughTimeException | IllegalStateException | NoCustomerException e){
			System.out.println(e);
		}
		try {
			System.out.println("\n14 dishes are ordered.");
			System.out.println("=======================");
			CustomerVO customer = new CustomerVO("Customer", "Test", LocalDate.of(1997, 12, 2));
			OrderVO order = ordering.startNewOrder(customer);
			for(int i= 0; i< 14; i++) {
				order.addDish(new PizzaVO());
			}
			order.setState(StateOfOrderVO.CONFIRMED);
			kitchen.startService(order);
			delivery.startService(order);
			System.out.println("Services ok.");
		}catch(CustomerTooYoungException| NotEnoughTimeException | IllegalStateException | NoCustomerException e){
			System.out.println(e);
		}
		
		try {
			System.out.println("\n14 dishes are ordered and a chef ist hired.");
			System.out.println("=============================================");
			ChefVO chef = (ChefVO) hr.hire("Cheferino", "Chef", "Test",kitchen);
			CustomerVO customer = new CustomerVO("Customer", "Test", LocalDate.of(1997, 12, 2));
			OrderVO order = ordering.startNewOrder(customer);
			for(int i= 0; i< 14; i++) {
				order.addDish(new PizzaVO());
			}
			order.setState(StateOfOrderVO.CONFIRMED);
			kitchen.startService(order);
			delivery.startService(order);
			System.out.println("Services ok.");
		}catch(CustomerTooYoungException| NotEnoughTimeException | IllegalStateException | NoCustomerException e){
			System.out.println(e);
		}
	}
}
