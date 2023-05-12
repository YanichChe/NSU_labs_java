package ru.nsu.ccfit.chernovskaya.client.view;

import lombok.Getter;
import ru.nsu.ccfit.chernovskaya.client.Client;
import ru.nsu.ccfit.chernovskaya.observer.Observer;

import javax.swing.*;
import java.awt.BorderLayout;

public class GUI extends JFrame implements Observer {
    /** Текст окна-диалога при открытии чата. */
    public static final String INPUT_DIALOG_TEXT = "Enter your nickname";
    /** Размер окна. */
    public static final int SIZE = 400;

    private final Menu menu;
    private final BottomPanel bottomPanel;
    private final ChatPane chatPane;
    private final JMenuBar menuBar;
    private final Client client;

    public GUI(final Client client) {
        super();
        this.client = client;

        String nickname = inputNickname();
        client.setNickname(nickname);
        client.sendMessage(nickname);

        chatPane = new ChatPane(client);
        bottomPanel = new BottomPanel(client);
        menu = new Menu(client);
        menuBar = new JMenuBar();

        JScrollPane scrollPane = new JScrollPane(chatPane);
        scrollPane.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        this.add(scrollPane, BorderLayout.CENTER);

        menuBar.add(menu);
        menuBar.setBackground(Menu.BACKGROUND_COLOR);
        menuBar.setBorder(BorderFactory.createEmptyBorder());

        client.addObserver(chatPane);

        addAll(client);
        setAllSettings(nickname);
    }

    private String inputNickname() {
        return JOptionPane.showInputDialog(INPUT_DIALOG_TEXT);
    }

    private void addAll(final Client client) {
        this.setJMenuBar(menuBar);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.addWindowListener(new ClientWindowAdapter(client));
    }

    private void setAllSettings(final String nickname) {
        this.setTitle(nickname);
        this.setFocusable(true);
        this.setSize(SIZE, SIZE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void update() {
        if (client.getSocket().isClosed()) {
            this.dispose();
        }
    }
}
