package de.thb.pizzaPronto.customer.data;

import de.thb.pizzaPronto.Exception.IdNotFoundException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Getter
@Repository
public class CustomerDAO implements ICustomerDAO {
    private List<CustomerVO> customers;

    public CustomerDAO() {
        customers = new ArrayList<>();
    }

    @Override
    public void saveCustomer(CustomerVO customer) {
        if(customer == null){
            throw new NullPointerException("Customer should not be null.");
        }
        if(customer.getId() == 0){
            customer.resetId();
            customers.add(customer);
        }else{
            int appearanceIndex = customers.indexOf(customer);
            if(appearanceIndex != -1){
                this.customers.set(appearanceIndex, customer);
            }else{
                customers.add(customer);
            }
        }
    }

    @Override
    public List<CustomerVO> getAllCustomers() {
        return customers;
    }

    @Override
    public void deleteCustomerById(int customerId) throws IdNotFoundException {
        if(!customers.remove(new CustomerVO(customerId))){
            throw new IdNotFoundException("There is no customer with the given id");
        }
    }
}
