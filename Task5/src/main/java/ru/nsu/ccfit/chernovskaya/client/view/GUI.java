package ru.nsu.ccfit.chernovskaya.client.view;

import lombok.extern.log4j.Log4j2;
import ru.nsu.ccfit.chernovskaya.message.Message;
import ru.nsu.ccfit.chernovskaya.client.Client;
import ru.nsu.ccfit.chernovskaya.client.observer.Observer;

import javax.swing.*;
import java.awt.BorderLayout;
import java.io.IOException;

@Log4j2
public class GUI extends JFrame implements Observer {

    /** Размер окна. */
    public static final int SIZE = 400;

    private final BottomPanel bottomPanel;
    private final JMenuBar menuBar;
    private final Client client;

    public GUI(final Client client) {
        super();
        this.client = client;

        String nickname = inputNickname();
        String host = inputHost();
        int port = inputPort();

        client.setHost(host);
        client.setPort(port);
        client.setNickname(nickname);
        try {
            client.createSocket();
        }
        catch (IOException | NullPointerException e) {
            log.error(e.getMessage());
            showErrorMessage();
            System.exit(0);
        }
        client.sendMessage(new Message(Message.Type.REQUEST, Message.SubType.LOGIN, nickname));

        ChatPane chatPane = new ChatPane(client);
        bottomPanel = new BottomPanel(client);
        Menu menu = new Menu(client);
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
        String nickname = JOptionPane.showInputDialog("Input your nickname");
        if (nickname.equals(""))  inputNickname();
        return nickname;
    }

    private int inputPort() {
        int port = 0;
        try{
            port = Integer.parseInt(JOptionPane.showInputDialog("Input port"));
        } catch (NumberFormatException e){
            inputPort();
        }

        return port;
    }

    private String inputHost(){
        String host = JOptionPane.showInputDialog("Input host");
        return host;
    }

    private void showErrorMessage(){
        JOptionPane.showMessageDialog(this, "Error Server", "Error",
                JOptionPane.ERROR_MESSAGE);
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
