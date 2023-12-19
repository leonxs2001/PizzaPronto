package de.thb.dim.pizzaProntoGUI.authentication.gui;

import de.thb.dim.pizzaProntoGUI.Exception.FailedRESTCallException;
import de.thb.dim.pizzaProntoGUI.authentication.rest.IAuthenticationRESTController;
import de.thb.dim.pizzaProntoGUI.authentication.rest.UserVO;
import de.thb.dim.pizzaProntoGUI.view.LayoutPanel;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel errorLabel;

    private JFrame parent;
    private LayoutPanel layoutPanel;

    private IAuthenticationRESTController authenticationRESTController;

    public LoginPanel(IAuthenticationRESTController authenticationRESTController, JFrame parent, LayoutPanel layoutPanel) {
        setAuthenticationRESTController(authenticationRESTController);

        setParent(parent);
        setLayoutPanel(layoutPanel);

        setLayout(new GridBagLayout());

        JLabel usernameLabel = new JLabel("Benutzername:");
        usernameField = new JTextField();
        usernameField.setColumns(10);

        JLabel passwordLabel = new JLabel("Passwort:");
        passwordField = new JPasswordField();
        passwordField.setColumns(10);

        JButton submitButton = new JButton("Einloggen");
        errorLabel = new JLabel("");

        // Event Listener für den Einloggen-Button
        submitButton.addActionListener(e -> {
            String username = usernameField.getText();
            char[] passwordChars = passwordField.getPassword();
            String password = new String(passwordChars);

            try {
                authenticationRESTController.login(new UserVO(username ,password));
                CardLayout mainCardLayout = (CardLayout)parent.getLayout();
                mainCardLayout.show(parent, "layoutPanel");
            } catch (FailedRESTCallException ex) {
                errorLabel.setText("Es ist leider ein Fehler aufgetreten.");
            }
            passwordField.setText("");
        });

        // Komponenten zum Panel hinzufügen
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // optional: Abstand zu den Rändern
        add(usernameLabel, gbc);

        gbc.gridy++;
        add(usernameField, gbc);

        gbc.gridy++;
        add(passwordLabel, gbc);

        gbc.gridy++;
        add(passwordField, gbc);

        gbc.gridy++;
        add(submitButton, gbc);

        gbc.gridy++;
        add(errorLabel, gbc);
    }
}
