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

    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    @Setter private String nickname;
    @Setter private String host;
    @Setter private int port;

    private final List<Message> chat = new ArrayList<>();

    /**
     * создает сокет со стороны клиенты,
     * создает объекты класса ObjectInputStream inputStream
     * и ObjectOutputStream outputStream. Порт для подключения
     * берется из конфигурционного файла.
     */
    public void createSocket() throws IOException, NullPointerException{

        socket = new Socket(host, port);
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
        log.info("Client was created");

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
                if (Objects.equals(message.getSubType(), Message.SubType.LOGOUT)) {
                    close();
                    notifyObservers();
                    log.info(nickname + "session end");
                    break;
                } else {
                    chat.add(message);
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
    public void sendMessage(final Message message) {
        try {
            outputStream.writeObject(message);
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
