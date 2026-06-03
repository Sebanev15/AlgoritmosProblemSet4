package ucu.edu.aed;

import org.junit.jupiter.api.Test;
import ucu.edu.aed.tda.grafo.impl.DirectedGraph;
import ucu.edu.aed.tda.grafo.impl.DirectedGraphAlgorithms;
import ucu.edu.aed.tda.grafo.model.edge.WeightedEdge;
import ucu.edu.aed.tda.grafo.model.result.IDijkstraResult;
import ucu.edu.aed.tda.grafo.model.result.Path;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class DirectedGraphAlgorithmsTest {
    DirectedGraph<String, Integer> g = new DirectedGraph<>();
    private DirectedGraphAlgorithms algorithms = new DirectedGraphAlgorithms();
    
    @Test
    public void testRecorridoEnProfundidadEmptyGraph() {
        List<String> resultado = new ArrayList<>();
        algorithms.recorridoEnProfundidad(g, g.construirComparable("A"), resultado::add);
        assertTrue(resultado.isEmpty());
    }
    
    
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
    void testObtenerTodosLosCaminosConCiclo() {

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
    void testObtenerTodosLosCaminosVerticeOrigenInexistente() {

        DirectedGraph<String, WeightedEdge> grafo = new DirectedGraph<>();

        grafo.agregarVertice("A");
        grafo.agregarVertice("B");

        List<Path<String>> caminos =algorithms.obtenerTodosLosCaminos(grafo.construirComparable("Z"),grafo.construirComparable("B"),grafo);

        assertTrue(caminos.isEmpty());
    }

    @Test
    void testObtenerTodosLosCaminosVerticeDestinoInexistente() {

        DirectedGraph<String, WeightedEdge> grafo = new DirectedGraph<>();

        grafo.agregarVertice("A");
        grafo.agregarVertice("B");

        List<Path<String>> caminos =algorithms.obtenerTodosLosCaminos(grafo.construirComparable("A"),grafo.construirComparable("Z"),grafo);

        assertTrue(caminos.isEmpty());
    }

    @Test
    void testObtenerTodosLosCaminosSinCamino() {

        DirectedGraph<String, WeightedEdge> grafo = new DirectedGraph<>();

        grafo.agregarVertices(List.of("A", "B", "C"));

        grafo.agregarArista("A", "B", new WeightedEdge(10));

        List<Path<String>> caminos =
                algorithms.obtenerTodosLosCaminos(grafo.construirComparable("A"),grafo.construirComparable("C"),grafo);

        assertTrue(caminos.isEmpty());
    }
    @Test
    void testObtenerTodosLosCaminosMultiplesCaminos() {

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
    void testObtenerTodosLosCaminosUnCamino() {

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

    @Test
    void testAlgoritmoDijkstraVerticeInexistente() {

        DirectedGraph<String, WeightedEdge> grafo = new DirectedGraph<>();

        grafo.agregarVertice("A");
        grafo.agregarVertice("B");

        IDijkstraResult<String> resultado = algorithms.dijkstra(grafo.construirComparable("A"), grafo);

        assertEquals(null, resultado.getPath("Z"));
    }

    @Test
    void testAlgoritmoDijkstraFuncionaCorrectamente() {
        DirectedGraph<String, WeightedEdge> grafo = new DirectedGraph<>();

        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarVertice("C");
        grafo.agregarVertice("D");

        grafo.agregarArista("A", "B", new WeightedEdge(10));
        grafo.agregarArista("A", "C", new WeightedEdge(5));
        grafo.agregarArista("A", "D", new WeightedEdge(35));

        IDijkstraResult<String> resultado = algorithms.dijkstra(grafo.construirComparable("A"), grafo);

        assertEquals(10, resultado.getCost("B"));
        assertEquals(5, resultado.getCost("C"));
        assertEquals(35, resultado.getCost("D"));
    }

    @Test
    void testAlgoritmoDijkstraGuardaMasDeUnCaminoYCostos() {
        DirectedGraph<String, WeightedEdge> grafo = new DirectedGraph<>();

        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarVertice("C");

        grafo.agregarArista("A", "B", new WeightedEdge(10));
        grafo.agregarArista("A", "C", new WeightedEdge(10));

        IDijkstraResult<String> resultado = algorithms.dijkstra(grafo.construirComparable("A"), grafo);

        assertEquals(10, resultado.getCost("B"));
        assertEquals(10, resultado.getCost("C"));

        assertEquals(List.of("A", "B"), resultado.getPath("B"));
        assertEquals(List.of("A", "C"), resultado.getPath("C"));
    }

    @Test
    void testAlgoritmoFloydVerticeInexistenteNoHayCamino() {

        DirectedGraph<String, WeightedEdge> grafo = new DirectedGraph<>();

        grafo.agregarVertice("A");
        grafo.agregarVertice("B");

        // No hay camino entre A y Z
        var camino = algorithms.floyd(grafo).getPath("A", "Z");

        assertEquals(null, camino);
    }

    
    @Test
    void testAlgoritmoFloydNoHayCaminoCostoInfinito() {

        DirectedGraph<String, WeightedEdge> grafo = new DirectedGraph<>();

        grafo.agregarVertice("A");
        grafo.agregarVertice("B");

        // No hay camino entre A y B
        assertEquals(-1, algorithms.floyd(grafo).getCost("A", "B"));
    }

    @Test
    void testAlgoritmoFloydNoHayConeccion() {

        DirectedGraph<String, WeightedEdge> grafo = new DirectedGraph<>();

        grafo.agregarVertice("A");
        grafo.agregarVertice("B");

        // No hay camino entre A y B
        var resultado = algorithms.floyd(grafo);

        assertFalse(resultado.connected("A", "B"));
    }

    @Test
    void testAlgoritmoFloydHayConeccion() {

        DirectedGraph<String, WeightedEdge> grafo = new DirectedGraph<>();

        grafo.agregarVertice("A");
        grafo.agregarVertice("B");

        grafo.agregarArista("A", "B", new WeightedEdge(10));

        // Hay camino entre A y B
        var resultado = algorithms.floyd(grafo);

        assertTrue(resultado.connected("A", "B"));
    }
    
    @Test
    void testAlgoritmoFloydFuncionaCorrectamente() {
        DirectedGraph<String, WeightedEdge> grafo = new DirectedGraph<>();

        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarVertice("C");

        grafo.agregarArista("A", "B", new WeightedEdge(10));
        grafo.agregarArista("B", "C", new WeightedEdge(20));
        grafo.agregarArista("A", "C", new WeightedEdge(50));

        var resultado = algorithms.floyd(grafo);

        assertEquals(30, resultado.getCost("A", "C"));
        assertEquals(List.of("A", "B", "C"), resultado.getPath("A", "C"));
    }

    @Test
    void testAlgoritmoFloydGuardaMasDeUnCaminoYCostos() {
        DirectedGraph<String, WeightedEdge> grafo = new DirectedGraph<>();

        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarVertice("C");

        grafo.agregarArista("A", "B", new WeightedEdge(10));
        grafo.agregarArista("B", "C", new WeightedEdge(20));
        grafo.agregarArista("A", "C", new WeightedEdge(50));

        var resultado = algorithms.floyd(grafo);

        assertEquals(30, resultado.getCost("A", "C"));
        assertEquals(List.of("A", "B", "C"), resultado.getPath("A", "C"));
    }

    @Test
    void testAlgoritmoFloydCaminoDeUnSoloVertice() {
        DirectedGraph<String, WeightedEdge> grafo = new DirectedGraph<>();

        grafo.agregarVertice("A");

        var resultado = algorithms.floyd(grafo);

        assertEquals(0, resultado.getCost("A", "A"));
        assertEquals(List.of("A"), resultado.getPath("A", "A"));
    }

    @Test
    void testAlgoritmoWarshallNoHayCamino() {
        DirectedGraph<String, WeightedEdge> grafo = new DirectedGraph<>();

        grafo.agregarVertice("A");
        grafo.agregarVertice("B");

        var resultado = algorithms.warshall(grafo);

        assertFalse(resultado.connected("A", "B"));
    }

    @Test
    void testAlgoritmoWarshallHayCamino() {
        DirectedGraph<String, WeightedEdge> grafo = new DirectedGraph<>();

        grafo.agregarVertice("A");
        grafo.agregarVertice("B");

        grafo.agregarArista("A", "B", new WeightedEdge(10));

        var resultado = algorithms.warshall(grafo);

        assertTrue(resultado.connected("A", "B"));
    }

    @Test
    void testAlgoritmoWarshallCaminoConMuchosPredecesores() {
        DirectedGraph<String, WeightedEdge> grafo = new DirectedGraph<>();

        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarVertice("C");
        grafo.agregarVertice("D");

        grafo.agregarArista("A", "B", new WeightedEdge(10));
        grafo.agregarArista("B", "C", new WeightedEdge(20));
        grafo.agregarArista("C", "D", new WeightedEdge(30));

        var resultado = algorithms.warshall(grafo);

        assertTrue(resultado.connected("A", "D"));
    }

    @Test
    void testObtenerCentroGrafoNoHayVertices() {
        DirectedGraph<String, WeightedEdge> grafo = new DirectedGraph<>();

        var resultado = algorithms.obtenerCentroGrafo(grafo);

        assertEquals(null, resultado);
    }

    @Test
    void testObtenerCentroGrafoUnVertice() {
        DirectedGraph<String, WeightedEdge> grafo = new DirectedGraph<>();

        grafo.agregarVertice("A");

        var resultado = algorithms.obtenerCentroGrafo(grafo);

        assertEquals("A", resultado);
    }

    @Test
    void testObtenerCentroGrafoExiste() {
        DirectedGraph<String, WeightedEdge> grafo = new DirectedGraph<>();

        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarVertice("C");

        grafo.agregarArista("A", "B", new WeightedEdge(10));
        grafo.agregarArista("B", "C", new WeightedEdge(20));
        grafo.agregarArista("A", "C", new WeightedEdge(50));

        var resultado = algorithms.obtenerCentroGrafo(grafo);

        assertEquals("A", resultado);
    }

    @Test
    void testObtenerCentroGrafoMultiplesVertices() {
        DirectedGraph<String, WeightedEdge> grafo = new DirectedGraph<>();
        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarVertice("C");
        grafo.agregarVertice("D");
        grafo.agregarVertice("E");
        
        grafo.agregarArista("A", "B", new WeightedEdge(10));
        grafo.agregarArista("B", "C", new WeightedEdge(20));
        grafo.agregarArista("C", "D", new WeightedEdge(30));
        grafo.agregarArista("A", "E", new WeightedEdge(15));
        grafo.agregarArista("E", "D", new WeightedEdge(25));

        assertEquals("A", algorithms.obtenerCentroGrafo(grafo));
    }

    @Test
    void testObtenerCentroGrafoConMultiplesCentros_GuardaPorAntiguedad() {
        DirectedGraph<String, WeightedEdge> grafo = new DirectedGraph<>();
        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarVertice("C");

        grafo.agregarArista("A", "B", new WeightedEdge(10));
        grafo.agregarArista("B", "C", new WeightedEdge(10));
        grafo.agregarArista("A", "C", new WeightedEdge(10));

        assertEquals("A", algorithms.obtenerCentroGrafo(grafo));
    }
}