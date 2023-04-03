package ru.nsu.ccfit.chernovskaya;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

/**
 * Model class
 * <p> Constants: BOARD_WIDTH, BOARD_HEIGHT</p>
 * <p>Private fields: int curX, int curY, boolean isStarted,
 *                     boolean fellStatus, Figure currentFigure,
 *                     Tetrominoe[] boardField</p>
 *
 */
@Getter
@Setter
public class Board {
    public final static int BOARD_WIDTH = 10;
    public final static int BOARD_HEIGHT = 20;

    private int numberLinesRemoved = 0;

    /* current element in the board */

    private int curX;
    private int curY;
    private boolean isStarted;
    private boolean fellStatus;
    private Figure currentFigure;
    private final Figure.Tetrominoe[] boardField;

    //////////////////////////////////

    /**
     * Constructor init array boardField and set fellStatus as false
     */
    public Board() {
        boardField = new Figure.Tetrominoe[BOARD_WIDTH * BOARD_HEIGHT];
        fellStatus = false;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStartedStatus(boolean status) {
        this.isStarted = status;
    }

    public void setFigure(int index, Figure.Tetrominoe figure) {
        boardField[index] = figure;
    }

    public Figure.Tetrominoe getFigure(Point point) {
        if (point.y * BOARD_WIDTH + point.x < BOARD_WIDTH * BOARD_HEIGHT - 1){
            return boardField[(point.y * BOARD_WIDTH) + point.x];
        }
        return Figure.Tetrominoe.Empty;
    }

    public void addNumberLinesRemoved(int lines) {
        numberLinesRemoved += lines;
    }

}
