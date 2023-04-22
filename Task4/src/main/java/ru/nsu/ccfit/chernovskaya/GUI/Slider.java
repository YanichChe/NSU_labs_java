package ru.nsu.ccfit.chernovskaya.GUI;

import ru.nsu.ccfit.chernovskaya.factory.product.auto.Accessory;
import ru.nsu.ccfit.chernovskaya.factory.product.auto.Body;
import ru.nsu.ccfit.chernovskaya.factory.product.auto.Motor;
import javax.swing.*;
import javax.swing.event.ChangeListener;

public class Slider extends JSlider{
    public final static int SEC = 1000;
    public static final int FPS_MIN = 0;
    public static final int FPS_MAX = 30;
    public static final int FPS_INIT = 15;

    public Slider(){

        super(JSlider.HORIZONTAL,
                FPS_MIN, FPS_MAX, FPS_INIT);

        this.setMajorTickSpacing(10);
        this.setMinorTickSpacing(1);
        this.setPaintTicks(true);
        this.setPaintLabels(true);

    }
}
