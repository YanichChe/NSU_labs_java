package main.java.ru.nsu.ccfit.chernovskaya;

import java.awt.*;

/**
 * Model class
 * <p> Constants: BOARD_WIDTH, BOARD_HEIGHT</p>
 * <p>Private fields: int curX, int curY, boolean isStarted,
 *                     boolean fellStatus, Figure currentFigure,
 *                     Tetrominoe[] boardField</p>
 *
 */
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
        return boardField[(point.y * BOARD_WIDTH) + point.x];
    }

    public void setCurrentFigure(Figure currentFigure) {
        this.currentFigure = currentFigure;
    }

    public Figure getCurrentFigure() {
        return this.currentFigure;
    }

    public boolean isFellStatus() {
        return fellStatus;
    }

    public void setFellStatus(boolean fellStatus) {
        this.fellStatus = fellStatus;
    }

    public int getCurX() {
        return curX;
    }

    public int getCurY() {
        return curY;
    }

    public int getNumberLinesRemoved() {
        return numberLinesRemoved;
    }

    public void addNumberLinesRemoved(int lines) {
        numberLinesRemoved += lines;
    }

    public void setCurX(int curX) {
        this.curX = curX;
    }

    public void setCurY(int curY) {
        this.curY = curY;
    }

    public Figure.Tetrominoe[] getBoardField() {
        return boardField;
    }
}
