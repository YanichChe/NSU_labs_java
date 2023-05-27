package ru.nsu.ccfit.chernovskaya.server.job;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import ru.nsu.ccfit.chernovskaya.Message.Message;
import ru.nsu.ccfit.chernovskaya.server.Client;
import ru.nsu.ccfit.chernovskaya.server.Server;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static ru.nsu.ccfit.chernovskaya.server.Client.SERVER_NICKNAME;

@Log4j2
public class Job {

    private static final int CLIENT_COUNT = 2;
    private final ExecutorService clientHandlers =
            Executors.newFixedThreadPool(CLIENT_COUNT);

    private final Server server;

    public Job(final Server server) {
        this.server = server;
    }

    @RequiredArgsConstructor
    private class RegistrationTask implements Runnable {

        private final Client client;

        /**
         * Задача регистрации пользователя в чат.
         * Сервер отправляет новому клиенту историю чата
         * фиксированного размера, а всем остальным (данному клиенту
         * включительно) сообщение о регистрации нового пользователя
         * в чате.
         */
        @Override
        public void run() {
            log.info("new Register Task " + client.getName());

            server.getUserList().addUser(client);
            client.sendChatHistory();
            server.sendMessageToAll(new Message(Message.Type.REQUEST, Message.SubType.NEW_MESSAGE,
                    SERVER_NICKNAME, "New user connected "));
            server.sendMessageToAll(new Message(Message.Type.REQUEST, Message.SubType.NEW_MESSAGE,
                    SERVER_NICKNAME, "Now connected "
                    + server.getUserList().getClientsList().size()
                    + " users"));
            clientHandlers.execute(new RequestHandleTask(client));
        }
    }


    @RequiredArgsConstructor
    private class RequestHandleTask implements Runnable {

        private final Client client;

        /**
         * Задача, обеспечивающая прием сообщений клиентом.
         * После того как задача выполнилась, она запускает
         * новую задачу с такими же параметрами.
         */
        @Override
        public void run() {

            Message message = client.receiveMessage();

            if (message != null) {

                if (Objects.equals(message.getSubType(), Message.SubType.LOGOUT)) {
                    try {
                        server.getUserList().deleteUser(client);
                        log.info("server delete from user list " + client.getName());
                    } catch (IOException e) {
                        log.error(e.getMessage());
                    }
                } else if (Objects.equals(message.getSubType(), Message.SubType.USER_LIST)) {
                    client.sendClientsList();
                } else {
                    server.getChatHistory().addMessage(message);
                    server.sendMessageToAll(message);
                }

            }
            clientHandlers.execute(this);
        }
    }

    /**
     * Функция добавляет новую задачу регистрации
     * нового пользователя в ThreadPool.
     * @param client клиент
     */
    public void addUser(final Client client) {
        clientHandlers.execute(new RegistrationTask(client));
    }
}
