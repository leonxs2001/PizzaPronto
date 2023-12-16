package de.thb.dim.pizzaProntoGUI.authentication.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.thb.dim.pizzaProntoGUI.authentication.data.AuthenticatedUserVO;
import de.thb.dim.pizzaProntoGUI.authentication.data.UserVO;
import de.thb.dim.pizzaProntoGUI.Exception.NoAuthenticatedUserException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@NoArgsConstructor
@Getter
public class AuthenticationRESTController implements IAuthenticationRESTController {
    private AuthenticatedUserVO authenticatedUser;

    private final String AUTHENTICATION_URL = API_URL + "/authenticate";

    @Override
    public boolean login(UserVO user) {


        HttpClient httpClient = HttpClient.newHttpClient();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String userJson = objectMapper.writeValueAsString(user);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(AUTHENTICATION_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(userJson))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                this.authenticatedUser = objectMapper.readValue(response.body(), AuthenticatedUserVO.class);
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
    public boolean hasAuthenticatedUser() {
        return authenticatedUser != null;
    }

    @Override
    public AuthenticatedUserVO getAuthenticatedUserVO() {
        return authenticatedUser;
    }

    @Override
    public HttpRequest authenticateHttpRequest(HttpRequest request) throws NoAuthenticatedUserException {
        if (hasAuthenticatedUser()) {
            return HttpRequest.newBuilder(request, (name, value) -> true)
                    .header("Authorization", "Bearer " + authenticatedUser.getToken())
                    .build();
        } else {
            throw new NoAuthenticatedUserException("There is no given AuthenticatedUser.");
        }
    }
}
