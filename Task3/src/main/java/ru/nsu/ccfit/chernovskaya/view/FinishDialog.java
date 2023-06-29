package ru.nsu.ccfit.chernovskaya.view;

import ru.nsu.ccfit.chernovskaya.tetris.Tetris;

import javax.swing.*;

/**
 * The dialog box that is displayed at the end of the game.
 * When you click OK, a new game starts (the Tetris class is created anew),
 * when you click on the cross, the StartMenu class opens anew (the StartMenu
 * class is loaded again)
 */
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
