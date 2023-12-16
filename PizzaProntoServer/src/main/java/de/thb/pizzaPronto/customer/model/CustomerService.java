package de.thb.pizzaPronto.customer.model;

import de.thb.pizzaPronto.Exception.IdNotFoundException;
import de.thb.pizzaPronto.customer.data.CustomerVO;
import de.thb.pizzaPronto.customer.data.ICustomerDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService implements ICustomerService{
    private final ICustomerDAO customerDAO;


    @Override
    public void addCustomer(CustomerVO customer) {
        customerDAO.saveCustomer(customer);
    }

    @Override
    public void updateCustomer(CustomerVO customer) {
        customerDAO.saveCustomer(customer);
    }

    @Override
    public void deleteCustomer(int customerId) throws IdNotFoundException {
        customerDAO.deleteCustomerById(customerId);
    }

    @Override
    public List<CustomerVO> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }
}
