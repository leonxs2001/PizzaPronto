package de.thb.dim.pizzaProntoGUI.authentication.rest;

import de.thb.dim.pizzaProntoGUI.authentication.data.AuthenticatedUserVO;
import de.thb.dim.pizzaProntoGUI.authentication.data.UserVO;
import de.thb.dim.pizzaProntoGUI.Exception.NoAuthenticatedUserException;

import java.net.http.HttpRequest;

public interface IAuthenticationRESTController {
    String API_URL = "http://localhost:8080";

    boolean login(UserVO user);
    boolean hasAuthenticatedUser();
    AuthenticatedUserVO getAuthenticatedUserVO();
    HttpRequest authenticateHttpRequest(HttpRequest request) throws NoAuthenticatedUserException;
}
