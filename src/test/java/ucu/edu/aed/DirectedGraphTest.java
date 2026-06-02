package ucu.edu.aed;

import org.junit.jupiter.api.Test;
import ucu.edu.aed.tda.grafo.impl.DirectedGraph;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DirectedGraphTest {
    private DirectedGraph<String,Integer> g = new DirectedGraph<>();

    @Test
    void testAgregarYBuscarVertice() {
        assertTrue(g.agregarVertice("A"));
        assertFalse(g.agregarVertice("A"));

        assertEquals("A", g.buscarVertice("A"));
        assertNull(g.buscarVertice("B"));
    }

    @Test
    void testAgregarAristaYExisteArista() {
        g.agregarArista("A","B",1);

        assertTrue(g.existeArista("A","B"));
        assertFalse(g.existeArista("B","A"));
    }


    @Test
    void testEliminarArista() {
        g.agregarArista("A","B",1);

        assertTrue(g.eliminarArista("A","B"));
        assertFalse(g.existeArista("A","B"));
    }

    @Test
    void testRemoverVertice() {
        g.agregarArista("A","B",1);

        assertTrue(g.removerVertice("A"));

        assertNull(g.buscarVertice("A"));
        assertFalse(g.removerVertice("A"));
    }

    @Test
    void testVertices() {
        g.agregarVertice("A");
        g.agregarVertice("B");

        assertEquals(2, g.vertices().size());
    }

    @Test
    void testAristas() {
        g.agregarArista("A","B",1);
        g.agregarArista("A","C",2);

        assertEquals(2, g.aristas().size());
    }

    @Test
    void testVaciar() {
        g.agregarArista("A","B",1);

        g.vaciar();

        assertTrue(g.vertices().isEmpty());
        assertTrue(g.aristas().isEmpty());
    }

    @Test
    void testSuccessors() {
        g.agregarArista("A","B",1);
        g.agregarArista("A","C",2);

        Set<String> succ = g.successors("A");

        assertEquals(2, succ.size());
        assertTrue(succ.contains("B"));
        assertTrue(succ.contains("C"));
    }

    @Test
    void testPredecessors() {
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
        g.agregarArista("A","B",1);
        g.agregarArista("B","C",1);
        g.agregarArista("C","A",1);

        assertTrue(g.tieneCiclos());
    }

    @Test
    void testTieneCiclosFalse() {
        g.agregarArista("A","B",1);
        g.agregarArista("B","C",1);

        assertFalse(g.tieneCiclos());
    }

    @Test
    void testEliminarAristaNotExistSourceAndTarget(){
        assertFalse(g.eliminarArista("A","B"));
    }

    @Test
    void testExisteAristaThatNotExists(){
        assertFalse(g.existeArista("A","B"));
    }

    @Test
    void testAdyacenciasInNoExistingNode(){
        assertTrue(g.adyacencias("A").isEmpty());
    }
    @Test
    void testObtenerAristaThatExists(){
        g.agregarArista("A","B",1);
        assertNotNull(g.obtenerArista("A","B"));
    }
    @Test
    void testAgregarAristaAutoAgregaVertices() {
        assertTrue(g.agregarArista("X", "Y", 99));

        assertNotNull(g.buscarVertice("X"));
        assertNotNull(g.buscarVertice("Y"));
        assertTrue(g.existeArista("X", "Y"));
    }

    @Test
    void testSuccessorsVerticeInexistente() {
        g.agregarArista("A", "B", 1);

        Set<String> succ = g.successors("Z");

        assertTrue(succ.isEmpty());
    }

    @Test
    void testSuccessorsSinSalientes() {
        g.agregarArista("A", "B", 1);

        Set<String> succ = g.successors("B");

        assertTrue(succ.isEmpty());
    }

    @Test
    void testPredecessorsVerticeInexistente() {
        g.agregarArista("A", "B", 1);

        Set<String> pred = g.predecessors("Z");

        assertTrue(pred.isEmpty());
    }

    @Test
    void testPredecessorsSinEntrantes() {
        g.agregarArista("A", "B", 1);

        Set<String> pred = g.predecessors("A");

        assertTrue(pred.isEmpty());
    }

    @Test
    void testEliminarAristaVerticesExistenPeroAristaNo() {
        g.agregarVertice("A");
        g.agregarVertice("B");
        g.agregarVertice("C");
        g.agregarArista("A", "B", 1);

        assertFalse(g.eliminarArista("A", "C"));
        assertTrue(g.existeArista("A", "B"));
    }

    @Test
    void testRemoverVerticeEliminaEntrantesYSalientes() {
        g.agregarArista("A", "B", 1);
        g.agregarArista("C", "A", 2);
        g.agregarArista("A", "D", 3);

        assertTrue(g.removerVertice("A"));

        assertNull(g.buscarVertice("A"));
        assertFalse(g.existeArista("A", "B"));
        assertFalse(g.existeArista("C", "A"));
        assertFalse(g.existeArista("A", "D"));
    }

    @Test
    void testAdyacenciasNodoExistenteSinAristas() {
        g.agregarVertice("A");

        assertTrue(g.adyacencias("A").isEmpty());
    }

    @Test
    void testAdyacenciasNodoExistenteConAristas() {
        g.agregarArista("A", "B", 1);
        g.agregarArista("A", "C", 2);

        assertEquals(2, g.adyacencias("A").size());
    }

    @Test
    void testObtenerAristaNoExisteConVerticesExistentes() {
        g.agregarVertice("A");
        g.agregarVertice("B");

        assertNull(g.obtenerArista("A", "B"));
    }

    @Test
    void testObtenerAristaConVerticesInexistentes() {
        assertNull(g.obtenerArista("X", "Y"));
    }

    @Test
    void testEsConexoImplementacionActual() {
        g.agregarArista("A", "B", 1);

        assertFalse(g.esConexo());
    }

}
