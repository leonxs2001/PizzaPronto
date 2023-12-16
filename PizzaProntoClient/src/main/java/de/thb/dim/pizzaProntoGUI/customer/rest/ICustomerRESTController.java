package de.thb.dim.pizzaProntoGUI.customer.rest;

import de.thb.dim.pizzaProntoGUI.customer.data.CustomerVO;

import java.util.List;

public interface ICustomerRESTController {
    boolean addCustomer(CustomerVO customer);
    boolean deleteCustomer(CustomerVO customer);
    List<CustomerVO> getAllCustomers(CustomerVO customer);
    boolean updateCustomer(CustomerVO customer);
}
