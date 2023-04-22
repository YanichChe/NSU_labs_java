package ru.nsu.ccfit.chernovskaya.GUI;

import ru.nsu.ccfit.chernovskaya.factory.product.Product;
import ru.nsu.ccfit.chernovskaya.factory.product.auto.Accessory;
import ru.nsu.ccfit.chernovskaya.factory.product.auto.Body;
import ru.nsu.ccfit.chernovskaya.factory.product.auto.Motor;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

import static ru.nsu.ccfit.chernovskaya.GUI.Slider.SEC;

public class DetailInfoPanel extends ProductInfoPanel {

    private final Slider timeSlider;

    protected DetailInfoPanel(GUI_Controller controller, String title, Class<? extends Product> _class) {

        super(controller, title, _class);
        timeSlider = new Slider();
        timeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider)e.getSource();
                if (!source.getValueIsAdjusting()) {
                    int fps = source.getValue();

                    int delay = SEC * fps;
                    if (_class == Accessory.class) controller.setAccessorySupplierDelay(delay);
                    else if (_class == Body.class) controller.setBodySupplierDelay(delay);
                    else if (_class == Motor.class) controller.setMotorSupplierDelay(delay);
                }
            }
        });

        super.getGridBagConstraints().fill = GridBagConstraints.HORIZONTAL;
        super.getGridBagConstraints().gridy = 4;

        add(timeSlider, super.getGridBagConstraints());

    }

}
