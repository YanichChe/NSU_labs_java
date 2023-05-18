package ru.nsu.ccfit.chernovskaya.client;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import ru.nsu.ccfit.chernovskaya.observer.ConcreteObservable;
import ru.nsu.ccfit.chernovskaya.server.ConfigParser;
import ru.nsu.ccfit.chernovskaya.Message.Message;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**Клиентская сторона приложения.*/
@Getter
@Log4j2
public class Client extends ConcreteObservable implements Runnable, Closeable {

    /**Сообщение о конце сессии пользователя.*/
    public static final String SESSION_END_MESSAGE = "session end";

    /** Название хоста сервера, к которому подключается пользователь.*/
    public static final String HOST = "localhost";

    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    @Setter private String nickname;

    private final List<String> chat = new ArrayList<>();

    /**
     * Конструктор создает сокет со стороны клиенты,
     * создает объекты класса ObjectInputStream inputStream
     * и ObjectOutputStream outputStream. Порт для подключения
     * берется из конфигурционного файла.
     */
    public Client() {
        try {
            ConfigParser configParser = new ConfigParser();
            int port = configParser.getPort();
            socket = new Socket(HOST, port);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
            log.info("Client was created");
        } catch (IOException e) {
            log.error(e.getMessage());
        }

    }

    /**
     * Функция оожидает ввода сообщения
     * Пользователя. Если будет получено сообщение
     * session end, то клиент отключается от сервера
     */
    @Override
    public synchronized void run() {
        while (true) {
            try {
                Message message = (Message) inputStream.readObject();
                if (Objects.equals(message.getMessage(), SESSION_END_MESSAGE)) {
                    getOutputStream().close();
                    getInputStream().close();
                    getSocket().close();
                    notifyObservers();
                    log.info(nickname + "session end");
                    break;
                } else {
                    chat.add(message.getMessage());
                    notifyObservers();
                }
            } catch (SocketException e) {
                log.error(e.getMessage());
                break;
            } catch (IOException | ClassNotFoundException e) {
                log.error(e.getMessage());
            }
        }
    }
    /**
     * Отправка сообщения в чат.
     * @param message сообщение введенное пользователем.
     */
    public void sendMessage(final String message) {
        try {
            outputStream.writeObject(new Message(nickname, message));
            outputStream.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void close() throws IOException {
        outputStream.close();
        inputStream.close();
        socket.close();
    }
}
