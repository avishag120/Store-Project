/**
 * Submitted by:
 * Maayan Gueta – ID 327554143
 * Avishag Almakaies – ID 325684678
 */
package store.gui.view;
import store.gui.controler.MainController;
import javax.swing.*;
import java.awt.*;

/**
 * MainWindow is the entry window of the application.
 *
 * It allows the user to choose whether to open the system
 * as a customer or as a manager.
 *
 * This window remains open and can be used to open
 * multiple customer or manager windows.
 */
public class MainWindow extends JFrame {

    /**
     * Constructs the main entry window of the store application.
     *
     * Initializes the GUI components and connects the buttons
     * to the MainController in order to open customer or manager windows.
     *
     * @param controller the main controller responsible for opening new windows
     */
    public MainWindow(MainController controller) {

        setTitle("Store - Main Window");
        setSize(300,200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
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
