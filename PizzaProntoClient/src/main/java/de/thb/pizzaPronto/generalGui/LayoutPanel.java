package de.thb.pizzaPronto.generalGui;
import de.thb.pizzaPronto.customer.gui.CustomerPanel;
import de.thb.pizzaPronto.customer.rest.ICustomerRESTController;
import de.thb.pizzaPronto.menu.gui.MenuPanel;
import de.thb.pizzaPronto.order.gui.OrderPanel;
import de.thb.pizzaPronto.staff.gui.StaffPanel;
import lombok.Getter;
import lombok.Setter;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")
@Setter
@Getter
public class LayoutPanel extends JPanel {
	
	private TopPanel topPanel;
	private SidePanel sidePanel;
	private JPanel mainPanel;
	private HomePanel homePanel;
	private StaffPanel staffPanel2;
	private CustomerPanel customerPanel;
	private MenuPanel menuPanel;
	private OrderPanel orderPanel;

	private ICustomerRESTController customerRESTController;
		
	public LayoutPanel(ICustomerRESTController customerRESTController) {
		setCustomerRESTController(customerRESTController);

		setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
			
		topPanel = new TopPanel();
		topPanel.setPreferredSize(new Dimension(0, 50));

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(topPanel, c);

		sidePanel = new SidePanel();
		sidePanel.setPreferredSize(new Dimension(110, 0));
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.VERTICAL;
		add(sidePanel, c);
		
		homePanel = new HomePanel();
		staffPanel2 = new StaffPanel();
		customerPanel = new CustomerPanel(customerRESTController);
		menuPanel = new MenuPanel();
		orderPanel = new OrderPanel();
		
		mainPanel = new JPanel(new CardLayout());
		mainPanel.add(homePanel, "homePanel");
		mainPanel.add(staffPanel2, "staffPanel");
		mainPanel.add(customerPanel, "customerPanel");
		mainPanel.add(menuPanel, "menuPanel");
		mainPanel.add(orderPanel, "orderPanel");
		mainPanel.setPreferredSize(new Dimension(1170, 700));
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.NONE;
		add(mainPanel, c);
		
		sidePanel.setMainPanel(mainPanel);
		homePanel.setMainPanel(mainPanel);

	}
	

}
