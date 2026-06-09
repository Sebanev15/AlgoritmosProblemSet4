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
        algorithms.puntosDeArticulacion()
    }
}
