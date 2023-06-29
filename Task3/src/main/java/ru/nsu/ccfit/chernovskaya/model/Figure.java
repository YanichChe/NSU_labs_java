package ru.nsu.ccfit.chernovskaya.model;

import java.awt.Point;
import java.util.Random;

/**
 * Model Class storing information about the structure of game
 * objects, contains enum Tetrominoe.
 * <p>Constants: FIGURE_SIZE</p>
 * <p>Private Fields:Point[] coordinates, Point[][] coordsTable,
 * Tetrominoe figureName</p>
 */
public class Figure {
    public static final int FIGURE_SIZE = 4;

    private final Point[] coordinates;
    private final Tetrominoe figureName;

    /**
     * Constructor set coordinate to figure.
     * @param tetrominoe - figure name
     */
    public Figure(final Tetrominoe tetrominoe) {
        coordinates = new Point[FIGURE_SIZE];
        for (int i = 0; i < FIGURE_SIZE; i++) {
            coordinates[i] = new Point();
            coordinates[i].setLocation(tetrominoe.getCoordinates()[i]);
        }

        this.figureName = tetrominoe;
    }

    /**
     * Create random figure.
     * @return instance of the class Figure  with random name
     */
    public static Figure getRandomFigure() {
        Random random = new Random();
        Tetrominoe[] values = Tetrominoe.values();
        return new Figure(values[Math.abs(random.nextInt())
                % (Tetrominoe.TETROMINOE_NUMBER - 1) + 1]);
    }

    /**
     * In the loop, it passes through all the points and calculates
     * the minimum value of y from them.
     * @return min y value
     */
    public int getMinY() {
        int minY = (int) coordinates[0].getY();

        for (int i = 1; i < FIGURE_SIZE; i++) {
            minY = Math.min(minY, (int) coordinates[i].getY());
        }

        return minY;
    }

    public Tetrominoe getFigureName() {
        return this.figureName;
    }

    public Point getCoordinate(final int index) {
        return coordinates[index];
    }
}
