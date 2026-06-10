package aod.lab5.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Implementerar en tvådimensionell graf bestående av vertexar och kanter.
 *
 * Grafen lagrar vertexar i en HashMap där varje vertex identifieras av ett
 * unikt info-värde av typen T. Kanterna lagras som adjacenslistor
 * där varje vertex har en lista med sina utgående kanter.
 * 
 * Grafen hanterar oriktade kanter genom att internt lagra två riktade
 * Edge-objekt, ett i vardera riktningen. Antalet kanter som returneras
 * av numberOfEdges() räknar däremot varje oriktad kant endast en gång.
 *
 * @param <T> typen på informationen som används som identifierare för vertexar
 */
public class Graph<T> implements GraphInterface<T> {

    /**
     * Antalet vertexar i grafen.
     */
    private int nVertices;

    /**
     * Antalet oriktade kanter i grafen.
     */
    private int nEdges;

    /**
     * En samling med alla vertexar i grafen.
     * Nyckeln är vertexens unika identifierare och värdet är själva
     * Vertex-objektet.
     */
    private HashMap<T, Vertex<T>> vertices;

    /**
     * En samling med grafens kanter lagrade som adjacenslistor.
     * Varje nyckel motsvarar en vertex och värdet är en lista med alla
     * Edge-objekt som utgår från den vertexen.
     */
    private HashMap<T, ArrayList<Edge<T>>> edges;

    /**
     * Skapar en ny tom graf.
     * När grafen skapas finns inga vertexar eller kanter. Både vertexlistan
     * och kantlistan initieras som tomma HashMap-strukturer.
     */
    public Graph() {
        this.nVertices = 0;
        this.nEdges = 0;
        this.vertices = new HashMap<>();
        this.edges = new HashMap<>();
    }

    /**
     * Returnerar en lista med alla vertexar som finns i grafen.
     * 
     * Metoden skapar en ny ArrayList} baserad på värdena i
     * {@code vertices}. Detta gör att anroparen får en lista med grafens
     * vertex-objekt utan att direkt få tillgång till den interna HashMap:en.
     *
     * @return en lista med alla Vertex-objekt i grafen
     */
    @Override
    public List<Vertex<T>> getAllVertices() {
        return new ArrayList<>(vertices.values());
    }

    /**
     * Returnerar alla kanter som utgår från den vertex som identifieras av
     * det angivna info-värdet.
     *
     * Om ingen vertex med det angivna info-värdet finns i grafens kantstruktur
     * returneras en tom lista.
     *
     * @param info identifieraren för den vertex vars kanter ska hämtas
     *
     * @return en lista med alla Edge-objekt som utgår från vertexen,
     *         eller en tom lista om vertexen saknas
     */
    @Override
    public List<Edge<T>> getEdges(T info) {
        if (!edges.containsKey(info)) {
            return new ArrayList<>();
        }
        return edges.get(info);
    }

    /**
     * Lägger till en ny vertex i grafen.
     * 
     * Info-värdet används som unik identifierare. Om grafen redan innehåller
     * en vertex med samma identifierare gör metoden ingenting. På så sätt
     * undviks dubbletter i grafen.
     *
     * @param x x-koordinaten för vertexen
     * @param y y-koordinaten för vertexen
     * @param info informationen och identifieraren som ska lagras i vertexen
     */
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

    /**
     * Lägger till en oriktad kant mellan två vertexar i grafen.
     *
     * Eftersom Edge-objekt är riktade skapas två kanter internt:
     * en från infoA till infoB och en från infoB
     * till infoA.
     * Om någon av vertexarna saknas, om båda identifierarna är samma, eller
     * om kanten redan finns, gör metoden ingenting.
     *
     * @param infoA identifieraren för den första vertexen
     * @param infoB identifieraren för den andra vertexen
     */
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

    /**
     * Kontrollerar om en kant redan finns mellan två vertexar.
     * Eftersom grafen hanterar oriktade kanter räcker det att kontrollera
     * om det finns en kant från infoA till infoB. Om den
     * finns antas även den motsatta riktningen redan finnas.
     *
     * @param infoA identifieraren för startvertexen
     * @param infoB identifieraren för slutvertexen
     *
     * @return {@code true} om kanten redan finns, annars {@code false}
     */
    private boolean edgeExists(T infoA, T infoB) {
        for (Edge<T> edge : edges.get(infoA)) {
            if (edge.getTo().getInfo().equals(infoB)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Tar bort en vertex och alla kanter som är kopplade till den.
     *
     * Om grafen inte innehåller någon vertex med det angivna info-värdet
     * gör metoden ingenting.
     *
     * Metoden tar först reda på hur många oriktade kanter som är kopplade
     * till vertexen. Därefter tas alla inkommande kanter från andra
     * adjacenslistor bort. Slutligen tas vertexen och dess egen kantlista
     * bort från grafens interna strukturer.
     *
     * @param info identifieraren för den vertex som ska tas bort
     */
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

    /**
     * Returnerar antalet oriktade kanter i grafen.
     *
     * Även om varje oriktad kant lagras som två riktade Edge-objekt
     * räknas den endast som en kant i denna metod.
     *
     * @return antalet oriktade kanter i grafen
     */
    @Override
    public int numberOfEdges() {
        return nEdges;
    }

    /**
     * Returnerar antalet vertexar i grafen.
     *
     * @return antalet vertexar i grafen
     */
    @Override
    public int numberOfVertices() {
        return nVertices;
    }
}
