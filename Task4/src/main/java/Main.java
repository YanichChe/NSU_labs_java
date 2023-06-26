import ru.nsu.ccfit.chernovskaya.GUI.FactoryFrame;
import ru.nsu.ccfit.chernovskaya.GUI.GUIController;

import javax.swing.*;


public class Main {

    public static void main(String[] args) {

        GUIController gui_controller = new GUIController();
        SwingUtilities.invokeLater(() -> new FactoryFrame(gui_controller));
    }
}
