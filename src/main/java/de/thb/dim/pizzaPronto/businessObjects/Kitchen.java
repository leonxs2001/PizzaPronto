package de.thb.dim.pizzaPronto.businessObjects;


import de.thb.dim.pizzaPronto.valueObjects.EmployeeVO;

import java.util.ArrayList;
import java.util.Objects;

import de.thb.dim.pizzaPronto.valueObjects.ChefVO;
import de.thb.dim.pizzaPronto.valueObjects.OrderVO;
import de.thb.dim.pizzaPronto.valueObjects.StateOfOrderVO;
import de.thb.dim.pizzaPronto.businessObjects.exceptions.*;


public class Kitchen extends AbstractService {  

	private ArrayList<EmployeeVO> employees;

	public Kitchen() {
		setServiceName("Kitchen");
		employees = new ArrayList<EmployeeVO>();
		// Koch erstellen
		employees.add(new ChefVO("Koch5Sterne", "Bocuse", "Bruno"));
	}

	@Override
	public String startService(OrderVO order) throws NullPointerException, IllegalStateException,NotEnoughTimeException{
		String s = String.format("\nService of ChefVO %s: No order available.", employees.get(0).getPersonnelNo());
		
		Objects.requireNonNull(order, s);
		
			if (order.getState() != StateOfOrderVO.CONFIRMED) {
				
				s = String.format("\nService of ChefVO %s: No order for processing available.",
						employees.get(0).getPersonnelNo());
				throw new IllegalStateException(s);
			}
			else {
				if(this.calculateOrderTime(order)>this.getWorktime()) {
					throw new NotEnoughTimeException("We don't have enough chefs to produce this order in one day!.");
				}
				order.setState(StateOfOrderVO.READY);
				s  = String.format("\nService of ChefVO %s: Order is ready.", employees.get(0).getPersonnelNo());
			} 
			return s;
	}
	
	private float calculateOrderTime(OrderVO order) {
		float ret = 0;
		for (int i=0; i<order.getNumberOfDishes(); i++) {
			ret += order.getDish(i).getTimeToMake();
		}
		return ret;
	}
	
	private int getWorktime() {
		int ret = 0;
		for (int i=0; i<this.employees.size(); i++) {
			ret += this.employees.get(i).getWorkingHours();
		}
		return ret;
	}

	/**
	 * @return the employees
	 */
	public ArrayList<EmployeeVO> getEmployees() {
		return employees;
	}

	/**
	 * @param employees the employees to set
	 */
	public void setEmployees(ArrayList<EmployeeVO> employees) {
		this.employees = employees;;
	}
	
	public void addEmployee(EmployeeVO employee) {
		this.employees.add(employee);
	}
	
	public void removeEmployee(EmployeeVO employee) {
		this.employees.remove(employee);
	}
	
	public void removeEmployee(String persNr) {
		for(int i = 0; i<this.employees.size(); i++) {
			if(this.employees.get(i).getPersonnelNo() == persNr) {
				this.employees.remove(i);
			}
		}
	}
	
}
