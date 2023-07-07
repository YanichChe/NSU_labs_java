package ru.nsu.ccfit.chernovskaya.client.view;

import lombok.extern.log4j.Log4j2;
import ru.nsu.ccfit.chernovskaya.message.Message;
import ru.nsu.ccfit.chernovskaya.client.Client;

import javax.imageio.ImageIO;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import java.io.IOException;
import java.util.Objects;
import java.awt.Color;

@Log4j2
public class Menu extends JMenu {

    /** Фон верхнего меню.*/
    public static final Color BACKGROUND_COLOR = new Color(137, 103, 240);

    /** Название меню. */
    public static final String NAME_MENU = "Options";

    /** Название файла иконки для пункта меню. */
    public static final String MENU_ICON = "images/list.png";

    /**
     * Создание верхнего меню с одной кнопки,
     * для вывода списка активных пользователей.
     * @param client клиент
     */
    public Menu(final Client client) {
        super(NAME_MENU);
        JMenuItem getClientsList = null;
        try {
            getClientsList = new JMenuItem("Get clients list",
                    new ImageIcon(ImageIO.read(Objects.requireNonNull(
                            Menu.class.getClassLoader()
                                    .getResourceAsStream(MENU_ICON)))));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        this.add(getClientsList);
        this.setBackground(BACKGROUND_COLOR);
        assert getClientsList != null;
        getClientsList.addActionListener(
                e -> client.sendMessage(new Message(Message.Type.REQUEST,
                        Message.SubType.USER_LIST, client.getNickname())));

    }
}
