package ru.nsu.ccfit.chernovskaya.server;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import ru.nsu.ccfit.chernovskaya.common.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Objects;

/**
 * Класс отвечает за создание отдельных потоков
 * пользователей со стороны сервера для работы
 * приложения в многопользовательском режиме.
 */
@Log4j2
public class Client implements Runnable {

    /** Кодовое сообщение для вывода списка пользователей. */
    public static final String GET_CLIENTS_LIST = "get clients list";

    /** Название сервера. */
    public static final String SERVER_NICKNAME = "Server";

    /** Максимальное ожидание чтения из сокета.*/
    public static final int MAX_TIME_WAIT = 1000;

    /** Перевод из секунд в милисекунды.*/
    public static final long MILL_SEC = 1000L;

    private final Server server;
    private ObjectInputStream clientInStream;
    private ObjectOutputStream clientOutStream;
    @Getter private String name;

    /**
     * Конструктор внутри создает объекты класса
     * ObjectInputStream clientInStream
     * и ObjectOutputStream clientOutStream. Устанавливает значение
     * Timeout для сокета.
     * @param socket - сокет создающийся при подключении к серверу
     * @param server - сервер,к которому осуществляется подключение
     */
    public Client(final Socket socket, final Server server) {
        this.server = server;

        try {
            clientInStream = new ObjectInputStream(socket.getInputStream());
            clientOutStream = new ObjectOutputStream(socket.getOutputStream());
            socket.setSoTimeout(MAX_TIME_WAIT);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        getClientName();
    }

    /**
     * При регистрации нового пользователя в чат
     * всем пользователям отпрвляется сообщение, уведомляющее
     * о входе нового участника и о новом количестве участников чата.
     */
    @Override
    public void run() {
        try {
            sendChatHistory();
            server.sendMessageToAll(new Message(
                    SERVER_NICKNAME, "New client connected "));
            server.sendMessageToAll(new Message(
                    SERVER_NICKNAME, "Now connected "
                            + server.getUserList().getClientsList().size()
                            + " users"));

            long start = System.currentTimeMillis();
            while (true) {
                Message message;
                try {
                    message = (Message) clientInStream.readObject();
                    this.name = message.getNickname();
                } catch (SocketException | SocketTimeoutException e) {
                    message = null;
                }

                if (message != null) {

                    if (Objects.equals(message.getMessage(), "session end")) {
                        break;
                    }
                    if (Objects.equals(message.getMessage(),
                            GET_CLIENTS_LIST)) {
                        sendClientsList();
                        continue;
                    }

                    server.getChatHistory().addMessage(message);
                    start = System.currentTimeMillis();
                    server.sendMessageToAll(message);
                }

                long timeout = server.getConfigParser().getTimeout() * MILL_SEC;
                if (System.currentTimeMillis() - start > timeout) {
                    server.sendMessageToAll(new Message(SERVER_NICKNAME, name
                            + " was disconnected for inactivity"));
                    sendMessage(new Message(SERVER_NICKNAME, "session end"));
                    break;
                }
            }
            server.removeClient(this);
        } catch (IOException | ClassNotFoundException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * Функция записывает параметр message в ObjectOutputStream clientOutStream
     * @param message - входящие сообщение от пользователя/сервера
     */
    public void sendMessage(final Message message) {
        try {
            clientOutStream.writeObject(message);
            clientOutStream.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * Функция выводит историю чата определенной длины,
     * заданной в конфигурционном файле
     * (сообщения которые были отправлены другими пользователями/сервером
     * до того, как подключился текущий пользователь).
     */
    public void sendChatHistory() {
        int size = server.getChatHistory().getHistory().size();
        List<Message> chat = server.getChatHistory().getHistory();
        int chatHistoryCount = server.getConfigParser().getChatHistoryCount();

        if (size < chatHistoryCount) {
            for (Message message : chat) {
                sendMessage(message);
            }
        } else {
            for (Message message
                    : chat.subList(size - chatHistoryCount, size)) {
                sendMessage(message);
            }
        }
    }

    private void getClientName() {
        String name = null;
        while (name == null) {
            try {
                name = ((Message) clientInStream.readObject()).getNickname();
            } catch (IOException | ClassNotFoundException ignored) {}
            this.name = name;
        }
    }
    /** Функция выводи список всех текущих пользователей. */
    public void sendClientsList() {
        sendMessage(new Message(SERVER_NICKNAME, "\n"));
        sendMessage(new Message(SERVER_NICKNAME, "Online Users"));
        sendMessage(new Message(SERVER_NICKNAME, "-----------------"));
        sendMessage(new Message(SERVER_NICKNAME, name));
        sendMessage(new Message(SERVER_NICKNAME, "-----------------\n"));
    }
}
