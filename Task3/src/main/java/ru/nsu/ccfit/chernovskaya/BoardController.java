package ru.nsu.ccfit.chernovskaya;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.awt.*;
import java.util.Objects;

import static ru.nsu.ccfit.chernovskaya.Board.BOARD_HEIGHT;
import static ru.nsu.ccfit.chernovskaya.Board.BOARD_WIDTH;
import static ru.nsu.ccfit.chernovskaya.Figure.FIGURE_SIZE;

/**
 * Class which manages board and boardDrawer
 * <p>Private fields: Board board, StatusBar statusBar<p/>
 *
 * @since java 16
 */
@Getter
@Setter
@AllArgsConstructor
public final class BoardController {
    @NonNull private final Board board;
    @NonNull private final StatusBar statusBar;

    public Board board() {
        return board;
    }

    public StatusBar statusBar() {
        return statusBar;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        var that = (BoardController) obj;
        return Objects.equals(this.board, that.board) && Objects.equals(this.statusBar, that.statusBar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board, statusBar);
    }

    @Override
    public String toString() {
        return "BoardController[" + "board=" + board + ", " + "statusBar=" + statusBar + ']';
    }

    private static Component component;

    protected enum Rotation {
        LEFT, RIGHT
    }

    public void setComponent(Component component) {
        BoardController.component = component;
    }

    /**
     * Create new instance of class Figure, set coordinates in a center top of the board.
     * If figure can't move down, method set started status as false
     */
    public void createNewFigure() {
        Figure newFigure = Figure.getRandomFigure();
        if (!ableMove(newFigure, new Point(BOARD_WIDTH / 2, BOARD_HEIGHT - 1 + newFigure.getMinY()))) {
            board.setStartedStatus(false);
            return;
        }

        board.setCurrentFigure(newFigure);
        board.setCurX(BOARD_WIDTH / 2);
        board.setCurY(BOARD_HEIGHT - 1 + board.getCurrentFigure().getMinY());
        board.setFellStatus(false);
    }

    /**
     * Set all board's element figure "Empty"
     */
    public void clearBoard() {
        for (int i = 0; i < BOARD_HEIGHT * BOARD_WIDTH; i++) {
            board.setFigure(i, Figure.Tetrominoe.Empty);
        }
    }


    /**
     * Check if figure can move to coordinate point
     *
     * @param figure moving figure
     * @param point  the coordinate to which move shape
     * @return ability move to point
     */
    public boolean ableMove(Figure figure, Point point) {
        for (int i = 0; i < FIGURE_SIZE; ++i) {
            int x = point.x + figure.getCoordinate(i).x;
            int y = point.y - figure.getCoordinate(i).y;

            if (x < 0 || x >= BOARD_WIDTH || y < 0 || y >= BOARD_HEIGHT) {
                return false;
            }

            if (board.getFigure(new Point(x, y)) != Figure.Tetrominoe.Empty) {
                return false;
            }
        }

        return true;
    }

    /**
     * Move figure to the point, if it is possible
     *
     * @param figure moving figure
     * @param point  the coordinate to which move shape
     */
    public void move(Figure figure, Point point) {
        board.setCurX(point.x);
        board.setCurY(point.y);
        board.setCurrentFigure(figure);
        component.repaint();
    }

    /**
     * The function rotates the shape in the specified direction. If, when the figure is rotated, the index
     * of any square of the figure goes beyond the index of the board, then the function moves the figure
     * so that it completely fits on the board
     *
     * @param rotation direction of rotation
     */
    public void rotate(Rotation rotation) {
        if (board.getCurrentFigure().getFigureName().equals(Figure.Tetrominoe.SquareShape)) {
            return;
        }

        Figure result = new Figure(board.getCurrentFigure().getFigureName());
        for (int i = 0; i < FIGURE_SIZE; i++) {

            if (rotation == Rotation.LEFT) {
                result.getCoordinate(i).setLocation(board.getCurrentFigure().getCoordinate(i).getY(), -board.getCurrentFigure().getCoordinate(i).getX());
            }

            if (rotation == Rotation.RIGHT) {
                result.getCoordinate(i).setLocation(-board.getCurrentFigure().getCoordinate(i).getY(), board.getCurrentFigure().getCoordinate(i).getX());
            }

        }

        for (int i = 0; i < FIGURE_SIZE; i++) {
            int x = board.getCurX() + result.getCoordinate(i).x;
            if (x < 0) {
                move(result, new Point(board.getCurX() + 1, board.getCurY()));
            }
            if (x >= BOARD_WIDTH) {
                move(result, new Point(board.getCurX() - 1, board.getCurY()));
            }
        }

        for (int i = 0; i < FIGURE_SIZE; i++) {
            int x = board.getCurX() + result.getCoordinate(i).x;
            int y = board.getCurY() - result.getCoordinate(i).y;

            if (board.getFigure(new Point(x, y)) != Figure.Tetrominoe.Empty)
                return;
        }

        if (!ableMove(result, new Point(board.getCurX(), board.getCurY() - 1)))
            return;
        board.setCurrentFigure(result);
        component.repaint();
    }

    /**
     * Check all lines and if it is full delete it
     */
    public void removeFullLines() {
        int numFullLines = 0;
        for (int i = BOARD_HEIGHT - 1; i >= 0; --i) {
            boolean lineIsFull = true;
            for (int j = 0; j < BOARD_WIDTH; ++j) {
                if (board.getFigure(new Point(j, i)) == Figure.Tetrominoe.Empty) {
                    lineIsFull = false;
                    break;
                }
            }

            if (lineIsFull) {
                numFullLines++;

                for (int k = i; k < BOARD_HEIGHT - 1; k++) {
                    for (int j = 0; j < BOARD_WIDTH; j++) {
                        board.getBoardField()[(k * BOARD_WIDTH) + j] = board.getFigure(new Point(j, k + 1));
                    }
                }
            }
        }

        if (numFullLines > 0) {
            board.addNumberLinesRemoved(numFullLines);
            statusBar.setStatusBarText("Score :" + board.getNumberLinesRemoved() * BOARD_WIDTH);
            component.repaint();
        }
    }

    /**
     * Change current figure's coordinate to one line down if it is possible
     */
    public void down() {
        if (!ableMove(board.getCurrentFigure(), new Point(board.getCurX(), board.getCurY() - 1))) {
            pieceDropped();
        } else {
            move(board.getCurrentFigure(), new Point(board.getCurX(), board.getCurY() - 1));
        }
    }

    /**
     * Lower the figure as low as possible until it touches another figure
     */
    public void dropDown() {
        int newY = board.getCurY();

        while (newY > 0) {
            if (!ableMove(board.getCurrentFigure(), new Point(board.getCurX(), newY - 1))) {
                break;
            } else {
                move(board.getCurrentFigure(), new Point(board.getCurX(), board.getCurY() - 1));
            }

            --newY;
        }

        pieceDropped();
    }

    /**
     * Handling touch with other shapes at the bottom
     * <p>Calls a check to see if any string is now complete
     * Causes the creation of the following shape<p/>
     */
    public void pieceDropped() {
        for (int i = 0; i < FIGURE_SIZE; ++i) {
            int x = board.getCurX() + board.getCurrentFigure().getCoordinate(i).x;
            int y = board.getCurY() - board.getCurrentFigure().getCoordinate(i).y;
            board.getBoardField()[(y * BOARD_WIDTH) + x] = board.getCurrentFigure().getFigureName();
        }

        removeFullLines();
        board.setFellStatus(true);
        createNewFigure();
    }
}