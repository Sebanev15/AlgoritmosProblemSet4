package ucu.edu.aed;
import org.junit.jupiter.api.Test;
import ucu.edu.aed.tda.grafo.impl.UndirectedGraph;
import ucu.edu.aed.tda.grafo.impl.UndirectedGraphAlgorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

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
    public void puntosArticulacionInTwoVertexGraphTest(){
        g.agregarVertice("A");
        g.agregarVertice("B");
        assertTrue(algorithms.puntosDeArticulacion(g).isEmpty());
    }

    @Test
    public void puntosArticulacionInThreeVertexGraphTest() {
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
    public void puntosArticulacionInCyclicalGraphTest(){
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

    @Test
    public void busquedaEnAmplitudInEmptyGraphTest(){
        LinkedList<String> resultado = new LinkedList<>();
        algorithms.bea(g, (x -> {
            resultado.add(x);
        }));
        assertTrue(resultado.isEmpty());
    }

    @Test
    public void busquedaEnAmplitudInSingleVertexGraphTest(){
        g.agregarVertice("A");
        LinkedList<String> resultado = new LinkedList<>();
        algorithms.bea(g, (x -> {
            resultado.add(x);
        }));
        assertEquals(1, resultado.size());
        assertTrue(resultado.contains("A"));
    }

    @Test
    public void busquedaEnAmplitudInThreeVertexGraphTest(){
        g.agregarVertice("A");
        g.agregarVertice("B");
        g.agregarVertice("C");
        g.agregarArista("A", "B", 1);
        g.agregarArista("B", "C", 1);
        List<String> resultado = new ArrayList<>();
        algorithms.bea(g, (x -> {
            resultado.add(x);
        }));
        assertEquals(3, resultado.size());
        assertTrue(resultado.indexOf("A") < resultado.indexOf("B"));
        assertTrue(resultado.indexOf("B") < resultado.indexOf("C"));
    }

    @Test
    public void busquedaEnAmplitudInCyclicalGraphTest(){
        g.agregarVertice("A");
        g.agregarVertice("B");
        g.agregarVertice("C");
        g.agregarArista("A", "B", 1);
        g.agregarArista("B", "C", 1);
        g.agregarArista("C", "A", 1);
        List<String> resultado = new ArrayList<>();
        algorithms.bea(g, (x -> {
            resultado.add(x);
        }));
        assertEquals(3, resultado.size());

    }
}
