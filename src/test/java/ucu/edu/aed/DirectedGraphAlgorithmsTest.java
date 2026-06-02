package ucu.edu.aed;

import org.junit.jupiter.api.Test;
import ucu.edu.aed.tda.grafo.impl.DirectedGraph;
import ucu.edu.aed.tda.grafo.impl.DirectedGraphAlgorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;
public class DirectedGraphAlgorithmsTest {
    DirectedGraph<String, Integer> g = new DirectedGraph<>();
    private DirectedGraphAlgorithms algorithms = new DirectedGraphAlgorithms();
    @Test
    public void RecorridoEnProfundidadOnlyWithOneNodeTest() {
        List<String> resultado = new ArrayList<>();
        g.agregarVertice("A");
        algorithms.recorridoEnProfundidad(g, g.construirComparable("A"), resultado::add);
        assertEquals(1, resultado.size());
        assertTrue(resultado.contains("A"));
    }

    @Test
    public void RecorridoEnProfundidadWithMultipleNodesTest() {
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
    public void RecorridoEnProfunidadWithMultipleAristToNodeTest(){
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
}
