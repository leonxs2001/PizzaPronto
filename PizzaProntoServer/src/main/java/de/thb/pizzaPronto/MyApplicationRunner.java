package de.thb.pizzaPronto;

import de.thb.pizzaPronto.authentication.data.IUserDAO;
import de.thb.pizzaPronto.authentication.data.RoleVO;
import de.thb.pizzaPronto.authentication.data.UserVO;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MyApplicationRunner implements ApplicationRunner {
    private final IUserDAO userDAO;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        RoleVO defaultRole = new RoleVO("default");
        RoleVO adminRole = new RoleVO("admin");
        userDAO.saveUser(new UserVO("default", "passwort", defaultRole));
        userDAO.saveUser(new UserVO("admin", "passwort", adminRole));
    }
}
