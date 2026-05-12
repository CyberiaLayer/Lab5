package aod.lab5.graph;

import java.awt.Color;

/**
 * Representerar en vertex i en 2D graf
 *
 * @param <T>
 */
public class Vertex<T> {
    private T info;
    private double x;
    private double y;
    private Color color;

    public Vertex(double x, double y, T info) {
        this.x = x;
        this.y = y;
        this.info = info;
        this.color = Color.BLACK;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}