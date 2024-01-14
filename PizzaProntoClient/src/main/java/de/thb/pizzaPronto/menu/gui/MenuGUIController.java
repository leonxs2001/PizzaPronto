package de.thb.pizzaPronto.menu.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;


import de.thb.pizzaPronto.customer.gui.CustomerPanel;
import de.thb.pizzaPronto.exception.FailedRESTCallException;
import de.thb.pizzaPronto.exception.NoAuthenticatedUserException;
import de.thb.pizzaPronto.generalGui.ExceptionPanel;
import de.thb.pizzaPronto.generalGui.MainGUIController;
import de.thb.pizzaPronto.generalGui.MainView;
import de.thb.pizzaPronto.menu.rest.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuGUIController {

    private MainView view;
    private MenuPanel menuPanel;
    private MainGUIController mainGUIController;
    private IMenuRESTController menuRESTController;

    public MenuGUIController(MainView view) {

        setView(view);

        MenuPanel menuPanel = view.getMainPanel().getLayoutPanel().getMenuPanel();

        MenuVO menu = new MenuVO();

        menuPanel.loadMenu(menu);

        JButton addButton = menuPanel.getAddButton();
        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int ingredientCount = menuPanel.getIngredientTableModel().getRowCount();
                int number = Integer.parseInt(menuPanel.getNumberTextField().getText());
                int typeOfPasta = (int) menuPanel.getTypeComboBox().getSelectedItem();
                int size = (int) menuPanel.getSizeComboBox().getSelectedItem();
                float priceAsFloat = 0.0F;
                String name = menuPanel.getNameTextField().getText();
                String[] ingredients = new String[ingredientCount];
                String priceAsString = menuPanel.getPriceTextField().getText().replace(',', '.');
                String typeOfDish = (String) menuPanel.getDishComboBox().getSelectedItem();


                if (!priceAsString.equals("")) {
                    try {
                        priceAsFloat = Float.parseFloat(priceAsString);
                    } catch (NumberFormatException exception) {
                        System.err.println("Input error by price: " + exception.getMessage());
                    }
                }

                for (int i = 0; i < ingredientCount; i++) {
                    ingredients[i] = (String) menuPanel.getIngredientTableModel().getValueAt(i, 0);
                }

                DishVO dish = null;

                //No ingredients, since composite IngredientComponent is not implemented in GUI
                if (typeOfDish.equals("Pasta"))
                    dish = new PastaVO(number, name, priceAsFloat, typeOfPasta);
                else if (typeOfDish.equals("Pizza"))
                    dish = new PizzaVO(number, name, priceAsFloat, size);
                else if (typeOfDish.equals("Dessert"))
                    dish = new DessertVO(number, name, priceAsFloat);

                int rowCnt = menuPanel.getTableModel().getRowCount();

                boolean isEqual = false;

                for (int i = 0; i < rowCnt; i++) {
                    if (dish.equals(menuPanel.getTableModel().getValueAt(i, 5)))
                        isEqual = true;
                }

                if (isEqual == true) {
                    EventQueue.invokeLater(new Runnable() {

                        @Override
                        public void run() {
                            JFrame frame = new JFrame("Note");

                            JPanel innerPanel = new JPanel(new GridBagLayout());
                            innerPanel.setOpaque(true);
                            innerPanel.setBackground(Color.WHITE);
                            innerPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

                            GridBagConstraints c0 = new GridBagConstraints();

                            JLabel label = new JLabel("This dish is already on the menu.");
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
                        }

                    });

                } else {
                    Object[] row = new Object[9];

                    row[0] = dish;
                    row[1] = dish.getNumberOfDish();
                    row[2] = dish.getClass().getSimpleName();
                    row[3] = dish.getName();
//					if(dish instanceof PizzaVO || dish instanceof PastaVO)
                    row[4] = dish.ingredientsToString();
                    if (dish instanceof PizzaVO)
                        row[5] = ((PizzaVO) dish).getSize();
                    if (dish instanceof PastaVO)
                        row[6] = ((PastaVO) dish).getTypeOfPasta();
                    row[7] = dish.getPrice();
                    row[8] = dish.hashCode();


                    menuPanel.getTableModel().addRow(row);

                    menuPanel.getPriceTextField().setText(null);
                    menuPanel.getNameTextField().setText(null);
                    menuPanel.getIngredientTableModel().setRowCount(0);
                }

            }

        });


        JButton removeButton = menuPanel.getRemoveButton();
        removeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int numRows = menuPanel.getTable().getSelectedRows().length;
                for (int i = 0; i < numRows; i++) {

                    menuPanel.getTableModel().removeRow(menuPanel.getTable().getSelectedRow());
                }

            }

        });

        JButton addIngredientButton = menuPanel.getAddIngredientButton();
        addIngredientButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Object[] row = new Object[1];

                row[0] = menuPanel.getIngredientTextField().getText();

                menuPanel.getIngredientTableModel().addRow(row);

                menuPanel.getIngredientTextField().setText(null);

            }

        });

        JButton removeIngredientButton = menuPanel.getRemoveIngredientButton();
        removeIngredientButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int numRows = menuPanel.getIngredientsTable().getSelectedRows().length;
                for (int i = 0; i < numRows; i++) {

                    menuPanel.getIngredientTableModel().removeRow(menuPanel.getIngredientsTable().getSelectedRow());
                }

            }

        });

        JButton printButton = menuPanel.getPrintButton();
        printButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        JFrame frame = new JFrame("Print Details");

                        int numRows = menuPanel.getTable().getSelectedRows().length;

                        StringBuilder sb = new StringBuilder();

                        int[] idx = menuPanel.getTable().getSelectedRows();

                        for (int i = 0; i < numRows; i++) {

                            String s = menuPanel.getTableModel().getValueAt(idx[i], 0).toString();
                            sb.append(s);
                            sb.append("\n");
                        }

                        JPanel innerPanel = new JPanel(new GridBagLayout());
                        innerPanel.setOpaque(true);
                        innerPanel.setBackground(Color.WHITE);
                        innerPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

                        GridBagConstraints c0 = new GridBagConstraints();

                        JLabel label = new JLabel("Printed Dishes:");
                        label.setFont(new Font("Arial", Font.PLAIN, 20));
                        label.setForeground(new Color(0x606060));
                        c0.gridx = 0;
                        c0.gridy = 0;
                        c0.insets = new Insets(20, 20, 10, 20);
                        innerPanel.add(label, c0);

                        JTextArea details = new JTextArea();
                        details.setBackground(Color.WHITE);
                        details.setEditable(false);
                        details.setText(sb.toString());
                        c0.gridx = 0;
                        c0.gridy = 1;
                        c0.insets = new Insets(10, 20, 20, 20);
                        innerPanel.add(details, c0);

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
                    }

                });

            }
        });


