package ru.nsu.ccfit.chernovskaya.server;

import lombok.Getter;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import ru.nsu.ccfit.chernovskaya.message.Message;
import ru.nsu.ccfit.chernovskaya.server.job.Job;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/** Серверная часть приложения.*/
@Getter
@Log4j2
public class Server implements Runnable {

    /** Название сервера. */
    public static final String SERVER_NICKNAME = "Server";

    private final ChatHistory chatHistory;
    private final UserList userList = new UserList();

    private ServerSocket serverSocket;
    private final ConfigParser configParser;

    private final Job job = new Job(this);

    /**
     * Конструктор создает сереврный сокет
     * с портом, указанным в конфигурционном файле.
     * @param configParser - класс, который распарсил значения
     *                     в конфигурционном файле
     **/
    public Server(@NonNull final ConfigParser configParser) {
        log.info("Server was created");
        this.configParser = configParser;
        chatHistory = new ChatHistory(configParser);

        try {
            int port = configParser.getPort();
            serverSocket = new ServerSocket(port);
            log.info("Server socket was created with port " + port);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * Сервер находиться в режиме ожидания нового подключения.
     * При подключении запускается новый поток для клиента
     */
    @Override
    public void run() {
        try {
            //noinspection InfiniteLoopStatement
            while (true) {
                Socket socket = serverSocket.accept();
                Client client = new Client(socket, this);
                job.addUser(client);
                log.info("Server got new user " + client.getName());
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
    }

    /**
     * Рассылка сообщения всем активным пользователям.
     * @param message - сообщение пользователя/сервера
     */
    public void broadcast(final Message message) {
        for (Client client : userList.getClientsList()) {
            client.sendMessage(message);
        }
    }

    /** Функция выводит список всех текущих пользователей. */
    public void sendClientsList(@NonNull Client client) {
        client.sendMessage(new Message(Message.Type.REQUEST, Message.SubType.NEW_MESSAGE,
                SERVER_NICKNAME, "\n"));
        client.sendMessage(new Message(Message.Type.REQUEST, Message.SubType.NEW_MESSAGE,
                SERVER_NICKNAME, "Online Users"));
        client.sendMessage(new Message(Message.Type.REQUEST, Message.SubType.NEW_MESSAGE,
                SERVER_NICKNAME, "-----------------"));

        for (int i = 0; i < userList.getClientsList().size(); i++){
            client.sendMessage(new Message(Message.Type.REQUEST, Message.SubType.NEW_MESSAGE,
                    SERVER_NICKNAME,userList.getClientsList().get(i).getName()));
        }

        client.sendMessage(new Message(Message.Type.REQUEST, Message.SubType.NEW_MESSAGE,
                SERVER_NICKNAME, "-----------------\n"));
    }
}
