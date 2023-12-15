package de.thb.dim.pizzaProntoGUI.authentication.rest;

import de.thb.dim.pizzaProntoGUI.authentication.data.AuthenticatedUserVO;
import de.thb.dim.pizzaProntoGUI.authentication.data.UserVO;

public interface IAuthenticationRESTController {
    boolean login(UserVO user);
    boolean hasAuthenticatedUser();
    AuthenticatedUserVO getAuthenticatedUserVO();
}
