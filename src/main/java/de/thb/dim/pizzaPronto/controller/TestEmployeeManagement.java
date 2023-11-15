package de.thb.dim.pizzaPronto.controller;

import de.thb.dim.pizzaPronto.businessObjects.*;
import de.thb.dim.pizzaPronto.valueObjects.*;

public class TestEmployeeManagement {
	public static void main(String[] args) {
		Kitchen kitchen = new Kitchen();
		Delivery delivery = new Delivery();
		EmployeeManagement em = new EmployeeManagement();
		
		try {
			DeliveryManVO deliveryman = (DeliveryManVO) em.hire("Fahrerino", "Fahrer", "Test",delivery);
			ChefVO chef = (ChefVO) em.hire("Cheferino", "Chef", "Test",kitchen);
			System.out.println(em);
			em.fire(chef);
			System.out.println(em);
			em.fire(deliveryman);
			System.out.println(em);
			chef = (ChefVO) em.hire("Cheferino", "Chef", "Test",kitchen);
			System.out.println(em);
			chef = (ChefVO) em.hire("Cheferino", "Chef", "Test",kitchen);
			System.out.println(em);
			chef = (ChefVO) em.hire("Cheferino", "Chef", "Test",kitchen);
			System.out.println(em);
		}catch(IllegalStateException e){
			System.out.println(e);
		}
	}
}
