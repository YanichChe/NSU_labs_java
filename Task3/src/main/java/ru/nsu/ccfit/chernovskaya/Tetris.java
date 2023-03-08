package main.java.ru.nsu.ccfit.chernovskaya;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

public class Tetris extends JFrame {
    public static final int DELAY = 100;
    public static final int PERIOD = 800;

    private final Board board;
    private final BoardController boardController;
    private final BoardDrawer boardDrawer;
    private final StatusBar statusBar;
    private final Timer timer;

    public Tetris() {
        board = new Board();
        boardDrawer = new BoardDrawer(board);
        statusBar = new StatusBar();
        boardController = new BoardController(board, boardDrawer, statusBar);
        timer = new Timer();
    }

    public void init() {
        boardDrawer.setFocusable(true);
        boardDrawer.addKeyListener(new TetrisKeyAdapter());

        add(statusBar, BorderLayout.NORTH);
        add(boardDrawer);

        setTitle("Tetris");
        setSize(400, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public void start() {
        timer.scheduleAtFixedRate(new CurrentTask(), DELAY, PERIOD);
        board.setStartedStatus(true);
        boardController.clearBoard();
        boardController.createNewFigure();
    }

    private void update() {
        if (board.isFellStatus()) {
            board.setFellStatus(false);
            boardController.createNewFigure();
        } else {
            boardController.down();
        }
    }

    class CurrentTask extends TimerTask {
        @Override
        public void run() {
            if (board.isStarted()) {
                update();
                boardDrawer.repaint();
            } else {
                timer.cancel();
                timer.purge();
            }
        }
    }

    class TetrisKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (!board.isStarted())
                return;

            int keycode = e.getKeyCode();
            switch (keycode) {
                case KeyEvent.VK_LEFT -> {
                    if(boardController.ableMove(board.getCurrentFigure(), new Point(board.getCurX() - 1, board.getCurY())))
                        boardController.move(board.getCurrentFigure(), new Point(board.getCurX() - 1, board.getCurY()));
                }
                case KeyEvent.VK_RIGHT -> {
                    if(boardController.ableMove(board.getCurrentFigure(), new Point(board.getCurX() + 1, board.getCurY())))
                        boardController.move(board.getCurrentFigure(), new Point(board.getCurX() + 1, board.getCurY()));
                }
                case KeyEvent.VK_DOWN -> boardController.rotate(BoardController.Rotation.LEFT);
                case KeyEvent.VK_UP -> boardController.rotate(BoardController.Rotation.RIGHT);
                case KeyEvent.VK_SPACE -> boardController.dropDown();
                case KeyEvent.VK_D -> boardController.down();
            }
        }
    }
}
