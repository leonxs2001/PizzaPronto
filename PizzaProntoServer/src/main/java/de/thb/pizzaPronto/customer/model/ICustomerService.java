package de.thb.pizzaPronto.customer.model;

import de.thb.pizzaPronto.Exception.IdNotFoundException;
import de.thb.pizzaPronto.customer.data.CustomerVO;

import java.util.List;

public interface ICustomerService {
    CustomerVO addCustomer(CustomerVO customer);
    void deleteCustomer(int customerId) throws IdNotFoundException;
    List<CustomerVO> getAllCustomers();
}
