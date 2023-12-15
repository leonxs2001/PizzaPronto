package de.thb.pizzaPronto.authentication.data;

import de.thb.pizzaPronto.authentication.data.Exception.IdNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserDAO {
    void saveUser(UserVO user);
    UserVO getUserByUsername(String username) throws UsernameNotFoundException;
    UserVO getUserById(int id) throws IdNotFoundException;
}
