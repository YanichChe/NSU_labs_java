package main.java.ru.nsu.ccfit.chernovskaya;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu extends JFrame {

    private final JPanel jPanel;
    private final JButton jButton;

    public StartMenu(){
        jPanel = new JPanel();
        jButton = new JButton();

        init();
    }

    public void init(){

        setTitle("Tetris");
        setSize(400, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setResizable(false);

        jButton.setText("Start");
        jButton.setFont(new Font(null, Font.BOLD,20));
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Tetris().setVisible(true);
            }
        });
        jButton.setSize(500, 500);

        jPanel.add(jButton, BorderLayout.CENTER);
        add(jPanel);
    }
}
