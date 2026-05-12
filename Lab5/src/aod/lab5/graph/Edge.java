package aod.lab5.graph;

import java.awt.Color;

/**
 * 
 *
 * @param <T>
 */
public class Edge<T> {
    private Vertex<T> from;
    private Vertex<T> to;
    private double distance;
    private Color color;

    public Edge(Vertex<T> from, Vertex<T> to) {
        this.from = from;
        this.to = to;
        this.distance = calculateDistance(from, to);
        this.color = Color.GRAY;
    }

    private double calculateDistance(Vertex<T> a, Vertex<T> b) {
        double dx = a.getX() - b.getX();
        double dy = a.getY() - b.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    public Vertex<T> getFrom() {
        return from;
    }

    public void setFrom(Vertex<T> from) {
        this.from = from;
        this.distance = calculateDistance(this.from, this.to);
    }

    public Vertex<T> getTo() {
        return to;
    }

    public void setTo(Vertex<T> to) {
        this.to = to;
        this.distance = calculateDistance(this.from, this.to);
    }

    public double getDistance() {
        return distance;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}