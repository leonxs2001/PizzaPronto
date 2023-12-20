package de.thb.dim.pizzaProntoView.customer.gui;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.time.LocalDate;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import de.thb.dim.pizzaProntoView.customer.rest.CustomerVO;
import de.thb.dim.pizzaProntoView.customer.rest.Gender;
import de.thb.dim.pizzaProntoView.customer.rest.ICustomerRESTController;
import de.thb.dim.pizzaProntoView.exception.CustomerNoDateOfBirthException;
import de.thb.dim.pizzaProntoView.generalGui.DefaultButton;
import de.thb.dim.pizzaProntoView.generalGui.ExceptionPanel;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
public class CustomerPanel extends JPanel {

    private JPanel headerPanel;
    private JPanel addPanel;
    private JPanel tablePanel;
    private JPanel hintPanel;
    private JPanel datePanel;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField yearTextField;
    private JTextField houseNoTextField;
    private JTextField streetTextField;
    private JLabel dateOfBirthLabel;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel genderLabel;
    private JLabel addTopicLabel;
    private JLabel tableTopicLabel;
    private JLabel hintLabel;
    private JLabel day;
    private JLabel month;
    private JLabel year;
    private JLabel streetLabel;
    private JLabel houseNoLabel;
    private JComboBox<Gender> genderComboBox;
    private JComboBox<Integer> dayComboBox;
    private JComboBox<Integer> monthComboBox;
    private DefaultButton addButton;
    private DefaultButton removeButton;
    private DefaultButton printButton;
    private JTable table;
    private JScrollPane tableScrollPane;
    private DefaultTableModel tableModel;

    private ICustomerRESTController customerRESTController;

    public CustomerPanel(ICustomerRESTController customerRESTController) {

        setCustomerRESTController(customerRESTController);

        setOpaque(true);
        setBackground(new Color(0xeaeaea));
        setBorder(BorderFactory.createMatteBorder(5, 0, 0, 0, new ImageIcon("gui/de/thb/dim/pizzaProntoGUI/images/border.png")));
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        headerPanel = new JPanel();
        headerPanel = new JPanel(new GridBagLayout());
        headerPanel.setBackground(new Color(0xeaeaea));
        addComponentsToHeaderPanel(headerPanel);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.insets = new Insets(15, 30, 5, 10);
        c.fill = GridBagConstraints.BOTH;
        add(headerPanel, c);

        addPanel = new JPanel();
        addPanel.setBackground(Color.WHITE);
        addPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        addPanel.setPreferredSize(new Dimension(500, 0));
        addComponentsToAddPanel(addPanel);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0;
        c.weighty = 1;
        c.insets = new Insets(10, 30, 10, 10);
        add(addPanel, c);

        tablePanel = new JPanel();
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        addComponentsToTablePanel(tablePanel);
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 2;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 0, 10, 10);
        add(tablePanel, c);

