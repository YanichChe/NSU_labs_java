package ru.nsu.ccfit.chernovskaya;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MenuBar extends JMenuBar
{
    public MenuBar(JFrame parentsFrame){
        JMenu menu = new JMenu("Game");

        JMenuItem infoItem = new JMenuItem("Info");
        JMenuItem rulesItem = new JMenuItem("Rules");
        JMenuItem exitItem = new JMenuItem("Exit");

        infoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int answer = JOptionPane.showOptionDialog(null,
                            Files.readString(Paths.get("src/main/resources/info.txt")),
                            "INFO",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null, null, null);
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        File file = new File("rules.txt");
        rulesItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int answer = JOptionPane.showOptionDialog(null,
                            Files.readString(Paths.get("src/main/resources/rules.txt")),
                            "INFO",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null, null, null);
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int answer = JOptionPane.showOptionDialog(null,
                        "Do you want to exit?",
                        "EXIT",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null, null, null);

                if (answer == JOptionPane.OK_OPTION){
                    parentsFrame.dispose();
                }
            }
        });

        menu.add(infoItem);
        menu.add(rulesItem);
        menu.add(exitItem);
        menu.add(new JSeparator());

        this.add(menu);
        this.setBackground(new Color(12, 12, 12, 124));

    }
}
