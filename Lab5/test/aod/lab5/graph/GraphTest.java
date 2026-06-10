package aod.lab5.graph;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Testklass för Graph.
 * 
 * Klassen testar de publika metoderna i grafimplementationen, bland annat
 * att lägga till vertexar, lägga till kanter, ta bort vertexar, hämta
 * vertexar och kanter samt hantera ogiltiga operationer.
 */
public class GraphTest {

    /**
     * Testar att vertexar och kanter kan läggas till korrekt.
     */
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

    /**
     * Testar att getAllVertices returnerar alla vertexar.
     */
    @Test
    public void testGetAllVertices() {
        Graph<String> graph = new Graph<>();

        graph.addVertex(10, 20, "A");
        graph.addVertex(30, 40, "B");
        graph.addVertex(50, 60, "C");

        assertEquals(3, graph.getAllVertices().size());

        boolean containsA = false;
        boolean containsB = false;
        boolean containsC = false;

        for (Vertex<String> vertex : graph.getAllVertices()) {
            if (vertex.getInfo().equals("A")) {
                containsA = true;
            } else if (vertex.getInfo().equals("B")) {
                containsB = true;
            } else if (vertex.getInfo().equals("C")) {
                containsC = true;
            }
        }

        assertTrue(containsA);
        assertTrue(containsB);
        assertTrue(containsC);
    }

    /**
     * Testar att en oriktad kant lagras som två riktade Edge-objekt.
     */
    @Test
    public void testUndirectedEdgeCreatesTwoDirectedEdges() {
        Graph<String> graph = new Graph<>();

        graph.addVertex(0, 0, "A");
        graph.addVertex(10, 0, "B");
        graph.addEdge("A", "B");

        Edge<String> edgeFromA = graph.getEdges("A").get(0);
        Edge<String> edgeFromB = graph.getEdges("B").get(0);

        assertEquals("A", edgeFromA.getFrom().getInfo());
        assertEquals("B", edgeFromA.getTo().getInfo());

        assertEquals("B", edgeFromB.getFrom().getInfo());
        assertEquals("A", edgeFromB.getTo().getInfo());

        assertEquals(1, graph.numberOfEdges());
        assertEquals(1, graph.getEdges("A").size());
        assertEquals(1, graph.getEdges("B").size());
    }

    /**
     * Testar att getEdges returnerar en tom lista om vertexen inte finns.
     */
    @Test
    public void testGetEdgesForMissingVertexReturnsEmptyList() {
        Graph<String> graph = new Graph<>();

        graph.addVertex(0, 0, "A");

        assertTrue(graph.getEdges("B").isEmpty());
    }

    /**
     * Testar att dubbletter av vertexar och kanter ignoreras.
     */
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
        assertEquals(1, graph.getEdges("A").size());
        assertEquals(1, graph.getEdges("B").size());
    }

    /**
     * Testar att addEdge inte gör något om en eller båda vertexarna saknas.
     */
    @Test
    public void testAddEdgeWithMissingVertexDoesNothing() {
        Graph<String> graph = new Graph<>();

        graph.addVertex(0, 0, "A");

        graph.addEdge("A", "B");
        graph.addEdge("C", "A");
        graph.addEdge("X", "Y");

        assertEquals(1, graph.numberOfVertices());
        assertEquals(0, graph.numberOfEdges());
        assertTrue(graph.getEdges("A").isEmpty());
    }

    /**
     * Testar att addEdge inte tillåter en kant från en vertex till sig själv.
     */
    @Test
    public void testAddEdgeFromVertexToItselfDoesNothing() {
        Graph<String> graph = new Graph<>();

        graph.addVertex(0, 0, "A");
        graph.addEdge("A", "A");

        assertEquals(1, graph.numberOfVertices());
        assertEquals(0, graph.numberOfEdges());
        assertTrue(graph.getEdges("A").isEmpty());
    }

    /**
     * Testar att en vertex kan tas bort och att alla kopplade kanter
     * samtidigt tas bort från grafen.
     */
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

    /**
     * Testar att remove inte ändrar grafen om vertexen inte finns.
     */
    @Test
    public void testRemoveMissingVertexDoesNothing() {
        Graph<String> graph = new Graph<>();

        graph.addVertex(0, 0, "A");
        graph.addVertex(1, 1, "B");
        graph.addEdge("A", "B");

        graph.remove("C");

        assertEquals(2, graph.numberOfVertices());
        assertEquals(1, graph.numberOfEdges());
        assertEquals(1, graph.getEdges("A").size());
        assertEquals(1, graph.getEdges("B").size());
    }

    /**
     * Testar att en tom graf har noll vertexar och noll kanter.
     */
    @Test
    public void testEmptyGraphHasZeroVerticesAndEdges() {
        Graph<String> graph = new Graph<>();

        assertEquals(0, graph.numberOfVertices());
        assertEquals(0, graph.numberOfEdges());
        assertTrue(graph.getAllVertices().isEmpty());
    }
    
    /**
     * Testar att en vertex utan kopplade kanter kan tas bort korrekt.
     * 
     * Testet skapar en graf med en ensam vertex och tar sedan bort den.
     * Efter borttagningen ska grafen vara tom och innehålla varken
     * vertexar eller kanter.
     */
    @Test
    public void testRemoveIsolatedVertex() {
        Graph<String> graph = new Graph<>();

        graph.addVertex(0, 0, "A");

        graph.remove("A");

        assertEquals(0, graph.numberOfVertices());
        assertEquals(0, graph.numberOfEdges());
    }
}
