package ru.nsu.ccfit.chernovskaya.message;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class Message{

    public enum Type { REQUEST, RESPONSE, EVENT }

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

    public void read(){}

    public void write(){}
}


