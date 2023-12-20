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

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable int id) throws IdNotFoundException {
        customerService.deleteCustomer(id);
    }

    @PostMapping
    public CustomerVO addCustomer(@RequestBody CustomerVO customerVO){
        return customerService.addCustomer(customerVO);
    }
}
