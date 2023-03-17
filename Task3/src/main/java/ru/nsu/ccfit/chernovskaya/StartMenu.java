package ru.nsu.ccfit.chernovskaya;

import javax.swing.*;
import java.awt.*;

public class StartMenu extends JFrame {

    public StartMenu(){
        JButton jButton = new JButton();
        Container container = new Container();

        JPanel contentPane = new JPanel() {
            public void paintComponent(Graphics g) {
                Image img = Toolkit.getDefaultToolkit().getImage(StartMenu.class.getResource("/tetris_back.jpg"));
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };

        setTitle("Tetris");
        setSize(400, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setResizable(false);

        container.setLayout(new FlowLayout( FlowLayout.CENTER, 10, 10));

        jButton.setText("Start");
        jButton.setFont(new Font(null, Font.BOLD,20));
        jButton.addActionListener(e -> {
            dispose();
            new Tetris().setVisible(true);
        });

        jButton.setBackground(Color.LIGHT_GRAY);
        container.add(jButton);

        contentPane.add(container);
        setContentPane(contentPane);
        setVisible(true);
    }

}
