package aod.lab5.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @param <T> type of information used as vertex identifier
 */
public class Graph<T> implements GraphInterface<T> {
    private int nVertices;
    private int nEdges;
    private HashMap<T, Vertex<T>> vertices;
    private HashMap<T, ArrayList<Edge<T>>> edges;

    public Graph() {
        this.nVertices = 0;
        this.nEdges = 0;
        this.vertices = new HashMap<>();
        this.edges = new HashMap<>();
    }

    @Override
    public List<Vertex<T>> getAllVertices() {
        return new ArrayList<>(vertices.values());
    }

    @Override
    public List<Edge<T>> getEdges(T info) {
        if (!edges.containsKey(info)) {
            return new ArrayList<>();
        }
        return edges.get(info);
    }

    @Override
    public void addVertex(double x, double y, T info) {
        if (vertices.containsKey(info)) {
            return;
        }

        Vertex<T> vertex = new Vertex<>(x, y, info);
        vertices.put(info, vertex);
        edges.put(info, new ArrayList<>());
        nVertices++;
    }

    @Override
    public void addEdge(T infoA, T infoB) {
        if (!vertices.containsKey(infoA) || !vertices.containsKey(infoB)) {
            return;
        }

        if (infoA.equals(infoB)) {
            return;
        }

        if (edgeExists(infoA, infoB)) {
            return;
        }

        Vertex<T> vertexA = vertices.get(infoA);
        Vertex<T> vertexB = vertices.get(infoB);

        edges.get(infoA).add(new Edge<>(vertexA, vertexB));
        edges.get(infoB).add(new Edge<>(vertexB, vertexA));

        nEdges++;
    }

    private boolean edgeExists(T infoA, T infoB) {
        for (Edge<T> edge : edges.get(infoA)) {
            if (edge.getTo().getInfo().equals(infoB)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void remove(T info) {
        if (!vertices.containsKey(info)) {
            return;
        }

        int removedEdges = edges.get(info).size();

        for (T key : edges.keySet()) {
            if (!key.equals(info)) {
                edges.get(key).removeIf(edge -> edge.getTo().getInfo().equals(info));
            }
        }

        edges.remove(info);
        vertices.remove(info);

        nVertices--;
        nEdges -= removedEdges;
    }

    @Override
    public int numberOfEdges() {
        return nEdges;
    }

    @Override
    public int numberOfVertices() {
        return nVertices;
    }
}