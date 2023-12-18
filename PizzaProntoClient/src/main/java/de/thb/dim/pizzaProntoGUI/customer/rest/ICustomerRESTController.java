package de.thb.dim.pizzaProntoGUI.customer.rest;

import de.thb.dim.pizzaProntoGUI.Exception.FailedRESTCallException;
import de.thb.dim.pizzaProntoGUI.Exception.NoAuthenticatedUserException;
import de.thb.dim.pizzaProntoGUI.customer.data.CustomerVO;

import java.util.List;

public interface ICustomerRESTController {
    CustomerVO addCustomer(CustomerVO customer) throws FailedRESTCallException, NoAuthenticatedUserException;

    void deleteCustomer(CustomerVO customer) throws FailedRESTCallException, NoAuthenticatedUserException;

    List<CustomerVO> getAllCustomers() throws FailedRESTCallException, NoAuthenticatedUserException;

    void updateCustomer(CustomerVO customer) throws FailedRESTCallException, NoAuthenticatedUserException;
}
