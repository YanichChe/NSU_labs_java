package ru.nsu.ccfit.chernovskaya.server;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import ru.nsu.ccfit.chernovskaya.common.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/** Серверная часть приложения.*/
@Getter
@Log4j2
public class Server implements Runnable {

    private final ChatHistory chatHistory = new ChatHistory();
    private final UserList userList = new UserList();

    private ServerSocket serverSocket;
    private final ConfigParser configParser;

    /**
     * Конструктор создает сереврный сокет
     * с портом, указанным в конфигурционном файле.
     * @param configParser - класс, который распарсил значения
     *                     в конфигурционном файле
     **/
    public Server(final ConfigParser configParser) {
        log.info("Server was created");
        this.configParser = configParser;

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
                userList.addUser(client);
                new Thread(client).start();
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
     * Удаление клиента с сервера.
     * @param client - клиент
     */
    public void removeClient(final Client client) {
        userList.deleteUser(client.getName());
    }

    /**
     * Рассылка сообщения всем активным пользователям.
     * @param message - сообщение пользователя/сервера
     */
    public void sendMessageToAll(final Message message) {
        userList.broadcast(message);
    }
}
