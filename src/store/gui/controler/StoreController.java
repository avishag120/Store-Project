package store.gui.controler;
import javax.swing.*;
import java.io.File;
import store.Model.products.Product;
import store.gui.view.StoreWindow;
import store.Model.engine.StoreEngine;

public class StoreController {
    private StoreWindow storeWindow;
    private StoreEngine engine;

    public StoreController(StoreWindow storeWindow) {

        this.storeWindow = storeWindow;
        this.engine = new StoreEngine();
        initProducts();
        storeWindow.showAllProducts(engine.getAllProducts());
    }
    private void initProducts(){
        engine.addProduct(
                new store.Model.products.ClothingProduct(
                        "T-Shirt",
                        100,
                        20,
                        "Blue cotton shirt",
                        store.Model.products.Category.CLOTHING,
                        java.awt.Color.BLUE,
                        "M" ,
                        "images/tshirt.png"
                )
        );
    }
    public void search(String text) {
        if (text == null || text.isEmpty()) {
            storeWindow.showAllProducts(engine.getAllProducts());
        } else {
            storeWindow.showSearchResult(engine.getAllProducts());
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
