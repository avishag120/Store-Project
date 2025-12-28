package store;

import store.Model.engine.StoreEngine;
import store.gui.controler.MainController;
import store.gui.view.MainWindow;

import javax.swing.SwingUtilities;

public class MainTest {

    public static void main(String[] args) {

        StoreEngine engine = new StoreEngine();

        MainController controller = new MainController(engine);

        SwingUtilities.invokeLater(() -> {
            new MainWindow(controller).setVisible(true);
        });
    }
}
