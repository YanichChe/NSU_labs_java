package ru.nsu.ccfit.chernovskaya.client.view;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import ru.nsu.ccfit.chernovskaya.client.Client;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

@Log4j2
@AllArgsConstructor
public class ClientWindowAdapter extends WindowAdapter {

    /** Сообщение при выходе из чата. */
    public static final String END_MESSAGE = "session end";
    /** Клиент, с которым раюотает окно. */
    private final Client client;

    /**
     * Если пользователь закрыл окно, то он автоматически покидает чат.
     * @param e обработчик закрытия окна
     */
    @Override
    public void windowClosing(final WindowEvent e) {
        super.windowClosing(e);
        try {
            client.sendMessage(client.getNickname() + " exited the chat");
            client.sendMessage(END_MESSAGE);
            closeAll();

        } catch (IOException exception) {
            log.error(exception.getMessage());
        }
    }

    private void closeAll() throws IOException {
        client.getOutputStream().close();
        client.getInputStream().close();
        client.getSocket().close();
    }
}
