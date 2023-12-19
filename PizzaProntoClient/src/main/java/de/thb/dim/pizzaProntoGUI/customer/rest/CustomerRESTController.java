package de.thb.dim.pizzaProntoGUI.customer.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import de.thb.dim.pizzaProntoGUI.Exception.FailedRESTCallException;
import de.thb.dim.pizzaProntoGUI.Exception.NoAuthenticatedUserException;
import de.thb.dim.pizzaProntoGUI.authentication.rest.IAuthenticationRESTController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;

public class CustomerRESTController implements ICustomerRESTController{
    private final String CUSTOMER_URL = IAuthenticationRESTController.API_URL + "/customer";

    private final IAuthenticationRESTController authenticationRESTController;

    public CustomerRESTController(IAuthenticationRESTController authenticationRESTController){
        this.authenticationRESTController = authenticationRESTController;
    }

    @Override
    public CustomerVO addCustomer(CustomerVO customer) throws FailedRESTCallException, NoAuthenticatedUserException {
        HttpClient httpClient = HttpClient.newHttpClient();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            String customerJson = objectMapper.writeValueAsString(customer);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(CUSTOMER_URL))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(customerJson))
                    .build();

            request = authenticationRESTController.authenticateHttpRequest(request);

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return objectMapper.readValue(response.body(), CustomerVO.class);
            } else {
                throw new FailedRESTCallException();
            }

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            throw new FailedRESTCallException();
        }
    }

    @Override
    public void deleteCustomer(CustomerVO customer) throws FailedRESTCallException, NoAuthenticatedUserException {
        HttpClient httpClient = HttpClient.newHttpClient();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(CUSTOMER_URL + "/" + customer.getId()))
                    .DELETE()
                    .build();

            request = authenticationRESTController.authenticateHttpRequest(request);

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new FailedRESTCallException();
            }

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            throw new FailedRESTCallException();
        }
    }

    @Override
    public List<CustomerVO> getAllCustomers() throws FailedRESTCallException, NoAuthenticatedUserException{
        HttpClient httpClient = HttpClient.newHttpClient();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(CUSTOMER_URL))
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            request = authenticationRESTController.authenticateHttpRequest(request);

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                List<CustomerVO> customers = objectMapper.readValue(response.body(), new TypeReference<>() {});
                return customers;
            } else {
                // Handle error or return an empty list
                return Collections.emptyList();
            }

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            // Handle exception or return an empty list
            return Collections.emptyList();
        }
    }

    @Override
    public void updateCustomer(CustomerVO customer) throws FailedRESTCallException, NoAuthenticatedUserException{
        HttpClient httpClient = HttpClient.newHttpClient();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            String customerJson = objectMapper.writeValueAsString(customer);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(CUSTOMER_URL))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(customerJson))
                    .build();

            request = authenticationRESTController.authenticateHttpRequest(request);

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new FailedRESTCallException();
            }

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            throw new FailedRESTCallException();
        }
    }
}
