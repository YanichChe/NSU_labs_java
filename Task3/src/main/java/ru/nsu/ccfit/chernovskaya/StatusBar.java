package ru.nsu.ccfit.chernovskaya;

import javax.swing.*;
import java.awt.*;

/**
 * Status bar, displays the number of points received for this game
 */
public class StatusBar extends JPanel {

    private final JLabel score;
    public StatusBar() {
        score = new JLabel();
        score.setText("Score: 0");
        score.setFont(new Font(null, Font.BOLD, 20));
        setBackground(new Color(120,126,165));
        add(score);
    }

    public void setStatusBarText(String text){
        score.setText(text);
    }
}
