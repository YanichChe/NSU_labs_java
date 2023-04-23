package ru.nsu.ccfit.chernovskaya.GUI;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;

import static ru.nsu.ccfit.chernovskaya.GUI.FactoryFrame.BACKGROUND_COLOR;
import static ru.nsu.ccfit.chernovskaya.GUI.Slider.SEC;

public class DealerInfoPanel extends JPanel{

    private final String title = "_________Dealer________";

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

        this.setBackground(BACKGROUND_COLOR);


    }
}
