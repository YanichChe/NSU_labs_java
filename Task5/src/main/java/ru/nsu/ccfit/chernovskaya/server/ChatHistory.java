package ru.nsu.ccfit.chernovskaya.server;

import lombok.Getter;
import lombok.NonNull;
import ru.nsu.ccfit.chernovskaya.message.Message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, который хранит историю чата. Количество элементов в
 * истории хранится в конфигурционном файле
 * configuration.properties (ChatHistoryCount)
 */
public class ChatHistory{

    @Getter private final List<Message> history;
    private final ConfigParser configParser;

    /**
     * Конструктор класса при создании объекта
     * создает объект класса List<Message> history
     * размером заданным в конфигурционном файле
     * configuration.properties (ChatHistoryCount)
     * и объект класса ConfigParser configParser.
     */
    public ChatHistory(@NonNull final  ConfigParser configParser) {
        this.configParser = configParser;
        this.history = new ArrayList<>(configParser.getChatHistoryCount());
    }

    /**
     * Добавляет новое сообщение от пользователя/сервера в историю чата.
     * Если количество текущих сообщений в истории чата больше или равно
     * максимального значения ChatHistoryCount, заданного в конфигурционном
     * файле, то функция удаляет первое с начала сообщение в истории и
     * добавляет новое(message)
     * @param message - новое сообщение пользователя/сервера.
     */
    public void addMessage(@NonNull final Message message) {
        if (this.history.size() > configParser.getChatHistoryCount()) {
            this.history.remove(0);
        }

        this.history.add(message);
    }
}
