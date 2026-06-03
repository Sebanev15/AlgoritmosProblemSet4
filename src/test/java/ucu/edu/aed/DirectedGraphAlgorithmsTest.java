package ucu.edu.aed;

import org.junit.jupiter.api.Test;
import ucu.edu.aed.tda.grafo.impl.DirectedGraph;
import ucu.edu.aed.tda.grafo.impl.DirectedGraphAlgorithms;
import ucu.edu.aed.tda.grafo.model.edge.WeightedEdge;
import ucu.edu.aed.tda.grafo.model.result.Path;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;
public class DirectedGraphAlgorithmsTest {
    DirectedGraph<String, Integer> g = new DirectedGraph<>();
    private DirectedGraphAlgorithms algorithms = new DirectedGraphAlgorithms();
    @Test
    public void testRecorridoEnProfundidadOnlyWithOneNode() {
        List<String> resultado = new ArrayList<>();
        g.agregarVertice("A");
        algorithms.recorridoEnProfundidad(g, g.construirComparable("A"), resultado::add);
        assertEquals(1, resultado.size());
        assertTrue(resultado.contains("A"));
    }

    @Test
    public void testRecorridoEnProfundidadWithMultipleNodes() {
        List<String> resultado = new ArrayList<>();
        g.agregarVertice("A");
        g.agregarVertice("B");
        g.agregarVertice("C");
        g.agregarArista("A", "B", 1);
        g.agregarArista("B", "C", 1);

        algorithms.recorridoEnProfundidad(g, g.construirComparable("A"), resultado::add);

        assertEquals(3, resultado.size());
        assertTrue(resultado.contains("A"));
        assertTrue(resultado.contains("B"));
        assertTrue(resultado.contains("C"));
    }

    @Test
    public void testRecorridoEnProfunidadWithMultipleAristToNode(){
        List<String> resultado = new ArrayList<>();
        g.agregarVertice("A");
        g.agregarVertice("B");
        g.agregarVertice("C");
        g.agregarVertice("D");
        g.agregarArista("A", "B", 1);
        g.agregarArista("A", "C", 1);
        g.agregarArista("B", "D", 1);
        g.agregarArista("C", "D", 1);

        algorithms.recorridoEnProfundidad(g, g.construirComparable("A"), resultado::add);

        assertEquals(4, resultado.size());
        assertTrue(resultado.contains("A"));
        assertTrue(resultado.contains("B"));
        assertTrue(resultado.contains("C"));
        assertTrue(resultado.contains("D"));
    }
    @Test
void obtenerTodosLosCaminosConCiclo() {

    DirectedGraph<String, WeightedEdge> grafo = new DirectedGraph<>();

    grafo.agregarVertices(List.of("A", "B", "C", "D"));

    grafo.agregarArista("A", "B", new WeightedEdge(1));
    grafo.agregarArista("B", "C", new WeightedEdge(1));
    grafo.agregarArista("C", "A", new WeightedEdge(1)); // ciclo
    grafo.agregarArista("C", "D", new WeightedEdge(1));

    List<Path<String>> caminos =algorithms.obtenerTodosLosCaminos(grafo.construirComparable("A"),grafo.construirComparable("D"),grafo);

    assertEquals(1, caminos.size());

    assertEquals(
            List.of("A", "B", "C", "D"),
            caminos.get(0).getPath());
    }
    @Test
    void obtenerTodosLosCaminosVerticeInexistente() {

        DirectedGraph<String, WeightedEdge> grafo = new DirectedGraph<>();

        grafo.agregarVertice("A");
        grafo.agregarVertice("B");

        List<Path<String>> caminos =algorithms.obtenerTodosLosCaminos(grafo.construirComparable("A"),grafo.construirComparable("Z"),grafo);

        assertTrue(caminos.isEmpty());
    }
    @Test
    void obtenerTodosLosCaminosSinCamino() {

        DirectedGraph<String, WeightedEdge> grafo = new DirectedGraph<>();

        grafo.agregarVertices(List.of("A", "B", "C"));

        grafo.agregarArista("A", "B", new WeightedEdge(10));

        List<Path<String>> caminos =
                algorithms.obtenerTodosLosCaminos(grafo.construirComparable("A"),grafo.construirComparable("C"),grafo);

        assertTrue(caminos.isEmpty());
    }
    @Test
    void obtenerTodosLosCaminosMultiplesCaminos() {

        DirectedGraph<String, WeightedEdge> grafo = new DirectedGraph<>();

        grafo.agregarVertices(List.of("A", "B", "C", "D"));

        grafo.agregarArista("A", "B", new WeightedEdge(10));
        grafo.agregarArista("A", "C", new WeightedEdge(20));
        grafo.agregarArista("B", "D", new WeightedEdge(30));
        grafo.agregarArista("C", "D", new WeightedEdge(40));

        List<Path<String>> caminos =algorithms.obtenerTodosLosCaminos(grafo.construirComparable("A"),grafo.construirComparable("D"),grafo);

        assertEquals(2, caminos.size());
    }
    @Test
    void obtenerTodosLosCaminosUnCamino() {

        DirectedGraph<String, WeightedEdge> grafo = new DirectedGraph<>();

        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarVertice("C");

        grafo.agregarArista("A", "B", new WeightedEdge(10));
        grafo.agregarArista("B", "C", new WeightedEdge(20));

        List<Path<String>> caminos =algorithms.obtenerTodosLosCaminos(grafo.construirComparable("A"),grafo.construirComparable("C"),
                        grafo);

        assertEquals(1, caminos.size());

        assertEquals(List.of("A", "B", "C"),caminos.get(0).getPath());

        assertEquals(30,caminos.get(0).getCost(),0.001);
    }

}
