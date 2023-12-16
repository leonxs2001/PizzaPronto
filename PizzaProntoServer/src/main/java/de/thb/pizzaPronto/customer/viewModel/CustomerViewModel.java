package de.thb.pizzaPronto.customer.viewModel;

import de.thb.pizzaPronto.Exception.IdNotFoundException;
import de.thb.pizzaPronto.customer.data.CustomerVO;
import de.thb.pizzaPronto.customer.model.ICustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerViewModel {

    private final ICustomerService customerService;

    @GetMapping
    public List<CustomerVO> getALlCustomers(){
        return customerService.getAllCustomers();
    }

    @DeleteMapping
    public void deleteCustomer(@RequestBody int id) throws IdNotFoundException {
        customerService.deleteCustomer(id);
    }

    @PostMapping
    public void addCustomer(@RequestBody CustomerVO customerVO){
        customerService.addCustomer(customerVO);
    }

    @PutMapping
    public void updateCustomer(@RequestBody CustomerVO customerVO){
        customerService.updateCustomer(customerVO);
    }
}
