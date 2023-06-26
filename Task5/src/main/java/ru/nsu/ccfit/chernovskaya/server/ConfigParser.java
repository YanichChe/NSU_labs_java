package ru.nsu.ccfit.chernovskaya.server;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Класс парсит конфигурционный файл и
 * предоставляет другим классам доступ к значениям
 * параметров из файла с помощью @Getter.
 */
@Log4j2
@Getter
public class ConfigParser {

    private final int chatHistoryCount;
    private final int timeout;
    private final int port;

    public static final String FILE_NAME = "configuration.properties";

    /*
    * Конструктор загружает конфигурционный файл по
    * имени и парсит значения.
    */
    public ConfigParser() {
        Properties properties = new Properties();

        try {
            InputStream inputStream = ConfigParser.class.getClassLoader()
                    .getResourceAsStream(FILE_NAME);
            properties.load(inputStream);
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        port = Integer.parseInt(properties.getProperty("Port"));
        chatHistoryCount = Integer.parseInt(
                properties.getProperty("ChatHistoryCount"));
        timeout = Integer.parseInt(
                properties.getProperty("Timeout"));

        log.info("Port - " + port);
        log.info("Chat history count - " + chatHistoryCount);
        log.info("Timeout  -  " + timeout);
    }
}
