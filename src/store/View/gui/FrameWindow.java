package store.View.gui;
import javax.swing.*;

public class FrameWindow extends JFrame {
    public FrameWindow() {}
    public static void main(String[] args) {
        JFrame frame = new FrameWindow();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);
    }

}
