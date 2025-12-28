package store.gui.controler;

import store.Model.engine.StoreEngine;
import store.gui.view.FrameWindow;
import store.gui.view.ManagerWindow;
import store.gui.view.ProductDetailsPanel;
import store.gui.view.StoreWindow;

import javax.swing.*;
import java.awt.*;

public class MainController {
    private StoreEngine engine;

    public MainController(StoreEngine engine) {
        this.engine = engine;
    }

    public void openCustomer() {
        new Thread(() -> {
            FrameWindow window = new FrameWindow(engine);
            window.setVisible(true);
        }).start();
    }

    public void openManager() {
        new Thread(() -> {
            new ManagerWindow().setVisible(true);
        }).start();
    }
}
