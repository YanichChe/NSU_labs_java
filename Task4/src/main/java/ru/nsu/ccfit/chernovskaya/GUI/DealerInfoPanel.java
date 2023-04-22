package ru.nsu.ccfit.chernovskaya.GUI;

import lombok.Getter;
import ru.nsu.ccfit.chernovskaya.factory.product.auto.Accessory;
import ru.nsu.ccfit.chernovskaya.factory.product.auto.Body;
import ru.nsu.ccfit.chernovskaya.factory.product.auto.Motor;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;

import static ru.nsu.ccfit.chernovskaya.GUI.Slider.SEC;

public class DealerInfoPanel extends JPanel{

    private final String title = "______________Dealer________";

    private final GUI_Controller controller;

    private final JLabel nameLabel = new JLabel();
    private final GridBagConstraints gridBagConstraints = new GridBagConstraints();

    public DealerInfoPanel(GUI_Controller controller){

        nameLabel.setText(title);

        this.controller = controller;

        Slider timeSlider  = new Slider();

        timeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider)e.getSource();
                if (!source.getValueIsAdjusting()) {
                    int fps = source.getValue();

                    int delay = SEC * fps;
                    controller.setDealerDelay(delay);
                }
            }
        });

        setLayout(new GridBagLayout());

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridy = 1;
        add(timeSlider, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridy = 0;
        add(nameLabel, gridBagConstraints);


    }
}
