package de.thb.dim.pizzaProntoGUI.authentication.rest;

import de.thb.dim.pizzaProntoGUI.Exception.FailedRESTCallException;
import de.thb.dim.pizzaProntoGUI.Exception.NoAuthenticatedUserException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.http.HttpRequest;
import java.util.Objects;

public interface IAuthenticationRESTController {
    String API_URL = "http://localhost:8080";

    void login(UserVO user) throws FailedRESTCallException;
    boolean hasAuthenticatedUser();
    AuthenticatedUserVO getAuthenticatedUserVO();
    HttpRequest authenticateHttpRequest(HttpRequest request) throws NoAuthenticatedUserException;

}
