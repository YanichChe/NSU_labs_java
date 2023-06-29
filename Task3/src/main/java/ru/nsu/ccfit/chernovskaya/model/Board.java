package ru.nsu.ccfit.chernovskaya.model;

import java.awt.Point;
import lombok.Getter;
import lombok.Setter;

/**
 * Model class.
 * <p> Constants: BOARD_WIDTH, BOARD_HEIGHT</p>
 * <p>Private fields:  int curX, int curY, boolean isStarted,
 * boolean fellStatus, Figure currentFigure,
 * Tetrominoe[] boardField</p>
 */
@Getter
@Setter
public class Board {
    public static final int BOARD_WIDTH = 10;
    public static final int BOARD_HEIGHT = 20;

    private int numberLinesRemoved = 0;

    /* current element in the board */

    private int curX;
    private int curY;
    private boolean isStarted;
    private boolean fellStatus;
    private Figure currentFigure;
    private final Tetrominoe[] boardField;

    /**
     * Constructor init array boardField and set fellStatus as false.
     */
    public Board() {
        boardField = new Tetrominoe[BOARD_WIDTH * BOARD_HEIGHT];
        fellStatus = false;
    }

    public void setFigure(final int index, final Tetrominoe figure) {
        boardField[index] = figure;
    }

    public Tetrominoe getFigure(final Point point) {
        if (point.y * BOARD_WIDTH + point.x < BOARD_WIDTH * BOARD_HEIGHT - 1) {
            return boardField[(point.y * BOARD_WIDTH) + point.x];
        }
        return Tetrominoe.Empty;
    }

    public void addNumberLinesRemoved(final int lines) {
        numberLinesRemoved += lines;
    }

}