//		JButton copyButton = menuPanel.getCopyButton();
//		copyButton.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				int numRows = menuPanel.getTable().getSelectedRows().length;
//				for(int i=0; i<numRows ; i++ ) {
//
//					int[] idx = menuPanel.getTable().getSelectedRows();
//					
//					DishVO org = (DishVO) menuPanel.getTableModel().getValueAt(idx[i], 5);
//					DishVO cpy = (DishVO) org.clone();
//					
//					Object[] row = new Object[6];
//					
//					row[0] = "Pizza";
//					row[1] = cpy.getName();
//					row[2] = Arrays.toString(cpy.getIngredients());
//					row[3] = cpy.getPrice();
//					row[4] = cpy.hashCode();
//					row[5] = cpy;
//					
//					menuPanel.getTableModel().addRow(row);
//				}
//				
//			}
//			
//		});

        JComboBox<String> dishComboBox = menuPanel.getDishComboBox();
        dishComboBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String typeOfDish = (String) menuPanel.getDishComboBox().getSelectedItem();

                if (typeOfDish.equals("Pasta")) {
                    menuPanel.getTypeComboBox().setEnabled(true);
                    menuPanel.getSizeComboBox().setEnabled(false);
                    menuPanel.getIngredientTextField().setEnabled(true);
                    menuPanel.getIngredientsTable().setEnabled(true);
                    menuPanel.getAddIngredientButton().setVisible(true);
                    menuPanel.getRemoveIngredientButton().setVisible(true);


                } else if (typeOfDish.equals("Pizza")) {
                    menuPanel.getSizeComboBox().setEnabled(true);
                    menuPanel.getTypeComboBox().setEnabled(false);
                    menuPanel.getIngredientTextField().setEnabled(true);
                    menuPanel.getIngredientsTable().setEnabled(true);
                    menuPanel.getAddIngredientButton().setEnabled(true);
                    menuPanel.getRemoveIngredientButton().setEnabled(true);


                } else if (typeOfDish.equals("Dessert")) {
                    menuPanel.getSizeComboBox().setEnabled(false);
                    menuPanel.getTypeComboBox().setEnabled(false);
                    menuPanel.getIngredientTextField().setEnabled(false);
                    menuPanel.getIngredientsTable().setEnabled(false);
                    menuPanel.getAddIngredientButton().setEnabled(false);
                    menuPanel.getRemoveIngredientButton().setEnabled(false);

                }


            }

        });

        JButton importButton = menuPanel.getLoadButton();
        importButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser chooser = new JFileChooser("./");

                FileNameExtensionFilter filter = new FileNameExtensionFilter("Menu JSON-File", "json");

                chooser.setFileFilter(filter);

                int returnVal = chooser.showOpenDialog(menuPanel);

                if (returnVal == JFileChooser.APPROVE_OPTION) {

                    String fileName = null;

                    try {
                        fileName = chooser.getSelectedFile().getCanonicalPath();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                    /*MenuImporter mi = new MenuImporter();

                    try {
                        MenuVO menu = mi.readMenu(fileName);
                        loadMenu(menu);
//					} catch (IOException ex) {
//						System.err.println(ex.getMessage());
//					}
                    } catch (IOException | JSONException ex) {
                        new ExceptionPanel(ex);
                    }*/
                }

            }

        });


    }

    public MenuGUIController(MainGUIController mainGUIController, MenuPanel menuPanel) {

        setMainGUIController(mainGUIController);
        setMenuPanel(menuPanel);
        setMenuRESTController(mainGUIController.getMenuRESTController());

        // add dish
        JButton addButton = menuPanel.getAddButton();
        addButton.addActionListener(e -> addDish());

        //delete dish
        JButton removeButton = menuPanel.getRemoveButton();
        removeButton.addActionListener(e -> deleteDish());

        //print details
        JButton printButton = menuPanel.getPrintButton();
        printButton.addActionListener(e -> printDish());

        // reload everytime the component is shown
        menuPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentHidden(java.awt.event.ComponentEvent evt) {
            }

            @Override
            public void componentShown(java.awt.event.ComponentEvent evt) {
                reload();
            }
        });

        // change dish type
        JComboBox<String> dishComboBox = menuPanel.getDishComboBox();
        dishComboBox.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    changecurrentDishType();
                }

        });

        // add ingredient
        JButton addIngredientButton = menuPanel.getAddIngredientButton();
        addIngredientButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Object[] row = new Object[1];

                row[0] = menuPanel.getIngredientTextField().getText();

                menuPanel.getIngredientTableModel().addRow(row);

                menuPanel.getIngredientTextField().setText(null);

            }

        });

        JButton removeIngredientButton = menuPanel.getRemoveIngredientButton();
        removeIngredientButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int numRows = menuPanel.getIngredientsTable().getSelectedRows().length;
                for (int i = 0; i < numRows; i++) {

                    menuPanel.getIngredientTableModel().removeRow(menuPanel.getIngredientsTable().getSelectedRow());
                }

            }

        });

    }




    public void setView(MainView view) {
        this.view = view;
    }

    public void addDish() {
        try{
            DishVO dish = menuPanel.getDishFromInputs();
            menuRESTController.addDish(dish);
            menuPanel.addDishToTable(dish);
        } catch (NullPointerException | FailedRESTCallException | NoAuthenticatedUserException ex) {
            new ExceptionPanel(ex);
        }

    }

    public void deleteDish() {
        DishVO dish = menuPanel.getSelectedDish();
        menuPanel.deleteSelectedDishRow();
        try {
            menuRESTController.deleteDish(dish);
        } catch (NoAuthenticatedUserException | FailedRESTCallException ex) {
            new ExceptionPanel(ex);
        }

    }

    public void reload() {
        menuPanel.deleteAllDishsFromTable();
        try {
            MenuVO menu = menuRESTController.getMenu();
            menuPanel.loadMenu(menu);
        } catch (NoAuthenticatedUserException | FailedRESTCallException ex) {
            new ExceptionPanel(ex);
        }
    }

    public void printDish() {
        DishVO dish = menuPanel.getSelectedDish();
        menuPanel.printDish(dish);

    }

    public void importMenu(InputStream fileInputStream) {
        // not implemented in prototype
    }

    public void exportMenu() {
        // not implemented in prototype
    }

    private void changecurrentDishType() {
        menuPanel.changeCurrentDishType();
    }


}


// GUI menu - jpanel, rest, GUI_MAIN verändern

