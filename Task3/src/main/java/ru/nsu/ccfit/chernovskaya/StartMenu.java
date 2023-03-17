package ru.nsu.ccfit.chernovskaya;

import javax.swing.*;
import java.awt.*;

public class StartMenu extends JFrame {

    public StartMenu(){
        JButton jButton = new JButton();
        Container container = new Container();

        JPanel contentPane = new JPanel(new GridBagLayout()) {
            public void paintComponent(Graphics g) {
                Image img = Toolkit.getDefaultToolkit().getImage(StartMenu.class.getResource("/tetris_background.png"));
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };

        setTitle("Tetris");
        setSize(400, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        container.setLayout(new FlowLayout( FlowLayout.CENTER, 10, 10));

        jButton.setText("Start");
        jButton.setForeground(Color.WHITE);
        jButton.setOpaque(false);
        jButton.setContentAreaFilled(false);
        jButton.setBorderPainted(false);

        jButton.setFont(new Font("MV Boli", Font.BOLD,getHeight() / 8));
        jButton.addActionListener(e -> {
            dispose();
            new Tetris();
        });

        jButton.setFocusPainted(false);

        container.add(jButton);
        contentPane.add(container);
        setContentPane(contentPane);
        setVisible(true);
    }

}
