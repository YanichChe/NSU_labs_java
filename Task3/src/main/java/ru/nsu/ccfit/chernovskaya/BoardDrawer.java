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
            new Color(0, 0, 0), new Color(204, 102, 102),
            new Color(218, 170, 0), new Color(102, 204, 102),
            new Color(102, 102, 204), new Color(204, 204, 102),
            new Color(204, 102, 204), new Color(102, 204, 204)};

    private final Board board;

    /**
     * Constructor set field Board board
     * @param board model for rendering
     */
    public BoardDrawer(Board board) {
        this.board = board;
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
        g.fillRect(x + 1, y + 1, getSquareWidth() - 2, getSquareHeight() - 2);

        g.setColor(color.brighter());
        g.drawLine(x, y + getSquareHeight() - 1, x, y);
        g.drawLine(x, y, x + getSquareWidth() - 1, y);

        g.setColor(color.darker());
        g.drawLine(x + 1, y + getSquareHeight() - 1, x + getSquareWidth() - 1, y + getSquareHeight() - 1);
        g.drawLine(x + getSquareWidth() - 1, y + getSquareHeight() - 1, x + getSquareWidth() - 1, y + 1);
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
                drawSquare(g, x * getSquareWidth(), boardTop + (Board.BOARD_HEIGHT - y - 1) * getSquareHeight(), board.getCurrentFigure().getFigureName());
            }
        }
    }
}
