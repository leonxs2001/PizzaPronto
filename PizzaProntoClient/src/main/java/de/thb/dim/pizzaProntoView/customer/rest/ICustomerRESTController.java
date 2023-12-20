package de.thb.dim.pizzaProntoView.customer.rest;

import de.thb.dim.pizzaProntoView.exception.FailedRESTCallException;
import de.thb.dim.pizzaProntoView.exception.NoAuthenticatedUserException;

import java.util.List;

public interface ICustomerRESTController {
    CustomerVO addCustomer(CustomerVO customer) throws FailedRESTCallException, NoAuthenticatedUserException;

    void deleteCustomer(CustomerVO customer) throws FailedRESTCallException, NoAuthenticatedUserException;

    List<CustomerVO> getAllCustomers() throws FailedRESTCallException, NoAuthenticatedUserException;
}
