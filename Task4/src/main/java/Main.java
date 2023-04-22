import ru.nsu.ccfit.chernovskaya.GUI.FactoryFrame;
import ru.nsu.ccfit.chernovskaya.GUI.GUI_Controller;
import ru.nsu.ccfit.chernovskaya.factory.AutoFactory;

import javax.swing.*;


public class Main {

    public static void main(String[] args) {

        GUI_Controller gui_controller = new GUI_Controller();
        SwingUtilities.invokeLater(() -> new FactoryFrame(gui_controller));
    }
}
