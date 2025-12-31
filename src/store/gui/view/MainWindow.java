package store.gui.view;

import store.gui.controler.MainController;
import store.gui.controler.StoreController;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    public MainWindow(MainController controller) {

        setTitle("Store - Main Window");
        setSize(300,200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));

        // טקסט עליון
        JLabel title = new JLabel("What role would you like to enter?");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.BOLD, 18));

        mainPanel.add(title);
        mainPanel.add(Box.createVerticalStrut(30));
        JButton customerBtn = new JButton("customer");
        JButton managerBtn = new JButton("manager");

        Dimension btnSize = new Dimension(120,35);
        customerBtn.setMaximumSize(btnSize);
        managerBtn.setMaximumSize(btnSize);

        customerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        managerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        customerBtn.addActionListener(e -> controller.openCustomer());
        managerBtn.addActionListener(e -> controller.openManager());

        mainPanel.add(customerBtn);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(managerBtn);

        add(mainPanel);
    }

}
