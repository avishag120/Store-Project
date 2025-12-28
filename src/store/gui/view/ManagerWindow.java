package store.gui.view;

import javax.swing.*;

public class ManagerWindow extends JFrame {
    public ManagerWindow() {
    setTitle("Store - Manager");
    setSize(400,300);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    add(new JLabel("Manager Window", SwingConstants.CENTER));
}
}
