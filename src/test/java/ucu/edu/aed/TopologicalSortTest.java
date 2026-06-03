package ucu.edu.aed;

import org.junit.jupiter.api.Test;
import ucu.edu.aed.tda.grafo.impl.DirectedGraph;
import ucu.edu.aed.tda.grafo.impl.DirectedGraphAlgorithms;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TopologicalSortTest {

    DirectedGraph<String, Integer> g = new DirectedGraph<>();
    private DirectedGraphAlgorithms algorithms = new DirectedGraphAlgorithms();

    // Un solo vértice, sin aristas -> el resultado es ese único vértice
    @Test
    public void testClasificacionTopologicaConUnSoloVertice() {
        g.agregarVertice("A");

        List<String> resultado = algorithms.calcularClasificacionTopologica(g);

        assertEquals(1, resultado.size());
        assertTrue(resultado.contains("A"));
    }

    // Cadena lineal A -> B -> C -> el orden debe respetar que A va antes que B, y B antes que C
    @Test
    public void testClasificacionTopologicaCadenaLineal() {
        g.agregarVertice("A");
        g.agregarVertice("B");
        g.agregarVertice("C");
        g.agregarArista("A", "B", 1);
        g.agregarArista("B", "C", 1);

        List<String> resultado = algorithms.calcularClasificacionTopologica(g);

        assertEquals(3, resultado.size());
        assertTrue(resultado.indexOf("A") < resultado.indexOf("B"));
        assertTrue(resultado.indexOf("B") < resultado.indexOf("C"));
    }

    // Grafo con dos ramas independientes: A->C y B->C
    // A y B pueden ir en cualquier orden, pero ambos deben ir antes que C
    @Test
    public void testClasificacionTopologicaConDosNodosOrigen() {
        g.agregarVertice("A");
        g.agregarVertice("B");
        g.agregarVertice("C");
        g.agregarArista("A", "C", 1);
        g.agregarArista("B", "C", 1);

        List<String> resultado = algorithms.calcularClasificacionTopologica(g);

        assertEquals(3, resultado.size());
        assertTrue(resultado.indexOf("A") < resultado.indexOf("C"));
        assertTrue(resultado.indexOf("B") < resultado.indexOf("C"));
    }

    // Grafo más complejo: A->B, A->C, B->D, C->D
    // A debe ir primero, D debe ir último, B y C en el medio
    @Test
    public void testClasificacionTopologicaFormaDeRombo() {
        g.agregarVertice("A");
        g.agregarVertice("B");
        g.agregarVertice("C");
        g.agregarVertice("D");
        g.agregarArista("A", "B", 1);
        g.agregarArista("A", "C", 1);
        g.agregarArista("B", "D", 1);
        g.agregarArista("C", "D", 1);

        List<String> resultado = algorithms.calcularClasificacionTopologica(g);

        assertEquals(4, resultado.size());
        assertTrue(resultado.indexOf("A") < resultado.indexOf("B"));
        assertTrue(resultado.indexOf("A") < resultado.indexOf("C"));
        assertTrue(resultado.indexOf("B") < resultado.indexOf("D"));
        assertTrue(resultado.indexOf("C") < resultado.indexOf("D"));
    }

    // Vértices sin aristas entre sí -> todos deben aparecer en el resultado
    @Test
    public void testClasificacionTopologicaVerticesDesconectados() {
        g.agregarVertice("A");
        g.agregarVertice("B");
        g.agregarVertice("C");

        List<String> resultado = algorithms.calcularClasificacionTopologica(g);

        assertEquals(3, resultado.size());
        assertTrue(resultado.contains("A"));
        assertTrue(resultado.contains("B"));
        assertTrue(resultado.contains("C"));
    }

    // Grafo con ciclo -> debe lanzar IllegalStateException
    @Test
    public void testClasificacionTopologicaConCicloPorExcepcion() {
        g.agregarVertice("A");
        g.agregarVertice("B");
        g.agregarVertice("C");
        g.agregarArista("A", "B", 1);
        g.agregarArista("B", "C", 1);
        g.agregarArista("C", "A", 1); // ciclo

        assertThrows(IllegalStateException.class, () -> {
            algorithms.calcularClasificacionTopologica(g);
        });
    }
}