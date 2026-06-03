package ucu.edu.aed.tda.grafo.ej3;

import ucu.edu.aed.tda.grafo.impl.DirectedGraph;
import ucu.edu.aed.tda.grafo.model.edge.WeightedEdge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ejercicio3 {

    public static void cargarDatos(DirectedGraph<String, WeightedEdge> grafo, String rutaAeropuertos, String rutaConexiones) {
        
        // primero carga los aeropuertos
        try (BufferedReader br = new BufferedReader(new FileReader(rutaAeropuertos))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String aeropuerto = linea.trim();
                if (!aeropuerto.isEmpty()) {
                    grafo.agregarVertice(aeropuerto);
                }
            }
        }
        // me defiendo de errores en el archivo
        catch (IOException e) {
            System.err.println("Error al leer el archivo de aeropuertos: " + e.getMessage());
        }

        // luego carga las conexiones (aristas)
        try (BufferedReader br = new BufferedReader(new FileReader(rutaConexiones))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.trim().split(","); // separa por comas
                if (partes.length < 3) continue; // omito líneas inválidas o vacías

                String origen = partes[0];
                String destino = partes[1];
                int peso = Integer.parseInt(partes[2]);

                grafo.agregarArista(origen, destino, new WeightedEdge(peso));
            }
        }
        // me defiendo de errores en el archivo
        catch (IOException e) {
            System.err.println("Error al leer el archivo de conexiones: " + e.getMessage());
        }
        
        catch (NumberFormatException e) {
            System.err.println("Error en el formato numérico del peso: " + e.getMessage());
        }
    }

}