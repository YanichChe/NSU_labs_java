package ru.nsu.ccfit.chernovskaya;

import javax.swing.*;
import java.awt.*;

/**
 * The window that opens first when the application is loaded.
 * The only Start button starts the game (a Tetris class is created)
 */
public class StartMenu extends JFrame {

    public final static String backgroundPictureName = "/tetris_background.png";
    public final static String gameName = "Tetris";

    public StartMenu(){
        JButton jButton = new JButton();
        Container container = new Container();
        MenuBar menuBar = new MenuBar();

        JPanel contentPane = new JPanel(new GridBagLayout()) {
            public void paintComponent(Graphics g) {
                Image img = Toolkit.getDefaultToolkit().getImage(StartMenu.class.getResource(backgroundPictureName));
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };

        setTitle(gameName);
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
        setJMenuBar(menuBar);
        setContentPane(contentPane);
        setVisible(true);
    }
}
