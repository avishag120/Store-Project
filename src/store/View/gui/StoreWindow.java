package store.View.gui;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import store.Model.products.Product;
public class StoreWindow {
    private List<Product> products;

    public StoreWindow(List<Product> products) {
       this.products = new ArrayList<>(products);
        GridLayout myGridLayout = new GridLayout(this.products.size()/3, 3);




    }

}
