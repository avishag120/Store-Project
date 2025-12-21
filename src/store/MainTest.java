package store;

import store.gui.view.FrameWindow;
import store.Model.products.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainTest extends JFrame {

    public static void main(String[] args) {

        // ===== יצירת מוצרים (Model) =====
        BookProduct b = new BookProduct(
                "Clean Code",
                99.90,
                10,
                "Software engineering best practices",
                Category.BOOKS,
                Color.BLUE,
                "Robert C. Martin",
                464,
                "images/cleancode.png"
        );

        ElectronicsProduct ep = new ElectronicsProduct(
                "iPhone 15",
                3999.90,
                12,
                "Latest Apple smartphone",
                Category.ELECTRONICS,
                Color.BLACK,
                24,
                "Apple",
                "images/iphone.png"
        );

        ClothingProduct cp = new ClothingProduct(
                "T-Shirt",
                59.90,
                20,
                "Cotton unisex t-shirt",
                Category.CLOTHING,
                Color.RED,
                "M",
                "store/gui/view/resources/images/T-shirt.png"

        );

        List<Product> products = new ArrayList<>();
        products.add(b);
        products.add(ep);
        products.add(cp);

        // ===== הפעלת GUI =====
        SwingUtilities.invokeLater(() -> {
            FrameWindow frame = new FrameWindow();
            frame.setVisible(true);
        });
    }
}
