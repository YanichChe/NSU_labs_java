package ru.nsu.ccfit.chernovskaya.model;

import java.awt.Point;

public enum Tetrominoe {
    Empty(new Point[]{new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0)}),
    ZShape(new Point[]{
            new Point(0, -1), new Point(0, 0), new Point(-1, 0), new Point(-1, 1)}),
    SShape(new Point[]{
            new Point(0, -1), new Point(0, 0), new Point(1, 0), new Point(1, 1)}),
    LineShape(new Point[]{
            new Point(0, -1), new Point(0, 0), new Point(0, 1), new Point(0, 2)}),
    TShape(new Point[]{
            new Point(-1, 0), new Point(0, 0), new Point(1, 0), new Point(0, 1)}),
    SquareShape(new Point[]{
            new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(1, 1)}),
    LShape(new Point[]{
            new Point(-1, -1), new Point(0, -1), new Point(0, 0), new Point(0, 1)}),
    MirroredLShape(new Point[]{
            new Point(1, -1), new Point(0, -1), new Point(0, 0), new Point(0, 1)});

    Tetrominoe(Point[] coordinates) {
        this.coordinates = coordinates;
    }

    private final Point[] coordinates;

    public static final int TETROMINOE_NUMBER = 8;

    public Point[] getCoordinates() {
        return coordinates;
    }
}
