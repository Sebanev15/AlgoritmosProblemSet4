package ucu.edu.aed;

import org.junit.jupiter.api.Test;
import ucu.edu.aed.tda.grafo.impl.DirectedGraph;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DirectedGraphTest {

    @Test
    void testAgregarYBuscarVertice() {
        DirectedGraph<String,Integer> g = new DirectedGraph<>();

        assertTrue(g.agregarVertice("A"));
        assertFalse(g.agregarVertice("A"));

        assertEquals("A", g.buscarVertice("A"));
        assertNull(g.buscarVertice("B"));
    }

    @Test
    void testAgregarAristaYExisteArista() {
        DirectedGraph<String,Integer> g = new DirectedGraph<>();

        g.agregarArista("A","B",1);

        assertTrue(g.existeArista("A","B"));
        assertFalse(g.existeArista("B","A"));
    }


    @Test
    void testEliminarArista() {
        DirectedGraph<String,Integer> g = new DirectedGraph<>();

        g.agregarArista("A","B",1);

        assertTrue(g.eliminarArista("A","B"));
        assertFalse(g.existeArista("A","B"));
    }

    @Test
    void testRemoverVertice() {
        DirectedGraph<String,Integer> g = new DirectedGraph<>();

        g.agregarArista("A","B",1);

        assertTrue(g.removerVertice("A"));

        assertNull(g.buscarVertice("A"));
        assertFalse(g.removerVertice("A"));
    }

    @Test
    void testVertices() {
        DirectedGraph<String,Integer> g = new DirectedGraph<>();

        g.agregarVertice("A");
        g.agregarVertice("B");

        assertEquals(2, g.vertices().size());
    }

    @Test
    void testAristas() {
        DirectedGraph<String,Integer> g = new DirectedGraph<>();

        g.agregarArista("A","B",1);
        g.agregarArista("A","C",2);

        assertEquals(2, g.aristas().size());
    }

    @Test
    void testVaciar() {
        DirectedGraph<String,Integer> g = new DirectedGraph<>();

        g.agregarArista("A","B",1);

        g.vaciar();

        assertTrue(g.vertices().isEmpty());
        assertTrue(g.aristas().isEmpty());
    }

    @Test
    void testSuccessors() {
        DirectedGraph<String,Integer> g = new DirectedGraph<>();

        g.agregarArista("A","B",1);
        g.agregarArista("A","C",2);

        Set<String> succ = g.successors("A");

        assertEquals(2, succ.size());
        assertTrue(succ.contains("B"));
        assertTrue(succ.contains("C"));
    }

    @Test
    void testPredecessors() {
        DirectedGraph<String,Integer> g = new DirectedGraph<>();

        g.agregarArista("A","B",1);
        g.agregarArista("C","B",1);
        g.agregarArista("B","D",1);

        Set<String> pred = g.predecessors("B");

        assertEquals(2, pred.size());
        assertTrue(pred.contains("A"));
        assertTrue(pred.contains("C"));
    }

    @Test
    void testTieneCiclosTrue() {
        DirectedGraph<String,Integer> g = new DirectedGraph<>();

        g.agregarArista("A","B",1);
        g.agregarArista("B","C",1);
        g.agregarArista("C","A",1);

        assertTrue(g.tieneCiclos());
    }

    @Test
    void testTieneCiclosFalse() {
        DirectedGraph<String,Integer> g = new DirectedGraph<>();

        g.agregarArista("A","B",1);
        g.agregarArista("B","C",1);

        assertFalse(g.tieneCiclos());
    }
}
