package de.thb.pizzaPronto.authentication.rest;

import de.thb.pizzaPronto.exception.FailedRESTCallException;
import de.thb.pizzaPronto.exception.NoAuthenticatedUserException;
import org.springframework.web.socket.WebSocketHttpHeaders;

import java.net.http.HttpRequest;

public interface IAuthenticationRESTController {
    String API_URL = "http://localhost:8080";

    void login(UserVO user) throws FailedRESTCallException;
    boolean hasAuthenticatedUser();
    AuthenticatedUserVO getAuthenticatedUserVO();
    HttpRequest authenticateHttpRequest(HttpRequest request) throws NoAuthenticatedUserException;
    WebSocketHttpHeaders generateWebSocketAuthenticatedHttpHeader();

}
