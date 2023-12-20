package de.thb.pizzaPronto.authentication.gui;

import de.thb.pizzaPronto.authentication.rest.IAuthenticationRESTController;
import de.thb.pizzaPronto.authentication.rest.UserVO;
import de.thb.pizzaPronto.exception.FailedRESTCallException;
import de.thb.pizzaPronto.generalGui.MainGUIController;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationGUIController {
    private LoginPanel loginPanel;
    private MainGUIController mainGUIController;

    private IAuthenticationRESTController authenticationRESTController;

    public AuthenticationGUIController(MainGUIController mainGUIController, LoginPanel loginPanel) {
        setLoginPanel(loginPanel);
        setMainGUIController(mainGUIController);
        loginPanel.getSubmitButton().addActionListener(e -> login());

        setAuthenticationRESTController(mainGUIController.getAuthenticationRESTController());
    }

    public void login() {
        UserVO user = loginPanel.getUserFromInputField();
        try {
            authenticationRESTController.login(user);

            // tell the mainGUIController that the login succeeded
            mainGUIController.onLogin();

        } catch (FailedRESTCallException ex) {
            loginPanel.getErrorLabel().setText("Es ist leider ein Fehler aufgetreten. Haben sie dir richtige Kombination aus Passwort und Nutzername eingegeben?");
        }
        loginPanel.getPasswordField().setText("");
    }
}
