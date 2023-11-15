package de.thb.dim.pizzaPronto.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import de.thb.dim.pizzaPronto.businessObjects.DiscountManagement;
import de.thb.dim.pizzaPronto.valueObjects.DiscountVO;

public class TestDiscount {
	public static void main(String[] args) {
		DiscountManagement dm = new DiscountManagement();
		
		DiscountVO happyHour = new DiscountVO("Happy Hour", "Each day", null, null, LocalTime.of(17, 0),
				LocalTime.of(18, 0));
		System.out.println(happyHour.getDiscountInfo());

		DiscountVO pizzaWeek = new DiscountVO("Pizza Week", "Any pizza 10% discount", LocalDate.of(2023, 6, 26),
				LocalDate.of(2023, 7, 2), null, null);
		System.out.println(pizzaWeek.getDiscountInfo());
		
		System.out.println("\nDiscount management");
		dm.add(happyHour);
		dm.add(pizzaWeek);
		System.out.println(dm.toString());
		dm.delete(happyHour);
		System.out.println(dm.toString());
	}
}
