package store.gui.view;

import javax.swing.*;
import java.awt.*;

public class StoreWindow extends JPanel {

    public StoreWindow() {
        setLayout(new GridLayout(2,6,10,10));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        showAllProducts();

    }
    public void showAllProducts() {
        removeAll();
        // דוגמה זמנית
        add(new JLabel("Product 1"));
        add(new JLabel("Product 2"));
        add(new JLabel("Product 3"));
        add(new JLabel("Product 4"));
        add(new JLabel("Product 5"));
        add(new JLabel("Product 6"));
        revalidate();
        repaint();
    }

    // מציג תוצאת חיפוש דוגמא
    public void showSearchResult(String text) {
        removeAll();
        add(new JLabel("Result: " + text));
        revalidate();
        repaint();
    }
    public void showLoadMessage(String path) {
        removeAll();
        add(new JLabel("Loaded from file:"));
        add(new JLabel(path));
        revalidate();
        repaint();
    }

    public void showSaveMessage(String path) {
        removeAll();
        add(new JLabel("Saved to file:"));
        add(new JLabel(path));
        revalidate();
        repaint();
    }


}
