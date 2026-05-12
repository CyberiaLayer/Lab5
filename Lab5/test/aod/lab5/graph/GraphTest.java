package aod.lab5.graph;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GraphTest {

    @Test
    public void testAddVerticesAndEdges() {
        Graph<String> graph = new Graph<>();

        graph.addVertex(0, 0, "A");
        graph.addVertex(3, 4, "B");
        graph.addEdge("A", "B");

        assertEquals(2, graph.numberOfVertices());
        assertEquals(1, graph.numberOfEdges());
        assertEquals(1, graph.getEdges("A").size());
        assertEquals(1, graph.getEdges("B").size());
        assertEquals(5.0, graph.getEdges("A").get(0).getDistance(), 0.001);
    }

    @Test
    public void testRemoveVertexAlsoRemovesEdges() {
        Graph<String> graph = new Graph<>();

        graph.addVertex(0, 0, "A");
        graph.addVertex(1, 1, "B");
        graph.addVertex(2, 2, "C");

        graph.addEdge("A", "B");
        graph.addEdge("B", "C");

        graph.remove("B");

        assertEquals(2, graph.numberOfVertices());
        assertEquals(0, graph.numberOfEdges());
        assertEquals(0, graph.getEdges("A").size());
        assertEquals(0, graph.getEdges("C").size());
    }

    @Test
    public void testDuplicateVertexAndDuplicateEdgeAreIgnored() {
        Graph<String> graph = new Graph<>();

        graph.addVertex(0, 0, "A");
        graph.addVertex(1, 1, "A");

        graph.addVertex(2, 2, "B");
        graph.addEdge("A", "B");
        graph.addEdge("A", "B");

        assertEquals(2, graph.numberOfVertices());
        assertEquals(1, graph.numberOfEdges());
    }
}