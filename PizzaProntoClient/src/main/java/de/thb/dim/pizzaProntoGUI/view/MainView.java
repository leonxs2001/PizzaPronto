package de.thb.dim.pizzaProntoGUI.view;

import de.thb.dim.pizzaProntoGUI.authentication.gui.LoginPanel;
import de.thb.dim.pizzaProntoGUI.authentication.rest.IAuthenticationRESTController;
import de.thb.dim.pizzaProntoGUI.customer.rest.ICustomerRESTController;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;


@Getter
@Setter
@SuppressWarnings("serial")
public class MainView extends JFrame {

	private LayoutPanel layoutPanel;
	private LoginPanel loginPanel;

	private IAuthenticationRESTController authenticationRESTController;
	private ICustomerRESTController customerRESTController;

	public MainView(IAuthenticationRESTController authenticationRESTController,ICustomerRESTController customerRESTController) {
		setAuthenticationRESTController(authenticationRESTController);
		setCustomerRESTController(customerRESTController);

		CardLayout cardLayout = new CardLayout();
		setLayout(cardLayout);

		layoutPanel = new LayoutPanel(customerRESTController);
		loginPanel = new LoginPanel(authenticationRESTController, this, layoutPanel);


		add(loginPanel, "loginPanel");
		add(layoutPanel, "layoutPanel");


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null); // Center the frame on the screen
		setVisible(true);
	}

}
