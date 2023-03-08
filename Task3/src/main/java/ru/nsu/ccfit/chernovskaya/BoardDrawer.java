package main.java.ru.nsu.ccfit.chernovskaya;

import javax.swing.*;
import java.awt.*;

/**
 * Viewer Class paint board and falling figure
 * <p>Constants: Color[] colors - array with colors for all figures</p>
 * <p>Private fields: Board board</p>
 */
public class BoardDrawer extends JPanel {
    public final static Color[] colors = {
            new Color(0, 0, 0), new Color(253, 200, 18),
            new Color(255, 126, 43), new Color(239, 31, 40),
            new Color(37, 175, 75), new Color(64, 67, 207),
            new Color(165, 71, 164), new Color(20, 161, 234)};

    private final Board board;

    /**
     * Constructor set field Board board
     * @param board model for rendering
     */
    public BoardDrawer(Board board) {
        this.board = board;
        setBackground(new Color(0xFFFFFF));
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 4));
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        drawBoard(g);
    }

    private int getSquareWidth() {

        return (int) getSize().getWidth() / Board.BOARD_WIDTH;
    }

    private int getSquareHeight() {

        return (int) getSize().getHeight() / Board.BOARD_HEIGHT;
    }

    private void drawSquare(Graphics g, int x, int y, Figure.Tetrominoe tetrominoe) {
        Color color = colors[tetrominoe.ordinal()];

        g.setColor(color);
        g.fillRect(x, y, getSquareWidth(), getSquareHeight());

        g.setColor(Color.BLACK);
        g.drawLine(x, y + getSquareHeight(), x, y);
        g.drawLine(x, y, x + getSquareWidth(), y);
        g.drawLine(x, y + getSquareHeight(), x + getSquareWidth(), y + getSquareHeight());
        g.drawLine(x + getSquareWidth(), y + getSquareHeight(), x + getSquareWidth(), y);
    }

    /**
     * Rendering board and falling figure
     * @param g  Graphics
     */
    public void drawBoard(Graphics g) {
        Dimension size = getSize();
        int boardTop = (int) size.getHeight() - Board.BOARD_HEIGHT * getSquareHeight();

        for (int i = 0; i < Board.BOARD_HEIGHT; ++i) {
            for (int j = 0; j < Board.BOARD_WIDTH; ++j) {
                Figure.Tetrominoe figure = board.getFigure(new Point(j, Board.BOARD_HEIGHT - i - 1));

                if (figure != Figure.Tetrominoe.Empty) {
                    drawSquare(g, j * getSquareWidth(), boardTop + i * getSquareHeight(), figure);
                }
            }
        }

        if (board.getCurrentFigure().getFigureName() != Figure.Tetrominoe.Empty) {
            for (int i = 0; i < Figure.FIGURE_SIZE; i++) {
                int x = board.getCurX() + board.getCurrentFigure().getCoordinate(i).x;
                int y = board.getCurY() - board.getCurrentFigure().getCoordinate(i).y;
                drawSquare(g, x * getSquareWidth(), boardTop + (Board.BOARD_HEIGHT - y - 1) * getSquareHeight(),
                        board.getCurrentFigure().getFigureName());
            }
        }
    }
}
