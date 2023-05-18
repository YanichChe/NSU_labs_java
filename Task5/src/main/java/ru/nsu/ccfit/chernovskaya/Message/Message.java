package ru.nsu.ccfit.chernovskaya.Message;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 * Класс использующийся для сериализации/десериализации
 * сообщений.
 */
@Getter
public class Message implements Serializable {
    private final String nickname;
    private final String message;
    @Setter private String[] users;
    private final Date time;

    public Message(final String nickname, final String message) {
        this.nickname = nickname;
        this.message = message;
        this.time = java.util.Calendar.getInstance().getTime();
    }

    /**
     * @return время отправки сообщений.
     */
    public String getDate() {
        Time tm = new Time(this.time.getTime());
        return tm.toString();
    }
}


