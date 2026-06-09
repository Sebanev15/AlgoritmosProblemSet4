package ucu.edu.aed;
import org.junit.jupiter.api.Test;
import ucu.edu.aed.tda.grafo.impl.UndirectedGraph;
import ucu.edu.aed.tda.grafo.impl.UndirectedGraphAlgorithms;

import static org.junit.jupiter.api.Assertions.*;
public class UndirectedGraphAlgorithmsTest {
    UndirectedGraph<String, Integer> g = new UndirectedGraph<>();
    UndirectedGraphAlgorithms algorithms = new UndirectedGraphAlgorithms();

    @Test
    public void puntosArticulacionInEmptyGraphTest(){
        assertTrue(algorithms.puntosDeArticulacion(g).isEmpty());
    }

    @Test
    public void puntosArticulacionInSingleVertexGraphTest(){
        g.agregarVertice("A");
        assertTrue(algorithms.puntosDeArticulacion(g).isEmpty());
    }

    @Test
    public void puntosArticulacionInTwoVerticesGraphTest(){
        g.agregarVertice("A");
        g.agregarVertice("B");
        assertTrue(algorithms.puntosDeArticulacion(g).isEmpty());
    }

    @Test
    public void puntosArticulacionInThreeVerticesGraphTest() {
        g.agregarVertice("A");
        g.agregarVertice("B");
        g.agregarVertice("C");
        g.agregarArista("A", "B", 1);
        g.agregarArista("B", "C", 1);
        assertEquals(1, algorithms.puntosDeArticulacion(g).size());
        assertTrue(algorithms.puntosDeArticulacion(g).contains("B"));
    }

    @Test
    public void puntosArticulacionInLargeGraphTest() {
        g.agregarVertice("A");
        g.agregarVertice("B");
        g.agregarVertice("C");
        g.agregarVertice("D");
        g.agregarVertice("E");
        g.agregarArista("A", "B", 1);
        g.agregarArista("B", "C", 1);
        g.agregarArista("C", "D", 1);
        g.agregarArista("D", "E", 1);

        assertEquals(3, algorithms.puntosDeArticulacion(g).size());
        assertTrue(algorithms.puntosDeArticulacion(g).contains("B"));
        assertTrue(algorithms.puntosDeArticulacion(g).contains("C"));
        assertTrue(algorithms.puntosDeArticulacion(g).contains("D"));
    }

    @Test
    public void puntosArticulacionInCiclicGraphTest(){
        g.agregarVertice("A");
        g.agregarVertice("B");
        g.agregarVertice("C");
        g.agregarVertice("D");
        g.agregarArista("A", "B", 1);
        g.agregarArista("B", "C", 1);
        g.agregarArista("C", "D", 1);
        g.agregarArista("D", "A", 1);

        assertTrue(algorithms.puntosDeArticulacion(g).isEmpty());
    }

    @Test
    public void puntosArticulacionInStarGraphTest(){
        g.agregarVertice("A");
        g.agregarVertice("B");
        g.agregarVertice("C");
        g.agregarVertice("D");
        g.agregarVertice("E");
        g.agregarArista("A", "B", 1);
        g.agregarArista("A", "C", 1);
        g.agregarArista("A", "D", 1);
        g.agregarArista("A", "E", 1);
        assertTrue(algorithms.puntosDeArticulacion(g).contains("A"));
        assertEquals(1, algorithms.puntosDeArticulacion(g).size());
    }

    @Test
    public void puntosArticulacionInDisconnectedGraphTest() {
        g.agregarVertice("A");
        g.agregarVertice("B");
        g.agregarVertice("C");
        g.agregarVertice("D");
        g.agregarVertice("E");
        g.agregarArista("A", "B", 1);
        g.agregarArista("B", "C", 1);
        g.agregarArista("D", "E", 1);

        assertEquals(2, algorithms.puntosDeArticulacion(g).size());
        assertTrue(algorithms.puntosDeArticulacion(g).contains("B"));
        assertTrue(algorithms.puntosDeArticulacion(g).contains("D"));
    }
}
