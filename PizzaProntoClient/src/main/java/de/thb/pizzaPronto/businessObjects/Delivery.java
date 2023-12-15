package de.thb.pizzaPronto.businessObjects;

import java.util.Random;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

import de.thb.pizzaPronto.valueObjects.EmployeeVO;
import de.thb.pizzaPronto.valueObjects.DeliveryManVO;
import de.thb.pizzaPronto.valueObjects.OrderVO;
import de.thb.pizzaPronto.valueObjects.StateOfOrderVO;
import de.thb.pizzaPronto.businessObjects.exceptions.NoCustomerException;
import de.thb.pizzaPronto.valueObjects.CustomerVO;

public class Delivery extends AbstractService {

    private ArrayList<EmployeeVO> employees;

    public Delivery() {
        setServiceName("Delivery");
        employees = new ArrayList<EmployeeVO>();
        // create deliveryman
        employees.add(new DeliveryManVO("Lieferant01", "Rasender", "Rudi"));
        // create deliveryman
        employees.add(new DeliveryManVO("Lieferant02", "Lacy", "Lutz"));
    }

    @Override
    public String startService(OrderVO order) throws NoCustomerException, IllegalStateException {

        CustomerVO currentCustomer;
        EmployeeVO employee = selectEmployee();
        String s = String.format("\nService of DeliveryManVO %s: No order available.",
                employee.getPersonnelNo());
        Objects.requireNonNull(order, s);

        currentCustomer = order.getCustomer();

        if (currentCustomer == null) {
            s = String.format("\nService of DeliveryManVO %s: No customer available.", employee.getPersonnelNo());
            throw new NoCustomerException(s);
        }

        if (order.getState() == StateOfOrderVO.READY) {

            order.setState(StateOfOrderVO.DELIVERED);
            s += String.format("\nDrive to customer  %s %s, in %s %s\n", currentCustomer.getLastName(),
                    currentCustomer.getFirstName(), currentCustomer.getStreet(),
                    currentCustomer.getHouseNumber());
            s += String.format(
                    "\nService of DeliveryManVO %s: ",
                    employee.getPersonnelNo());
            s += String.format(
                    "Order is delivered on %1$tm/%1$td/%1$tY at %1$tH:%1$tM o'clock",
                    LocalDateTime.now());


        } else {
            s = String.format("\nService of DeliveryManVO %s: No order is ready for processing.",
                    employee.getPersonnelNo());
            throw new IllegalStateException(s);
        }
        return s;
    }

    private EmployeeVO selectEmployee() {
        EmployeeVO employee;
        Random zufall = new Random();
        employee = employees.get(zufall.nextInt(employees.size() - 1));
        return employee;
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
        this.employees = employees;
    }

    public void addEmployee(EmployeeVO employee) {
        this.employees.add(employee);
    }

    public void removeEmployee(EmployeeVO employee) {
        this.employees.remove(employee);
    }

    public void removeEmployee(String persNr) {
        for (int i = 0; i < this.employees.size(); i++) {
            if (this.employees.get(i).getPersonnelNo() == persNr) {
                this.employees.remove(i);
            }
        }
    }
}
