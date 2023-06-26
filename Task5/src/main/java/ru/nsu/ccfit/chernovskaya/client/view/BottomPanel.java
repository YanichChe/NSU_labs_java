package ru.nsu.ccfit.chernovskaya.client.view;

import ru.nsu.ccfit.chernovskaya.Message.Message;
import ru.nsu.ccfit.chernovskaya.client.Client;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/** Нижняя панель для ввода и отправки сообщений. */
public class BottomPanel extends JPanel {
    /**Надпись на кнопки отправки сообщений.*/
    public static final String BUTTON_SEND_NAME = "Send";
    /** Стандартный текст в поле ввода сообщения.*/
    public static final String TEXT_FIELD_NAME = "Enter your message";
    /**
     * Конструктор создает два объекта панели
     * JButton sendButton и JTextField messageTextField.
     * При нажатии на Enter или на кнопку Send
     * сообщение отправляется в общий чат(при условии что оно
     * не пустое).
     * @param client клиент
     * */
    public BottomPanel(final Client client) {
        super(new BorderLayout());

        JButton sendButton = new JButton(BUTTON_SEND_NAME);
        this.add(sendButton, BorderLayout.EAST);

        JTextField messageTextField = new JTextField(TEXT_FIELD_NAME);
        this.add(messageTextField, BorderLayout.CENTER);

        messageTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(final FocusEvent e) {
                messageTextField.setText("");
            }
        });

        sendButton.addActionListener(e -> {
            if (!messageTextField.getText().trim().isEmpty()) {
                sendMessage(client, messageTextField);
            }
        });

        messageTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    if (!messageTextField.getText().trim().isEmpty()) {
                        sendMessage(client, messageTextField);
                    }
                }
            }
        });
    }
    private void sendMessage(final Client client,
                             final JTextField messageTextField) {

        client.sendMessage(new Message(Message.Type.REQUEST, Message.SubType.NEW_MESSAGE,
                           client.getNickname(), messageTextField.getText()));
        messageTextField.setText("");
        messageTextField.grabFocus();
    }
}
