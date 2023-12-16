package de.thb.pizzaPronto.authentication.viewModel;

import de.thb.pizzaPronto.authentication.data.AuthenticatedUserVO;
import de.thb.pizzaPronto.authentication.data.UserVO;
import de.thb.pizzaPronto.authentication.model.AuthenticationService;
import de.thb.pizzaPronto.Exception.WrongUserCredentialsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Service
@RestController
public class AuthenticationViewModel {
    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public AuthenticatedUserVO authenticateUser(@RequestBody UserVO user) throws WrongUserCredentialsException {
        return authenticationService.authenticateUser(user);
    }
}
