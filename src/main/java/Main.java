import ucu.edu.aed.tda.grafo.ej3.ejercicio3;
import ucu.edu.aed.tda.grafo.impl.DirectedGraph;
import ucu.edu.aed.tda.grafo.impl.DirectedGraphAlgorithms;
import ucu.edu.aed.tda.grafo.model.edge.WeightedEdge;
import ucu.edu.aed.tda.grafo.model.result.IFloydWarshallResult;

public class Main {
    public static void main(String[] args) {
        DirectedGraph<String, WeightedEdge> grafoEjercicio3 = new DirectedGraph<>();
        ejercicio3.cargarDatos(grafoEjercicio3, "src/main/resources/aeropuertos.txt", "src/main/resources/conexiones.txt");

        // verificación del grafo
        System.out.println("-------------------- Ejercicio 3 --------------------");
        System.out.println("Total de vuelos cargados: " + grafoEjercicio3.aristas().size());

        IFloydWarshallResult<String> floyd = new DirectedGraphAlgorithms().floyd(grafoEjercicio3);

        var origen = grafoEjercicio3.buscarVertice("Curitiba"); // tomo un aeropuerto de ejemplo
        var destino = grafoEjercicio3.buscarVertice("Montevideo"); // tomo otro aeropuerto

        System.out.println("-----------------------------------------------------");
        System.out.println("¿Se conecta " + origen + " con " + destino + "? " + floyd.connected(origen, destino));
        System.out.println("Menor distancia de " + origen + " a " + destino + ": " + floyd.getCost(origen, destino));
        System.out.println("Camino de " + origen + " a " + destino + ": " + floyd.getPath(origen, destino));
        System.out.println("-----------------------------------------------------");

        var centro = new DirectedGraphAlgorithms().obtenerCentroGrafo(grafoEjercicio3);
        System.out.println("-----------------------------------------------------");
        System.out.println("Si la aerolínea decide instalar un nuevo centro de mantenimiento y logística "+
         "para sus aviones, lo haría en " + centro + ", ya que es el centro del grafo. Es decir, el vértice " +
         "con menor excentricidad, lo que implica que tiene la menor distancia máxima a cualquier otro vértice del grafo." );
        System.out.println("-----------------------------------------------------");
    }
}
