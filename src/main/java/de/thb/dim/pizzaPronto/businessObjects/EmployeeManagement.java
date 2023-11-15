package de.thb.dim.pizzaPronto.businessObjects;

import java.util.ArrayList;

import de.thb.dim.pizzaPronto.valueObjects.EmployeeVO;
import de.thb.dim.pizzaPronto.controller.EmployeeFactory;


public class EmployeeManagement{

	private final int  CAPACITY = 2;
	EmployeesAvailabilityState state;
	private ArrayList<EmployeeVO> currentWorkers;
	
	public EmployeeManagement() {
		this.currentWorkers = new ArrayList<EmployeeVO>(CAPACITY);
		//initialize state as ALLAVAILABLE
		state = EmployeesAvailabilityState.ALLAVAILABLE;
	}
	
	
	/**
	 * A new EmployeeVO object "newWorker" is created by its factory, added to the service (call method addEmployeeVO) and added as currentWorkers <br>
	 * The capacity is checked against the size of currentWorkers. If the size equals or is bigger than the CAPACITY the state is changed to NOBODYAVAILABLE, else it is AVAILABLE
	 * @param pNo
	 * @param lastName
	 * @param firstName
	 * @param service which is either kitchen or delivery
	 * @return "newWorker"
	 * @throws IllegalStateException with the message "All employees are working." if state is NOBODYAVAILABLE
	 */
	public EmployeeVO hire(String pNo,String lastName, String firstName,IService service) throws IllegalStateException {
		EmployeeVO newWorker = null;
		
		if(state.equals(EmployeesAvailabilityState.NOBODYAVAILABLE))
			throw new IllegalStateException("All employees are working.");
		
		// use of Factory
		newWorker = new EmployeeFactory().create(pNo, lastName, firstName, service);
		
		service.addEmployee(newWorker);
		this.currentWorkers.add(newWorker);
		
		if(currentWorkers.size() >= CAPACITY) {
			state = EmployeesAvailabilityState.NOBODYAVAILABLE;
		}
		else 
			state = EmployeesAvailabilityState.AVAILABLE;
		return newWorker;
	}
	
	/**
	 * checks if employee is in currentWorkers. If its is true the employee is removed from currentWorkers and the service where the employee works in ((call method removeEmployeeVO) <br>
	 * The size of currentWorkers is checked. If the size equals 0 the state is changed to ALLAVAILABLE, else it is AVAILABLE
	 * @param employee
	 * @throws IllegalStateException with the message "No employees are working." if state is ALLAVAILABLE
	 */
	public void fire(EmployeeVO employee) throws IllegalStateException {
		if(state.equals(EmployeesAvailabilityState.ALLAVAILABLE))
			throw new IllegalStateException("No employees are working.");
		for(int i = 0; i< currentWorkers.size(); i++) {
			if(this.currentWorkers.get(i).equals(employee)) {
				this.currentWorkers.get(i).getWorksIn().removeEmployee(employee);
				this.currentWorkers.remove(i);
			}
		}
		if(currentWorkers.size() == 0) {
			state = EmployeesAvailabilityState.ALLAVAILABLE;
		}
		else 
			state = EmployeesAvailabilityState.AVAILABLE;
	}
	
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("EmployeeManagement: Capacity = " + CAPACITY +"\n"+
				"Currently managing the " + currentWorkers.size() + " employee(s):\n");
		for(int i =0; i< this.currentWorkers.size(); i++) {
			sb.append(this.currentWorkers.get(i).toString() + "\n");
		}
		return sb.toString();
	}


	public EmployeesAvailabilityState getState() {
		return state;
	}


	public void setState(EmployeesAvailabilityState state) {
		this.state = state;
	}


	public ArrayList<EmployeeVO> getCurrentWorkers() {
		return currentWorkers;
	}


	public void setCurrentWorkers(ArrayList<EmployeeVO> currentWorkers) {
		this.currentWorkers = currentWorkers;
	}


	public int getCAPACITY() {
		return CAPACITY;
	}
	
   
}