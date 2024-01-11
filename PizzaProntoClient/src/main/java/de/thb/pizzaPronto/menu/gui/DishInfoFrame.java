package de.thb.pizzaPronto.menu.gui;


import de.thb.pizzaPronto.menu.rest.DishVO;

import javax.swing.*;
import java.awt.*;

public class DishInfoFrame extends JFrame {

    public DishInfoFrame(DishVO dish){
        setTitle("Print Details");

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
        details.setText(dish.toString());
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

        add(outerPanel);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
