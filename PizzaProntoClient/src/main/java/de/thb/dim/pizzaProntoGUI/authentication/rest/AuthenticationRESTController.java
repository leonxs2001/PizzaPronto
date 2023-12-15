package de.thb.dim.pizzaProntoGUI.authentication.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.thb.dim.pizzaProntoGUI.authentication.data.AuthenticatedUserVO;
import de.thb.dim.pizzaProntoGUI.authentication.data.UserVO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@NoArgsConstructor
@Getter
public class AuthenticationRESTController implements IAuthenticationRESTController {
    private AuthenticatedUserVO authenticatedUser;

    @Override
    public boolean login(UserVO user) {

        String apiUrl = "http://localhost:8080/authenticate";

        // Erstelle einen HttpClient
        HttpClient httpClient = HttpClient.newHttpClient();

        try {
            // Konvertiere das UserVO-Objekt in JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String userJson = objectMapper.writeValueAsString(user);

            // Erstelle die Anfrage mit POST-Body
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(userJson))
                    .build();

            // Sende die Anfrage und erhalte die Antwort
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Überprüfe, ob die Anfrage erfolgreich war (Statuscode 200)
            if (response.statusCode() == 200) {
                // Konvertiere die JSON-Antwort in ein AuthenticatedUserVO-Objekt
                AuthenticatedUserVO authenticatedUser = objectMapper.readValue(response.body(), AuthenticatedUserVO.class);

                // Verarbeite das AuthenticatedUserVO-Objekt
                System.out.println(authenticatedUser.getToken());
                System.out.println(authenticatedUser.getUsername());
                System.out.println(authenticatedUser.getRole());
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
}
