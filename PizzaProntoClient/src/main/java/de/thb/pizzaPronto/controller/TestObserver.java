package de.thb.pizzaPronto.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import de.thb.pizzaPronto.businessObjects.DiscountManagement;
import de.thb.pizzaPronto.valueObjects.DiscountVO;
import de.thb.pizzaPronto.valueObjects.Gender;
import de.thb.pizzaPronto.valueObjects.exceptions.CustomerTooYoungException;
import de.thb.pizzaPronto.valueObjects.CustomerVO;

public class TestObserver {
	public static void main(String[] args) {
		DiscountManagement dm = new DiscountManagement();
		CustomerVO customer1, customer2,customer3;
		try {
			customer1 = new CustomerVO("Mampf", "Manfred", "Essensstrasse", 42,
					Gender.M, LocalDate.of(1990, 6, 28));
			customer2 = new CustomerVO("Genuss", "Gini", "Haribostraße", 32, Gender.F, LocalDate.of(1995, 8, 8));
			customer3 = new CustomerVO("Bocuse", "Bruno", "Havenstraße", 105, Gender.F, LocalDate.of(1975, 2, 1));
		
		
		DiscountVO happyHour = new DiscountVO("Happy Hour", "Each day", null, null, LocalTime.of(17, 0),
				LocalTime.of(18, 0));
		System.out.println(happyHour.getDiscountInfo());

		DiscountVO pizzaWeek = new DiscountVO("Pizza Week", "Any pizza 10% discount", LocalDate.of(2023, 6, 26),
				LocalDate.of(2023, 2, 7), null, null);
		System.out.println(pizzaWeek.getDiscountInfo());
		
		System.out.println("\nDiscount management");
		System.out.println("=====================");
		dm.attach(customer1);
		dm.attach(customer2);
		System.out.println();
		dm.add(happyHour);
		dm.add(pizzaWeek);
		System.out.println();
		dm.delete(happyHour);
		
		System.out.println(dm.toString());
		} catch (NullPointerException | CustomerTooYoungException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
