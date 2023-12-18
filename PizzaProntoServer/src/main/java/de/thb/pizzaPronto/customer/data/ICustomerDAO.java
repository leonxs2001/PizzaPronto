package de.thb.pizzaPronto.customer.data;

import de.thb.pizzaPronto.Exception.IdNotFoundException;

import java.util.List;

public interface ICustomerDAO {
    CustomerVO saveCustomer(CustomerVO customer);
    List<CustomerVO> getAllCustomers();
    void deleteCustomerById(int customerId) throws IdNotFoundException;
}
