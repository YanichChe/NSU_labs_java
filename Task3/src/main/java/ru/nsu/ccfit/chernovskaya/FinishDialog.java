package ru.nsu.ccfit.chernovskaya;

import javax.swing.*;

public class FinishDialog {
    public FinishDialog(JFrame parentsFrame){
        int answer = JOptionPane.showOptionDialog(null,
                "Do you want to restart?",
                "GAME OVER",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, null, null);

        if (answer == JOptionPane.OK_OPTION){
            parentsFrame.dispose();
            new Tetris();
        }

        else{
            parentsFrame.dispose();
            new StartMenu();
        }
    }
}
