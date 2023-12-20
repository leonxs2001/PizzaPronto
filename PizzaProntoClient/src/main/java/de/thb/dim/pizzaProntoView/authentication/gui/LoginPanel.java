package de.thb.dim.pizzaProntoView.authentication.gui;

import de.thb.dim.pizzaProntoView.authentication.rest.UserVO;
import de.thb.dim.pizzaProntoView.generalGui.MainPanel;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class LoginPanel extends JPanel{
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel errorLabel;
    private JButton submitButton;

    private MainPanel mainPanel;


    public LoginPanel(MainPanel mainPanel) {
        setMainPanel(mainPanel);

        setLayout(new GridBagLayout());

        JLabel usernameLabel = new JLabel("Benutzername:");
        usernameField = new JTextField();
        usernameField.setColumns(10);

        JLabel passwordLabel = new JLabel("Passwort:");
        passwordField = new JPasswordField();
        passwordField.setColumns(10);

        submitButton = new JButton("Einloggen");
        errorLabel = new JLabel("");
        errorLabel.setForeground(new Color(0xff0000));

        // Komponenten zum Panel hinzufügen
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // optional: Abstand zu den Rändern
        add(errorLabel, gbc);

        gbc.gridy++;
        add(usernameLabel, gbc);

        gbc.gridy++;
        add(usernameField, gbc);

        gbc.gridy++;
        add(passwordLabel, gbc);

        gbc.gridy++;
        add(passwordField, gbc);

        gbc.gridy++;
        add(submitButton, gbc);

    }

    public UserVO getUserFromInputField(){
        String username = usernameField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);

        return new UserVO(username ,password);
    }
}
