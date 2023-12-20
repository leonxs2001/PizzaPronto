import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import de.thb.pizzaPronto.authentication.gui.AuthenticationGUIController;
import de.thb.pizzaPronto.authentication.rest.AuthenticationRESTController;
import de.thb.pizzaPronto.customer.gui.CustomerGUIController;
import de.thb.pizzaPronto.customer.rest.CustomerRESTController;
import de.thb.pizzaPronto.generalGui.MainGUIController;
import de.thb.pizzaPronto.generalGui.MainView;
import de.thb.pizzaPronto.menu.gui.MenuController;
import de.thb.pizzaPronto.order.gui.OrderController;
import de.thb.pizzaPronto.staff.gui.StaffController;

public class GUI_Main {

    public static void main(String[] args) {
        AuthenticationRESTController authenticationRESTController = new AuthenticationRESTController();
        CustomerRESTController customerRESTController = new CustomerRESTController(authenticationRESTController);
		SwingUtilities.invokeLater(new Runnable() {
			
			@SuppressWarnings("unused")
			public  void run() {
				
				try {
					UIManager.setLookAndFeel(
							UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				MainView view = new MainView(authenticationRESTController, customerRESTController);

				MainGUIController mainGUIController = new MainGUIController(authenticationRESTController, customerRESTController, view);
				AuthenticationGUIController authenticationGUIController = new AuthenticationGUIController(mainGUIController, view.getMainPanel().getLoginPanel());
				StaffController staffController = new StaffController(view);
				CustomerGUIController customerGUIController = new CustomerGUIController(mainGUIController, view.getMainPanel().getLayoutPanel().getCustomerPanel());
				OrderController orderController = new OrderController(view);
				MenuController menuController = new MenuController(view);
			}
		});

        /*try {
            AuthenticationRESTController authenticationRESTController = new AuthenticationRESTController();
            authenticationRESTController.login(new UserVO("default", "passwort"));

            CustomerRESTController customerRESTController = new CustomerRESTController(authenticationRESTController);
            CustomerVO c1 = customerRESTController.addCustomer(new CustomerVO(Gender.F, LocalDate.now(), "Schönber", "Leon", "Genthiner Str.", 91));
            CustomerVO c2 = customerRESTController.addCustomer(new CustomerVO(Gender.F, LocalDate.now(), "Schönber", "Leon", "Genthiner Str.", 91));

            System.out.println(customerRESTController.getAllCustomers());

            customerRESTController.deleteCustomer(c2);

            System.out.println(customerRESTController.getAllCustomers());

            c1.setLastName("Schönberg");
            customerRESTController.updateCustomer(c1);
            System.out.println(customerRESTController.getAllCustomers());


        } catch (Exception e) {
            System.out.println(e);
        }*/
    }

}
