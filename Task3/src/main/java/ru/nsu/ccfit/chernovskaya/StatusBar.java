package main.java.ru.nsu.ccfit.chernovskaya;

import javax.swing.*;
import java.awt.*;

public class StatusBar extends JPanel {

    private final JLabel score;
    public StatusBar() {
        score = new JLabel();
        score.setText("Score: 0");
        score.setFont(new Font(null, Font.BOLD, 20));
        add(score);
    }

    public void setStatusBarText(String text){
        score.setText(text);
    }
}