        hintPanel = new JPanel();
        hintPanel.setBackground(Color.WHITE);
        hintPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        addComponentsToHintPanel(hintPanel);
        c.gridx = 0;
        c.gridy = 4;
        c.gridheight = 0;
        c.gridwidth = 2;
        c.weightx = 1;
        c.weighty = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0, 30, 10, 10);
        add(hintPanel, c);
    }

    private void addComponentsToHintPanel(JPanel hintPanel) {
        hintPanel.setLayout(new GridBagLayout());
        hintLabel = new JLabel("<html><p><strong><span style=\"font-size: 10px;\">Hinweis</span></strong></p>\n" +
                "<p><span style=\"font-size: 10px;\">Es koennen die Exceptions CustomerNoDateOfBirthException und CustomerTooYoungException ausgegeben werden, " +
                "indem entweder kein Jahr angegeben wird bzw. das Alter kleiner als 18 Jahre ist.</span></p></html>");


        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.insets = new Insets(10, 10, 10, 10);
        hintPanel.add(hintLabel, c);
    }

    public void addComponentsToAddPanel(JPanel addPanel) {
        addPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;

        addTopicLabel = new JLabel("Add a Customer");
        addTopicLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        addTopicLabel.setForeground(Color.DARK_GRAY);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new Insets(10, 12, 0, 10);
        addPanel.add(addTopicLabel, c);

        firstNameLabel = new JLabel("First Name:");
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.insets = new Insets(10, 12, 0, 10);
        addPanel.add(firstNameLabel, c);

        firstNameTextField = new JTextField();
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.insets = new Insets(0, 10, 10, 10);
        addPanel.add(firstNameTextField, c);

        lastNameLabel = new JLabel("Last Name:");
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        c.insets = new Insets(0, 12, 0, 10);
        addPanel.add(lastNameLabel, c);

        lastNameTextField = new JTextField();
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 2;
        c.insets = new Insets(0, 10, 10, 10);
        addPanel.add(lastNameTextField, c);

        streetLabel = new JLabel("Street:");
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 1;
        c.insets = new Insets(0, 12, 0, 10);
        c.weightx = 1;
        addPanel.add(streetLabel, c);

        streetTextField = new JTextField();
        GridBagConstraints c8 = new GridBagConstraints();
        c8.gridx = 0;
        c8.gridy = 7;
        c8.gridwidth = 1;
        c8.insets = new Insets(0, 10, 10, 10);
        c8.fill = GridBagConstraints.HORIZONTAL;
        addPanel.add(streetTextField, c8);

        houseNoLabel = new JLabel("No:");
        c.gridx = 1;
        c.gridy = 6;
        c.gridwidth = 1;
        c.insets = new Insets(0, 12, 0, 10);
        c.weightx = 0.2;
        addPanel.add(houseNoLabel, c);

        houseNoTextField = new JTextField();
        GridBagConstraints c9 = new GridBagConstraints();
        c9.gridx = 1;
        c9.gridy = 7;
        c9.gridwidth = 1;
        c9.insets = new Insets(0, 10, 10, 10);
        c9.fill = GridBagConstraints.HORIZONTAL;
        addPanel.add(houseNoTextField, c9);

        datePanel = new JPanel(new GridBagLayout());
        datePanel.setBackground(Color.WHITE);
        c.gridx = 0;
        c.gridy = 9;
        c.gridwidth = 2;
        c.insets = new Insets(10, 9, 25, 10);
        addPanel.add(datePanel, c);

        dateOfBirthLabel = new JLabel("Date of Birth:");
        GridBagConstraints c7 = new GridBagConstraints();
        c7.gridx = 0;
        c7.gridy = 0;
        c7.gridwidth = 4;
        c7.anchor = GridBagConstraints.FIRST_LINE_START;
        c7.insets = new Insets(0, 3, 10, 0);
        datePanel.add(dateOfBirthLabel, c7);

        day = new JLabel("Day:");
        GridBagConstraints c1 = new GridBagConstraints();
        c1.gridx = 0;
        c1.gridy = 1;
        datePanel.add(day, c1);

        Integer[] days = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
        dayComboBox = new JComboBox<Integer>(days);
        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridx = 1;
        c2.gridy = 1;
        datePanel.add(dayComboBox, c2);

        month = new JLabel("Month:");
        GridBagConstraints c3 = new GridBagConstraints();
        c3.gridx = 2;
        c3.gridy = 1;
        datePanel.add(month, c3);

        Integer[] months = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        monthComboBox = new JComboBox<Integer>(months);
        GridBagConstraints c4 = new GridBagConstraints();
        c4.gridx = 3;
        c4.gridy = 1;
        datePanel.add(monthComboBox, c4);

        year = new JLabel("Year:");
        GridBagConstraints c5 = new GridBagConstraints();
        c5.gridx = 0;
        c5.gridy = 2;
        c5.insets = new Insets(10, 3, 0, 0);
        datePanel.add(year, c5);

        yearTextField = new JTextField();
        GridBagConstraints c6 = new GridBagConstraints();
        c6.gridx = 1;
        c6.gridy = 2;
        c6.fill = GridBagConstraints.HORIZONTAL;
        c6.insets = new Insets(10, 0, 0, 0);
        datePanel.add(yearTextField, c6);


        genderLabel = new JLabel("Gender:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 10;
        c.gridwidth = 2;
        c.insets = new Insets(0, 12, 5, 10);
        addPanel.add(genderLabel, c);

//		String[] genders = {"female", "male", "diverse"};

        genderComboBox = new JComboBox<>(Gender.values());
//		genderComboBox.setEditable(true);
        c.gridx = 0;
        c.gridy = 11;
        c.gridwidth = 2;
        c.insets = new Insets(0, 10, 20, 10);
        addPanel.add(genderComboBox, c);

        addButton = new DefaultButton("Add Customer");
        c.gridx = 0;
        c.gridy = 12;
        c.gridwidth = 2;
        c.weighty = 1;
        c.insets = new Insets(0, 12, 10, 12);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        addPanel.add(addButton, c);

    }

    public void addComponentsToTablePanel(JPanel tablePanel) {
        tablePanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        String[] columns = {"Object", "Customer ID", "First Name", "Last Name", "Street", "No.", "Gender", "Age", "hashCode"};
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columns);
        table = new JTable(tableModel);
        TableColumnModel tcm = table.getColumnModel();
        tcm.removeColumn(tcm.getColumn(0));
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.setShowGrid(false);
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(new Color(240, 240, 240));

//		table.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 14));
        table.setSelectionBackground(new Color(0x50c443));

        tableTopicLabel = new JLabel("Your current Customers");
        tableTopicLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        tableTopicLabel.setForeground(Color.DARK_GRAY);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.insets = new Insets(10, 12, 10, 10);
        tablePanel.add(tableTopicLabel, c);

        tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBorder(BorderFactory.createEmptyBorder());
        tableScrollPane.getViewport().setBackground(Color.WHITE);
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 10, 10, 10);
        tablePanel.add(tableScrollPane, c);

        printButton = new DefaultButton("Print Details");
        c.gridx = 0;
        c.gridy = 2;
        c.weighty = 0;
        c.weightx = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        c.insets = new Insets(0, 10, 10, 10);
        tablePanel.add(printButton, c);

        removeButton = new DefaultButton("Remove Customer");
        c.gridx = 1;
        c.gridy = 2;
        c.weighty = 0;
        c.weightx = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        c.insets = new Insets(0, 10, 10, 10);
        tablePanel.add(removeButton, c);


    }

    public void addComponentsToHeaderPanel(JPanel headerPanel) {
        GridBagConstraints c = new GridBagConstraints();

        JLabel headerLabelSmall = new JLabel("Pizza Pronto");
        headerLabelSmall.setFont(new Font("Helvetica", Font.PLAIN, 20));
        headerLabelSmall.setForeground(new Color(0x505050));
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.weightx = 1;
        headerPanel.add(headerLabelSmall, c);

        JLabel headerLabelLarge = new JLabel("Customers");
        headerLabelLarge.setFont(new Font("Helvetica", Font.PLAIN, 30));
        headerLabelLarge.setForeground(new Color(0x606060));
        c.gridx = 0;
        c.gridy = 1;
        headerPanel.add(headerLabelLarge, c);
    }

    public CustomerVO getCustomerFromInputs() {
        String lastName = lastNameTextField.getText();
        String firstName = firstNameTextField.getText();
        Gender gender = (Gender) genderComboBox.getSelectedItem();
        String street = streetTextField.getText();

        int houseNumber = 0;

        try {
            houseNumber = Integer.parseInt(getHouseNoTextField().getText());
        } catch (NumberFormatException exception) {
            System.err.println("House number must be an integer." + exception.getMessage());
        }


        int day = (int) dayComboBox.getSelectedItem();
        int month = (int) monthComboBox.getSelectedItem();
        String yearAsString = yearTextField.getText();
        int yearAsInt = 0;

        if (!yearAsString.equals(""))
            yearAsInt = Integer.parseInt(yearAsString);

        LocalDate dob = null;

        if (yearAsInt != 0)
            dob = LocalDate.of(yearAsInt, month, day);

        CustomerVO customer = null;

        customer = new CustomerVO(gender, dob, lastName, firstName, street, houseNumber);

        return customer;
    }

    public void addCustomerToTable(CustomerVO customer) {

        int rowCnt = getTableModel().getRowCount();

        boolean isEqual = false;

        if (customer != null) {

            for (int i = 0; i < rowCnt; i++) {
                if (customer.equals(getTableModel().getValueAt(i, 0)))
                    isEqual = true;
            }

            if (isEqual == true) {
                EventQueue.invokeLater(() -> {
                    JFrame frame = new JFrame("Note");

                    JPanel innerPanel = new JPanel(new GridBagLayout());
                    innerPanel.setOpaque(true);
                    innerPanel.setBackground(Color.WHITE);
                    innerPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

                    GridBagConstraints c0 = new GridBagConstraints();

                    JLabel label = new JLabel("This person is already a customer.");
                    label.setFont(new Font("Arial", Font.PLAIN, 18));
                    label.setForeground(new Color(0x606060));

                    c0.insets = new Insets(20, 20, 20, 20);
                    innerPanel.add(label, c0);

                    JPanel outerPanel = new JPanel(new GridBagLayout());
                    outerPanel.setOpaque(true);
                    outerPanel.setBackground(new Color(0xeaeaea));

                    GridBagConstraints c1 = new GridBagConstraints();
                    c1.insets = new Insets(20, 20, 20, 20);
                    outerPanel.add(innerPanel, c1);

                    frame.add(outerPanel);

                    frame.setLocationRelativeTo(null);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.pack();
                    frame.setVisible(true);
                });

            } else {
                Object[] row = new Object[9];

                row[0] = customer;
                row[1] = customer.getId();
                row[2] = customer.getFirstName();
                row[3] = customer.getLastName();
                row[4] = customer.getStreet();
                row[5] = customer.getHouseNumber();
                row[6] = customer.getGender();

                if (customer.getDateOfBirth() != null) {
                    try {
                        row[7] = customer.calculateAge();
                    } catch (CustomerNoDateOfBirthException ex) {
                        new ExceptionPanel(ex);
                    }
                }

                row[8] = customer.hashCode();

                getTableModel().addRow(row);

                getFirstNameTextField().setText(null);
                getLastNameTextField().setText(null);
                getYearTextField().setText(null);
                getDayComboBox().setSelectedIndex(0);
                getMonthComboBox().setSelectedIndex(0);
                getGenderComboBox().setSelectedIndex(0);
                getStreetTextField().setText(null);
                getHouseNoTextField().setText(null);
            }

        }
    }

    public CustomerVO getSelectedCustomer() {

        int id = (Integer) tableModel.getValueAt(table.getSelectedRow(), 1);
        String firstName =  (String) tableModel.getValueAt(table.getSelectedRow(), 2);
        String lastName =  (String) tableModel.getValueAt(table.getSelectedRow(), 3);
        String street =  (String) tableModel.getValueAt(table.getSelectedRow(), 4);
        int houseNumber =  (Integer) tableModel.getValueAt(table.getSelectedRow(), 5);
        Gender gender = (Gender)tableModel.getValueAt(table.getSelectedRow(), 6);

        return new CustomerVO(id, gender, lastName, firstName, street, houseNumber);
    }



    public void deleteSelectedCustomerRow() {
        tableModel.removeRow(table.getSelectedRow());
    }

    public void deleteAllCustomersFromTable(){
        tableModel.setRowCount(0);
    }

    public void printCustomer(CustomerVO customer){
        EventQueue.invokeLater(() -> new CustomerInfoFrame(customer));
    }
}
