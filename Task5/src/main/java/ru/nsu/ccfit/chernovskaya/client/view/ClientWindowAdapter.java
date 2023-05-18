package ru.nsu.ccfit.chernovskaya.client.view;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import ru.nsu.ccfit.chernovskaya.Message.Message;
import ru.nsu.ccfit.chernovskaya.client.Client;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import static ru.nsu.ccfit.chernovskaya.server.Client.SERVER_NICKNAME;

@Log4j2
@AllArgsConstructor
public class ClientWindowAdapter extends WindowAdapter {

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
            client.sendMessage(new Message(Message.Type.REQUEST,
                    Message.SubType.NEW_MESSAGE, SERVER_NICKNAME,
                    client.getNickname() + " exited the chat" ));
            client.sendMessage(new Message(Message.Type.REQUEST,
                    Message.SubType.LOGOUT, client.getNickname()));
            client.close();

        } catch (IOException exception) {
            log.error(exception.getMessage());
        }
    }
}
