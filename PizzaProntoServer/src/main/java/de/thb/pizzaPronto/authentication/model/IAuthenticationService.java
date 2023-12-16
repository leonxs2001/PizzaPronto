package de.thb.pizzaPronto.authentication.model;

import de.thb.pizzaPronto.authentication.data.AuthenticatedUserVO;
import de.thb.pizzaPronto.authentication.data.UserVO;
import de.thb.pizzaPronto.Exception.WrongUserCredentialsException;

public interface IAuthenticationService {
    AuthenticatedUserVO authenticateUser(UserVO user) throws WrongUserCredentialsException;
}
