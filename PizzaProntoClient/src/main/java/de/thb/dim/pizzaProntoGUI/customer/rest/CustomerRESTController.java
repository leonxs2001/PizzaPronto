package de.thb.dim.pizzaProntoGUI.customer.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import de.thb.dim.pizzaProntoGUI.authentication.data.AuthenticatedUserVO;
import de.thb.dim.pizzaProntoGUI.authentication.rest.IAuthenticationRESTController;
import de.thb.dim.pizzaProntoGUI.customer.data.CustomerVO;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class CustomerRESTController implements ICustomerRESTController{
    private final String CUSTOMER_URL = IAuthenticationRESTController.API_URL + "/customer";

    private final IAuthenticationRESTController authenticationRESTController;

    public CustomerRESTController(IAuthenticationRESTController authenticationRESTController){
        this.authenticationRESTController = authenticationRESTController;
    }

    @Override
    public boolean addCustomer(CustomerVO customer) {
        HttpClient httpClient = HttpClient.newHttpClient();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            String userJson = objectMapper.writeValueAsString(customer);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(CUSTOMER_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(userJson))
                    .build();

            request = authenticationRESTController.authenticateHttpRequest(request);

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteCustomer(CustomerVO customer) {
        return false;
    }

    @Override
    public List<CustomerVO> getAllCustomers(CustomerVO customer) {
        return null;
    }

    @Override
    public boolean updateCustomer(CustomerVO customer) {
        return false;
    }
}
