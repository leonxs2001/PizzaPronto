package de.thb.pizzaPronto.generalGui;

import de.thb.pizzaPronto.authentication.rest.IAuthenticationRESTController;
import de.thb.pizzaPronto.customer.rest.ICustomerRESTController;
import lombok.Getter;
import lombok.Setter;


import javax.swing.*;


@Getter
@Setter
@SuppressWarnings("serial")
public class MainView extends JFrame {
	private MainPanel mainPanel;

	private IAuthenticationRESTController authenticationRESTController;
	private ICustomerRESTController customerRESTController;

	public MainView(IAuthenticationRESTController authenticationRESTController, ICustomerRESTController customerRESTController) {
		setAuthenticationRESTController(authenticationRESTController);
		setCustomerRESTController(customerRESTController);
		mainPanel = new MainPanel(authenticationRESTController, customerRESTController);

		add(mainPanel);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null); // Center the frame on the screen
		setVisible(true);
	}

}
