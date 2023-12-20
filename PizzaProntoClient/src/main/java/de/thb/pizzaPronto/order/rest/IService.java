package de.thb.pizzaPronto.order.rest;

import de.thb.pizzaPronto.exception.NoCustomerException;
import de.thb.pizzaPronto.exception.NotEnoughTimeException;
import de.thb.pizzaPronto.staff.rest.EmployeeVO;
public interface IService {
	public String startService(OrderVO order) throws NoCustomerException, IllegalStateException, NullPointerException, NotEnoughTimeException;
	public void addEmployee(EmployeeVO employee);
	public void removeEmployee(EmployeeVO employee);
	public void removeEmployee(String persNr);
	public String getServiceName();
	public void setServiceName(String serviceName);
}
