package ru.nsu.ccfit.chernovskaya.Message;

import lombok.Getter;

import java.io.Serializable;

/**
 * Класс использующийся для сериализации/десериализации
 * сообщений.
 */
@Getter
public class Message implements Serializable {



    public enum Type { REQUEST, ANSWER, EVENT }

    public enum SubType { LOGIN, USER_LIST, NEW_MESSAGE,
                          LOGOUT, SUCCESS, ERROR }

    private final Type type;
    private final SubType subType;
    private final String nickname;
    private String message;

    public Message(Type type, SubType subType, String nickname, String message) {
        this.type = type;
        this.subType = subType;
        this.nickname = nickname;
        this.message = message;
    }

    public Message(Type type, SubType subType, String nickname){
        this.type = type;
        this.subType = subType;
        this.nickname = nickname;
    }
}


