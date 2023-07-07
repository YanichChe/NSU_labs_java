package ru.nsu.ccfit.chernovskaya.server;

import lombok.Getter;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import ru.nsu.ccfit.chernovskaya.message.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/** Класс рабоает со списком активных пользователей на сервере */
@Log4j2
public class UserList {

    @Getter private final Map<String, Client> users = new HashMap<>();

    /**
     * Функция добавляет нового пользователя на сервер.
     * Если на сервере есть пользователь с таким же ником,
     * то функция добвляет число в конец ника для
     * обеспечения условия уникальности ника.
     * @param client - клиент, который добавляется на сервер
     */
    public void addUser(@NonNull final Client client) {
        String nickname = client.getName();
        log.info(nickname + " connected");

        if (users.containsKey(nickname)) {
            int i = 1;
            while (users.containsKey(nickname)) {
                nickname = nickname + i;
                i++;
            }
        }

        users.put(nickname, client);
        log.info("User list have new user " + nickname);
    }

    /**
     * Удаление пользователя из списка активных пользователей.
     * @param client клиент
     */
    public void deleteUser(@NonNull final Client client) throws IOException {
        users.remove(client.getName());
        log.info(client.getName() + " was deleted");
    }


    /**
     * Функция из Map<String, Client> users возвращает
     * список клиентов.
     * @return - список активных пользователей
     */
    public ArrayList<Client> getClientsList() {
        ArrayList<Client> clientsList = new ArrayList<>(
                users.entrySet().size());

        for (Map.Entry<String, Client> m : users.entrySet()) {
            clientsList.add(m.getValue());
        }

        return clientsList;
    }
}
