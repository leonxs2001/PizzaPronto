package de.thb.pizzaPronto.menu.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import de.thb.pizzaPronto.authentication.rest.IAuthenticationRESTController;
import de.thb.pizzaPronto.exception.FailedRESTCallException;
import de.thb.pizzaPronto.exception.NoAuthenticatedUserException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MenuRESTController implements IMenuRESTController{

    private final String MENU_URL = IAuthenticationRESTController.API_URL + "/menu";

    private final IAuthenticationRESTController authenticationRESTController;

    public MenuRESTController(IAuthenticationRESTController authenticationRESTController){
         this.authenticationRESTController = authenticationRESTController;
    }

    @Override
    public void importFromInputStream(InputStream fileInputStream) throws FailedRESTCallException, NoAuthenticatedUserException {
        HttpClient httpClient = HttpClient.newHttpClient();

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(MENU_URL + "/import"))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofInputStream(() -> fileInputStream))
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
    public OutputStream exportMenu() throws FailedRESTCallException, NoAuthenticatedUserException {
        return null;
    }

    @Override
    public void addDish(DishVO dish) throws FailedRESTCallException, NoAuthenticatedUserException {
        HttpClient httpClient = HttpClient.newHttpClient();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            String dishJson = objectMapper.writeValueAsString(dish);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(MENU_URL))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(dishJson))
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
    public void deleteDish(DishVO dish) throws FailedRESTCallException, NoAuthenticatedUserException {
        HttpClient httpClient = HttpClient.newHttpClient();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(MENU_URL + "/" + dish.getNumber()))
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
    public MenuVO getMenu() throws FailedRESTCallException, NoAuthenticatedUserException {
        HttpClient httpClient = HttpClient.newHttpClient();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(MENU_URL))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            request = authenticationRESTController.authenticateHttpRequest(request);


            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

//            MenuVO menuVO = objectMapper.readValue(response.body(), MenuVO.class);
            if (response.statusCode() == 200) {
                return objectMapper.readValue(response.body(), MenuVO.class);
            } else {
                throw new FailedRESTCallException();
            }

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            throw new FailedRESTCallException();
        }
    }




}
