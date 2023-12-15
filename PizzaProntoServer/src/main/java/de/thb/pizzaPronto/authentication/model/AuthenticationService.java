package de.thb.pizzaPronto.authentication.model;

import de.thb.pizzaPronto.authentication.data.AuthenticatedUserVO;
import de.thb.pizzaPronto.authentication.data.IUserDAO;
import de.thb.pizzaPronto.authentication.data.UserVO;
import de.thb.pizzaPronto.authentication.model.Exception.WrongUserCredentialsException;
import de.thb.pizzaPronto.security.JwtTokenUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthenticationService implements IAuthenticationService {
    private final IUserDAO userDAO;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authManager;

    @Override
    public AuthenticatedUserVO authenticateUser(UserVO user){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword());

        Authentication authentication = authManager.authenticate(usernamePasswordAuthenticationToken);

        user = (UserVO) authentication.getPrincipal();
        String accessToken = jwtTokenUtil.generateAccessToken(user);
        AuthenticatedUserVO response = new AuthenticatedUserVO(user, accessToken);

        return response;
    }
}
