import ucu.edu.aed.tda.grafo.ej3.ejercicio3;

public class Main {
    public static void main(String[] args) {
    
    DirectedGraph<String, Integer> grafoEjercicio3 = new DirectedGraph<>();
    ejercicio3.cargarDatos(grafoEjercicio3, "aeropuertos.txt", "conexiones.txt");
    
    // verificación del grafo
    System.out.println("Total de vuelos cargados: " + grafoEjercicio3.aristas().size());
    }

}