package de.thb.dim.pizzaProntoView.authentication.rest;

import de.thb.dim.pizzaProntoView.exception.FailedRESTCallException;
import de.thb.dim.pizzaProntoView.exception.NoAuthenticatedUserException;

import java.net.http.HttpRequest;

public interface IAuthenticationRESTController {
    String API_URL = "http://localhost:8080";

    void login(UserVO user) throws FailedRESTCallException;
    boolean hasAuthenticatedUser();
    AuthenticatedUserVO getAuthenticatedUserVO();
    HttpRequest authenticateHttpRequest(HttpRequest request) throws NoAuthenticatedUserException;

}
