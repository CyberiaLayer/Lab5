package aod.lab5.graph;

import java.awt.Color;

/**
 * Representerar en riktad kant i en graf.
 *
 * En kant kopplar samman två vertex-objekt och innehåller information om
 * startvertex, slutvertex, avstånd mellan vertexarna samt en färg som kan
 * användas vid visualisering.
 *
 * Distansen beräknas automatiskt med hjälp av den euklidiska distansen
 * mellan vertexarnas koordinater.
 *
 * @param <T> typen på informationen som lagras i vertexarna
 */
public class Edge<T> {

    private Vertex<T> from;
    private Vertex<T> to;
    private double distance;
    private Color color;

    /**
     * Skapar en ny riktad kant mellan två vertexar.
     *
     * Distansen mellan vertexarna beräknas automatiskt och färgen sätts
     * till grå som standard.
     *
     * @param from startvertex
     * @param to slutvertex
     */
    public Edge(Vertex<T> from, Vertex<T> to) {
        this.from = from;
        this.to = to;
        this.distance = calculateDistance(from, to);
        this.color = Color.GRAY;
    }

    /**
     * Beräknar det euklidiska avståndet mellan två vertexar med hjälp av
     * Pythagoras sats.
     *
     * @param a den första vertexen
     * @param b den andra vertexen
     *
     * @return avståndet mellan vertexarna
     */
    private double calculateDistance(Vertex<T> a, Vertex<T> b) {
        double dx = a.getX() - b.getX();
        double dy = a.getY() - b.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Returnerar kantens startvertex.
     *
     * @return startvertexen
     */
    public Vertex<T> getFrom() {
        return from;
    }

    /**
     * Ändrar kantens startvertex.
     * Distansen beräknas om automatiskt.
     *
     * @param from den nya startvertexen
     */
    public void setFrom(Vertex<T> from) {
        this.from = from;
        this.distance = calculateDistance(this.from, this.to);
    }

    /**
     * Returnerar kantens slutvertex.
     *
     * @return slutvertexen
     */
    public Vertex<T> getTo() {
        return to;
    }

    /**
     * Ändrar kantens slutvertex.
     * Distansen beräknas om automatiskt.
     *
     * @param to den nya slutvertexen
     */
    public void setTo(Vertex<T> to) {
        this.to = to;
        this.distance = calculateDistance(this.from, this.to);
    }

    /**
     * Returnerar avståndet mellan kantens två vertexar.
     *
     * @return avståndet mellan vertexarna
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Returnerar kantens färg.
     *
     * @return kantens färg
     */
    public Color getColor() {
        return color;
    }

    /**
     * Ändrar kantens färg.
     *
     * @param color den nya färgen
     */
    public void setColor(Color color) {
        this.color = color;
    }
}
