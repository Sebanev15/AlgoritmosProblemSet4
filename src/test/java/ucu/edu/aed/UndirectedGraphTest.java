package ucu.edu.aed;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

import ucu.edu.aed.tda.grafo.impl.UndirectedGraph;
import ucu.edu.aed.tda.grafo.model.edge.Edge;

public class UndirectedGraphTest {

    @Test
    void agregarYBuscarVertice() {
        UndirectedGraph<String, Integer> g = new UndirectedGraph<>();

        assertTrue(g.agregarVertice("A"));
        assertEquals("A", g.buscarVertice("A"));
        assertNull(g.buscarVertice("B"));
    }

    @Test
    void agregarAristaYExisteArista() {
        UndirectedGraph<String, Integer> g = new UndirectedGraph<>();

        assertTrue(g.agregarArista("A", "B", 10));

        assertTrue(g.existeArista("A", "B"));
        assertTrue(g.existeArista("B", "A"));
    }

    @Test
    void obtenerArista() {
        UndirectedGraph<String, Integer> g = new UndirectedGraph<>();

        g.agregarArista("A", "B", 5);

        Edge<String, Integer> arista = g.obtenerArista("A", "B");

        assertNotNull(arista);
        assertEquals("A", arista.source());
        assertEquals("B", arista.target());
    }

    @Test
    void eliminarArista() {
        UndirectedGraph<String, Integer> g = new UndirectedGraph<>();

        g.agregarArista("A", "B", 5);

        assertTrue(g.eliminarArista("A", "B"));
        assertFalse(g.existeArista("A", "B"));
    }

    @Test
    void removerVertice() {
        UndirectedGraph<String, Integer> g = new UndirectedGraph<>();

        g.agregarArista("A", "B", 1);

        assertTrue(g.removerVertice("A"));

        assertNull(g.buscarVertice("A"));
        assertFalse(g.existeArista("A", "B"));
    }

    @Test
    void verticesYAristas() {
        UndirectedGraph<String, Integer> g = new UndirectedGraph<>();

        g.agregarArista("A", "B", 1);

        Set<String> vertices = g.vertices();

        assertEquals(2, vertices.size());
        assertEquals(1, g.aristas().size());
    }

    @Test
    void adyacencias() {
        UndirectedGraph<String, Integer> g = new UndirectedGraph<>();

        g.agregarArista("A", "B", 1);
        g.agregarArista("A", "C", 2);

        assertEquals(2, g.adyacencias("A").size());
    }

    @Test
    void vaciar() {
        UndirectedGraph<String, Integer> g = new UndirectedGraph<>();

        g.agregarArista("A", "B", 1);

        g.vaciar();

        assertTrue(g.vertices().isEmpty());
        assertTrue(g.aristas().isEmpty());
    }

    @Test
    void grafoVacioNoEsConexo() {
        UndirectedGraph<String, Integer> g = new UndirectedGraph<>();

        assertFalse(g.esConexo());
    }

    @Test
    void grafoConUnSoloVertice() {
        UndirectedGraph<String, Integer> g = new UndirectedGraph<>();

        g.agregarVertice("A");

        assertTrue(g.esConexo());
    }
    @Test
    void grafoSinCiclos() {
        UndirectedGraph<String,Integer> g = new UndirectedGraph<>();

        g.agregarArista("A","B",1);
        g.agregarArista("B","C",1);

        assertFalse(g.tieneCiclos());
    }

    @Test
    void grafoConCiclo() {
        UndirectedGraph<String,Integer> g = new UndirectedGraph<>();

        g.agregarArista("A","B",1);
        g.agregarArista("B","C",1);
        g.agregarArista("C","A",1);

        assertTrue(g.tieneCiclos());
    }
    @Test
    void agregarVerticeDuplicado() {
        UndirectedGraph<String,Integer> g = new UndirectedGraph<>();

        assertTrue(g.agregarVertice("A"));
        assertFalse(g.agregarVertice("A"));
    }
    @Test
    void eliminarAristaInexistente() {
        UndirectedGraph<String,Integer> g = new UndirectedGraph<>();

        g.agregarArista("A", "B", 1);

        assertFalse(g.eliminarArista("A", "C"));
    }
    @Test
    void removerVerticeInexistente() {
        UndirectedGraph<String,Integer> g = new UndirectedGraph<>();

        g.agregarVertice("A");

        assertFalse(g.removerVertice("B"));
    }
}
