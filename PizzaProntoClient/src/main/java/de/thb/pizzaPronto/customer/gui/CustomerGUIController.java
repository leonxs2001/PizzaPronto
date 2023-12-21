package de.thb.pizzaPronto.customer.gui;

import de.thb.pizzaPronto.customer.rest.CustomerVO;
import de.thb.pizzaPronto.customer.rest.ICustomerRESTController;
import de.thb.pizzaPronto.exception.FailedRESTCallException;
import de.thb.pizzaPronto.exception.NoAuthenticatedUserException;
import de.thb.pizzaPronto.generalGui.ExceptionPanel;
import de.thb.pizzaPronto.generalGui.MainGUIController;
import de.thb.pizzaPronto.generalGui.MainView;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

//HIER WEITER MACHEN TODO
@Getter
@Setter
public class CustomerGUIController {

    private MainView view;
    private CustomerPanel customerPanel;

    private MainGUIController mainGUIController;
    private ICustomerRESTController customerRESTController;

    public CustomerGUIController(MainGUIController mainGUIController, CustomerPanel customerPanel) {

        setMainGUIController(mainGUIController);
        setCustomerPanel(customerPanel);
        setCustomerRESTController(mainGUIController.getCustomerRESTController());

        // add customer
        JButton addButton = customerPanel.getAddButton();
        addButton.addActionListener(e -> addCustomer());

        //delete customer
        JButton removeButton = customerPanel.getRemoveButton();
        removeButton.addActionListener(e -> deleteCustomer());

        //print details
        JButton printButton = customerPanel.getPrintButton();
        printButton.addActionListener(e -> printCustomer());

        // reload everytime the component is shown
        customerPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent evt) {
            }

            @Override
            public void componentShown(ComponentEvent evt) {
                reload();
            }
        });
    }

    public void addCustomer() {
        try {
            CustomerVO customer = customerPanel.getCustomerFromInputs();
            customer = customerRESTController.addCustomer(customer);
            customerPanel.addCustomerToTable(customer);
        } catch (NullPointerException | FailedRESTCallException | NoAuthenticatedUserException ex) {
            new ExceptionPanel(ex);
        }
    }

    public void deleteCustomer() {
        CustomerVO customer = customerPanel.getSelectedCustomer();
        customerPanel.deleteSelectedCustomerRow();
        try {
            customerRESTController.deleteCustomer(customer);
        } catch (NoAuthenticatedUserException | FailedRESTCallException ex) {
            new ExceptionPanel(ex);
        }
    }

    public void reload() {
        customerPanel.deleteAllCustomersFromTable();
        try {
            customerRESTController.getAllCustomers().forEach((CustomerVO customer) -> {
                customerPanel.addCustomerToTable(customer);
            });
        } catch (NoAuthenticatedUserException | FailedRESTCallException ex) {
            new ExceptionPanel(ex);
        }
    }

    public void printCustomer(){
        CustomerVO customer = customerPanel.getSelectedCustomer();
        customerPanel.printCustomer(customer);
    }
}
