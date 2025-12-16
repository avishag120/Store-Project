package store.gui.view;

import javax.swing.*;
import java.awt.*;

public class StoreWindow extends JPanel {

    public StoreWindow() {
        setLayout(new GridLayout(2,6,10,10));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        // דוגמה זמנית – בלי Model
        add(new JLabel("Product 1"));
        add(new JLabel("Product 2"));
        add(new JLabel("Product 3"));
        add(new JLabel("Product 4"));
        add(new JLabel("Product 5"));
        add(new JLabel("Product 6"));
    }

}
