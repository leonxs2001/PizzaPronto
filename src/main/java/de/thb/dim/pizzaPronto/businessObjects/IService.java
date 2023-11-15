package de.thb.dim.pizzaPronto.businessObjects;

import de.thb.dim.pizzaPronto.businessObjects.exceptions.NoCustomerException;
import de.thb.dim.pizzaPronto.businessObjects.exceptions.NotEnoughTimeException;
import de.thb.dim.pizzaPronto.valueObjects.EmployeeVO;
import de.thb.dim.pizzaPronto.valueObjects.OrderVO;

public interface IService {
	public String startService(OrderVO order) throws NoCustomerException, IllegalStateException, NullPointerException, NotEnoughTimeException;
	public void addEmployee(EmployeeVO employee);
	public void removeEmployee(EmployeeVO employee);
	public void removeEmployee(String persNr);
	public String getServiceName();
	public void setServiceName(String serviceName);
}
