package store.gui.view;

import store.Model.products.Category;
import store.Model.products.Product;
import store.gui.controler.StoreController;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;

public class StoreWindow extends JPanel {
    private ProductDetailsPanel detailsPanel;
    private StoreController controller;


    public StoreWindow(ProductDetailsPanel detailsPanel) {
        this.detailsPanel = detailsPanel;
        setLayout(new GridLayout(0, 4, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    }
    public void setController(StoreController controller) {
        this.controller = controller;
    }
    public void showProductDetails(Product p,ImageIcon icon) {
        detailsPanel.showProduct(p,icon);
    }
    public void showProducts(List<Product> products) {
        removeAll();

        for (Product p : products) {
            JButton btn = new JButton(p.getDisplayName());
            btn.setToolTipText(p.toString());


            String imagePath = "/images/"+ p.getCategory()+"_"+ p.getDisplayName()+".png";
            URL imageURL = getClass().getResource(imagePath);
            ImageIcon scaledIcon= null;
            if(imageURL != null){
                ImageIcon icon = new ImageIcon(imageURL);
                int imageWidth = 120;
                int imageHeight = 120;
                Image image = icon.getImage().getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
                scaledIcon = new ImageIcon(image);
                btn.setIcon(scaledIcon);

            }
            else {
                System.out.println("Image not found: " + imagePath);
            }
            final ImageIcon scaledIconForListener= scaledIcon;
            btn.addActionListener(e ->
                    controller.productSelected(p,scaledIconForListener)
            );

            add(btn);
        }

        revalidate();
        repaint();
    }




}
