package ru.nsu.ccfit.chernovskaya.server;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import ru.nsu.ccfit.chernovskaya.message.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.List;


@Log4j2
@Getter
public class Client {

    /**
     * Максимальное ожидание чтения из сокета.
     */
    public static final int MAX_TIME_WAIT = 1000;

    private final Server server;
    private Socket socket;
    @Setter
    private String name;

    private ObjectInputStream clientInStream;
    private ObjectOutputStream clientOutStream;

    /**
     * Конструктор внутри создает объекты класса
     * ObjectInputStream clientInStream
     * и ObjectOutputStream clientOutStream. Устанавливает значение
     * Timeout для сокета.
     *
     * @param socket - сокет создающийся при подключении к серверу
     * @param server - сервер,к которому осуществляется подключение
     */
    public Client(@NonNull final Socket socket, @NonNull final Server server) {
        this.server = server;

        try {
            clientInStream = new ObjectInputStream(socket.getInputStream());
            clientOutStream = new ObjectOutputStream(socket.getOutputStream());
            this.socket = socket;
            this.socket.setSoTimeout(MAX_TIME_WAIT);
        }
        catch (IOException e) {
            log.error(e.getMessage());
        }
        getClientName();
    }


    /**
     * Синхронное чтение сообщения с сокета.
     * При получении сообщения устанавливается значение
     * name, которое считывается из структуры сообщения.
     *
     * @return message сообщение, которое принял клиент.
     */
    public synchronized Message receiveMessage() {
        Message message;
        try {
            message = (Message) clientInStream.readObject();
            this.name = message.getNickname();
        }
        catch (ClassNotFoundException | IOException e) {
            message = null;
        }

        return message;
    }

    /**
     * Функция записывает параметр message в ObjectOutputStream clientOutStream.
     *
     * @param message - входящие сообщение от пользователя/сервера
     */
    public synchronized void sendMessage(@NonNull final Message message) {
        try {
            clientOutStream.writeObject(message);
            clientOutStream.flush();
        }
        catch (IOException e) {
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

        for (Message message : chat) {
            sendMessage(message);
        }

    }

    private void getClientName() {
        String name = null;
        while (name == null) {
            try {
                name = ((Message) clientInStream.readObject()).getNickname();
            }
            catch (IOException | ClassNotFoundException ignored) {
            }
            this.name = name;
        }
    }
}
