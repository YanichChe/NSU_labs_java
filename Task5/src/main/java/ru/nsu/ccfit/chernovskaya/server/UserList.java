package ru.nsu.ccfit.chernovskaya.server;

import lombok.extern.log4j.Log4j2;
import ru.nsu.ccfit.chernovskaya.common.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/** Класс рабоает со списком активных пользователей на сервере */
@Log4j2
public class UserList {

    private final Map<String, Client> users = new HashMap<>();

    /**
     * Функция добавляет нового пользователя на сервер.
     * Если на сервере есть пользователь с таким же ником,
     * то функция добвляет число в конец ника для
     * обеспечения условия уникальности ника.
     * @param client - клиент, который добавляется на сервер
     */
    public void addUser(final Client client) {
        String nickname = client.getName();
        log.info(nickname + " connected");

        if (this.users.containsKey(nickname)) {
            int i = 1;
            while (this.users.containsKey(nickname)) {
                nickname = nickname + i;
                i++;
            }
        }

        this.users.put(nickname, client);
        log.info("User list have new user " + nickname);
    }

    /**
     * Удаление пользователя из списка активных пользователей.
     * @param nickname - ник пользователя/сервера
     */
    public void deleteUser(final String nickname) {
        this.users.remove(nickname);
        log.info(nickname + " was deleted");
    }


    /**
     * Функция из Map<String, Client> users возвращает
     * список клиентов.
     * @return - список активных пользователей
     */
    public ArrayList<Client> getClientsList() {
        ArrayList<Client> clientsList = new ArrayList<>(
                this.users.entrySet().size());

        for (Map.Entry<String, Client> m : this.users.entrySet()) {
            clientsList.add(m.getValue());
        }

        return clientsList;
    }

    /**
     * Рассылка сообщения всем активным пользователям.
     * @param message - сообщение пользователя/сервера
     */
    public void broadcast(final Message message) {
        for (Client client : users.values()) {
            client.sendMessage(message);
        }
    }
}
