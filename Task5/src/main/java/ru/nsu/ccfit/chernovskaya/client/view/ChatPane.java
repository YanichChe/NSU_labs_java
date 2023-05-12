package ru.nsu.ccfit.chernovskaya.client.view;

import lombok.extern.log4j.Log4j2;
import ru.nsu.ccfit.chernovskaya.client.Client;
import ru.nsu.ccfit.chernovskaya.observer.Observer;

import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.BadLocationException;
import java.awt.Color;
import java.util.ArrayList;

@Log4j2
public class ChatPane extends JTextPane implements Observer {

    /** Размер шрифта.*/
    public static final int TEXT_SIZE = 13;
    /** Цвет шрифта. */
    public static final Color TEXT_COLOR = Color.BLACK;
    /** Максимальная длина текста. */
    public static final int TEXT_LENGTH = GUI.SIZE / TEXT_SIZE;

    private final Client client;
    private final StyledDocument doc;
    private final SimpleAttributeSet left;
    private final SimpleAttributeSet center;
    private final SimpleAttributeSet right;

    /**
     * В конструкоре создается три части панели.
     * Слева отображаются сообщения других пользователей,
     * справа - сообщения от текущего клиента,
     * по середине - сообщения сервера.
     * @param client клиент
     */
    public ChatPane(final Client client) {
        super();
        this.client = client;
        setEditable(false);

        doc = this.getStyledDocument();

        left = new SimpleAttributeSet();
        StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);
        StyleConstants.setForeground(left, TEXT_COLOR);
        StyleConstants.setFontSize(left, TEXT_SIZE);

        center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        StyleConstants.setForeground(center, TEXT_COLOR);
        StyleConstants.setFontSize(center, TEXT_SIZE);

        right = new SimpleAttributeSet();
        StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);
        StyleConstants.setForeground(right, TEXT_COLOR);
        StyleConstants.setFontSize(right, TEXT_SIZE);
    }

    /**
     * Обновление чата.
     * При отпрвке сообщения кем-либо на
     * сервере(в том числе и сам сервер)
     * вызывается функция update() для обновления чата.
     */
    @Override
    public void update() {
        String message = client.getChat()
                .get(client.getChat().size() - 1) + "\n";
        String nickname = client.getNickname() + ": ";
        try {
            if (!message.contains(":")) {
                doc.setParagraphAttributes(doc.getLength(),
                        1, center, false);
                doc.insertString(doc.getLength(), message, center);
            } else {
                if (message.contains(nickname)) {
                    message = message.replace(nickname, "");
                    doc.setParagraphAttributes(doc.getLength(),
                            1, right, false);
                    printMessage(message, right);

                } else {
                    doc.setParagraphAttributes(doc.getLength(),
                            1, left, false);
                    printMessage(message, left);
                }
            }
        } catch (BadLocationException e) {
            log.error(e.getMessage());
        }

    }

    private ArrayList<String> parseMessage(final String message) {
        String[] arrWords = message.split(" ");
        ArrayList<String> arrPhrases = new ArrayList<>();

        StringBuilder stringBuffer = new StringBuilder();
        int cnt = 0;
        int index = 0;
        int length = arrWords.length;

        while (index != length) {
            if (arrWords[index].length() >= TEXT_LENGTH) {
                int index1 = 0;
                while (index1 < arrWords[index].length()) {
                    arrPhrases.add(arrWords[index].substring(index,
                            Math.min(index + TEXT_LENGTH,
                                    arrWords[index].length() - 1)));
                    index1 += TEXT_LENGTH;
                }
                index++;
            } else if (cnt + arrWords[index].length() <= TEXT_LENGTH) {
                cnt += arrWords[index].length() + 1;
                stringBuffer.append(arrWords[index]).append(" ");
                index++;
            } else {
                arrPhrases.add(stringBuffer.toString());
                stringBuffer = new StringBuilder();
                cnt = 0;
            }

        }

        if (stringBuffer.length() > 0) {
            arrPhrases.add(stringBuffer.toString());
        }
        return arrPhrases;
    }

    private void printMessage(final String message,
                              final SimpleAttributeSet location)
            throws BadLocationException {

        ArrayList<String> messageList = parseMessage(message);
        for (String mess: messageList) {
            doc.insertString(doc.getLength(), mess + "\n", location);
        }
    }
}
