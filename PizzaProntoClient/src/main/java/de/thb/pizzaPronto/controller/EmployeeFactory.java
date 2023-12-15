package de.thb.pizzaPronto.controller;

import de.thb.pizzaPronto.businessObjects.Delivery;
import de.thb.pizzaPronto.businessObjects.IService;
import de.thb.pizzaPronto.businessObjects.Kitchen;
import de.thb.pizzaPronto.valueObjects.ChefVO;
import de.thb.pizzaPronto.valueObjects.DeliveryManVO;
import de.thb.pizzaPronto.valueObjects.EmployeeVO;

public class EmployeeFactory implements IEmployeeFactory {

	@Override
	public EmployeeVO create(String pNo, String lastName, String firstName, IService service) throws IllegalArgumentException{
		EmployeeVO newEmployee = null;

		if (service instanceof Kitchen) {
			newEmployee = new ChefVO(pNo, lastName, firstName, service);
		} else {
			if (service instanceof Delivery) {

				newEmployee = new DeliveryManVO(pNo, lastName, firstName, service);
			} else {
				throw new IllegalArgumentException("Wrong service.");
			}
		}
		return newEmployee;
	}

}
