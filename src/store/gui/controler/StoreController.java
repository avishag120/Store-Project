package store.gui.controler;
import javax.swing.*;
import java.io.File;

import store.Model.products.Product;
import store.gui.view.StoreWindow;

public class StoreController {
    private StoreWindow storeWindow;

    public StoreController(StoreWindow storeWindow) {
        this.storeWindow = storeWindow;
    }
    public void search(String text) {
        if (text == null || text.isEmpty()) {
            storeWindow.showAllProducts();
        } else {
            storeWindow.showSearchResult(text);
        }
    }
    public void load(JFrame frame) {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(frame);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            storeWindow.showLoadMessage(file.getName());
        }
    }

    public void save(JFrame frame) {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showSaveDialog(frame);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            storeWindow.showSaveMessage(file.getName());
        }
    }
    public void productSelected(Product product) {
        storeWindow.showProductDetails(product);
    }
}
