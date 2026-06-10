package aod.lab5.graph;

import java.awt.Color;

/**
 * Representerar en vertex, alltså en nod, i en tvådimensionell graf.
 * 
 * En vertex innehåller ett info-värde av typen T, två koordinater
 * och en färg. Info-värdet används som information om vertexen och fungerar
 * även som identifierare i grafen.
 *
 * @param <T> typen på informationen som lagras i vertexen
 */
public class Vertex<T> {

    private T info;
    private double x;
    private double y;
    private Color color;

    /**
     * Skapar en ny vertex med angiven position och information.
     * Färgen sätts som standard till svart.
     *
     * @param x x-koordinaten för vertexen
     * @param y y-koordinaten för vertexen
     * @param info informationen som ska lagras i vertexen
     */
    public Vertex(double x, double y, T info) {
        this.x = x;
        this.y = y;
        this.info = info;
        this.color = Color.BLACK;
    }

    /**
     * Returnerar informationen som är lagrad i vertexen.
     *
     * @return vertexens info-värde
     */
    public T getInfo() {
        return info;
    }

    /**
     * Ändrar informationen som är lagrad i vertexen.
     *
     * @param info det nya info-värdet
     */
    public void setInfo(T info) {
        this.info = info;
    }

    /**
     * Returnerar vertexens x-koordinat.
     *
     * @return x-koordinaten
     */
    public double getX() {
        return x;
    }

    /**
     * Ändrar vertexens x-koordinat.
     *
     * @param x den nya x-koordinaten
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Returnerar vertexens y-koordinat.
     *
     * @return y-koordinaten
     */
    public double getY() {
        return y;
    }

    /**
     * Ändrar vertexens y-koordinat.
     *
     * @param y den nya y-koordinaten
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Returnerar vertexens färg.
     *
     * @return vertexens färg
     */
    public Color getColor() {
        return color;
    }

    /**
     * Ändrar vertexens färg.
     *
     * @param color den nya färgen
     */
    public void setColor(Color color) {
        this.color = color;
    }
}
