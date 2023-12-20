package de.thb.pizzaPronto.customer.rest;

import de.thb.pizzaPronto.exception.FailedRESTCallException;
import de.thb.pizzaPronto.exception.NoAuthenticatedUserException;

import java.util.List;

public interface ICustomerRESTController {
    CustomerVO addCustomer(CustomerVO customer) throws FailedRESTCallException, NoAuthenticatedUserException;

    void deleteCustomer(CustomerVO customer) throws FailedRESTCallException, NoAuthenticatedUserException;

    List<CustomerVO> getAllCustomers() throws FailedRESTCallException, NoAuthenticatedUserException;
}
