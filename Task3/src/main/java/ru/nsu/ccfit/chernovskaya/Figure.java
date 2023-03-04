package main.java.ru.nsu.ccfit.chernovskaya;

import java.awt.*;
import java.util.Random;

/**
 * Model Class storing information about the structure of game objects, contains enum Tetrominoe
 * <p>Constants: FIGURE_SIZE</p>
 * <p>Private Fields:Point[] coordinates, Point[][] coordsTable, Tetrominoe figureName</p>
 */
public class Figure {
    public static final int FIGURE_SIZE = 4;

    protected enum Tetrominoe {
        Empty, ZShape, SShape, LineShape, TShape, SquareShape, LShape, MirroredLShape
    }

    private final Point[] coordinates;
    private final Tetrominoe figureName;

    Point[][] coordinatesTable = new Point[][]{
            {new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0)},
            {new Point(0, -1), new Point(0, 0), new Point(-1, 0), new Point(-1, 1)},
            {new Point(0, -1), new Point(0, 0), new Point(1, 0), new Point(1, 1)},
            {new Point(0, -1), new Point(0, 0), new Point(0, 1), new Point(0, 2)},
            {new Point(-1, 0), new Point(0, 0), new Point(1, 0), new Point(0, 1)},
            {new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(1, 1)},
            {new Point(-1, -1), new Point(0, -1), new Point(0, 0), new Point(0, 1)},
            {new Point(1, -1), new Point(0, -1), new Point(0, 0), new Point(0, 1)}};

    /**
     * Constructor set coordinate to figure
     * @param tetrominoe - figure name
     */
    public Figure(Tetrominoe tetrominoe) {
        coordinates = new Point[FIGURE_SIZE];
        for (int i = 0; i < FIGURE_SIZE; i++) {
            coordinates[i] = new Point();
            coordinates[i].setLocation(coordinatesTable[tetrominoe.ordinal()][i]);
        }

        this.figureName = tetrominoe;
    }

    /**
     * Create random figure
     * @return instance of the class Figure  with random name
     */
    public static Figure getRandomFigure() {
        Random random = new Random();
        Tetrominoe[] values = Tetrominoe.values();
        return new Figure(values[Math.abs(random.nextInt()) % 7 + 1]);
    }

    /**
     *
     * @return rotated copy of this figure
     */
    public Figure rotateLeft() {
        if (figureName.equals(Tetrominoe.SquareShape)) {
            return this;
        }

        Figure result = new Figure(figureName);
        for (int i = 0; i < FIGURE_SIZE; i++) {
            result.coordinates[i].setLocation(coordinates[i].getY(), -coordinates[i].getX());
        }

        return result;
    }

    /**
     * @return
     */
    public Figure rotateRight() {
        if (figureName.equals(Tetrominoe.SquareShape)) {
            return this;
        }

        Figure result = new Figure(figureName);
        for (int i = 0; i < FIGURE_SIZE; i++) {
            result.coordinates[i].setLocation(-coordinates[i].getY(), coordinates[i].getX());
        }

        return result;
    }

    /**
     *
     * @return
     */
    int getMinY() {
        int minY = (int) coordinates[0].getY();

        for (int i = 1; i < FIGURE_SIZE; i++) {
            minY = Math.min(minY, (int) coordinates[i].getY());
        }

        return minY;
    }

    public Tetrominoe getFigureName() {
        return this.figureName;
    }

    public Point getCoordinate(int index) {
        return coordinates[index];
    }
}
