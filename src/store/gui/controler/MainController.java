package store.gui.controler;

import store.Model.engine.StoreEngine;
import store.gui.view.FrameWindow;
import store.gui.view.ManagerWindow;

import javax.swing.*;

public class MainController {

    private StoreEngine engine;
    private StoreController storeController;

    public MainController(StoreEngine engine) {
        this.engine = engine;
        this.storeController = new StoreController(null, engine);
    }
    public void openCustomer() {
        SwingUtilities.invokeLater(() -> {
            FrameWindow window = new FrameWindow(engine, storeController);
            window.setVisible(true);
        });
    }
    public void openManager() {
        SwingUtilities.invokeLater(() -> {
            ManagerWindow window = new ManagerWindow(storeController);
            window.setVisible(true);
        });
    }

}

