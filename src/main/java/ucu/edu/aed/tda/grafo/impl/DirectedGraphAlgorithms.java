package ucu.edu.aed.tda.grafo.impl;

import ucu.edu.aed.tda.grafo.IDirectedGraphAlgorithms;
import ucu.edu.aed.tda.grafo.IDirectedIGraph;
import ucu.edu.aed.tda.grafo.model.IGraph;
import ucu.edu.aed.tda.grafo.model.edge.Edge;
import ucu.edu.aed.tda.grafo.model.edge.WeightedEdge;
import ucu.edu.aed.tda.grafo.model.result.IDijkstraResult;
import ucu.edu.aed.tda.grafo.model.result.IFloydWarshallResult;
import ucu.edu.aed.tda.grafo.model.result.Path;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class DirectedGraphAlgorithms implements IDirectedGraphAlgorithms {
    @Override
    public <V, D extends WeightedEdge> IDijkstraResult<V> dijkstra(Comparable<V> source, IDirectedIGraph<V, D> grafo) {
        return null;
    }

    @Override
    public <V, D extends WeightedEdge> IFloydWarshallResult<V> floyd(IDirectedIGraph<V, D> grafo) {
        return null;
    }

    @Override
    public <V, D extends WeightedEdge> IFloydWarshallResult<V> warshall(IDirectedIGraph<V, D> grafo) {
        return null;
    }

    @Override
    public <V, D extends WeightedEdge> V obtenerCentroGrafo(IDirectedIGraph<V, D> grafo) {
        return null;
    }

    @Override
    public <V, D extends WeightedEdge> double obtenerExcentricidad(IDirectedIGraph<V, D> grafo, Comparable<V> vertexCriteria) {
        return 0;
    }

    @Override
    public <V, D extends WeightedEdge> List<Path<V>> obtenerTodosLosCaminos(Comparable<V> source, Comparable<V> target, IGraph<V, D> grafo) {
        List<Path<V>> caminos=new ArrayList<>();
        V origen= grafo.buscarVertice(source);
        V destino= grafo.buscarVertice(target);
        if (origen==null || destino==null){
            return caminos;
        }
        List<V> caminoActual=new ArrayList<>();
        Set<V> visitados= new HashSet<>();
        
        obtenerTodosLosCaminosAux(source, target, grafo)
        
        return List.of();
    }

    private <V,D extends WeightedEdge> void obtenerTodosLosCaminosAux( V actual, V destino, IGraph<V,D> grafo, List<Path<V>> caminos, 
        List<V> caminoActual, Set<V> visitados, double costoActual){
            caminoActual.add(actual);//agrego el V actual al camino
            visitados.add(actual); // lo marco como visitado

            if(actual.equals(destino)){ // si se llego a destino se guarda el camino
                caminos.add(new Path<>(new ArrayList<>(caminoActual), costoActual));
            }
            else{ // recorremos las aristas que llevan a los adyacentes
                for(Edge<V,D> arista: grafo.adyacencias(grafo.construirComparable(actual))){
                    V vecino=arista.target();
                    if (!visitados.contains(vecino)){ // esto para evitar vecinos
                        obtenerTodosLosCaminosAux(vecino, destino, grafo, caminos, caminoActual, visitados, costoActual + arista.dato().getWeight());
                    }
                
                
                
                }   
            
            
            }   
       
        
        
        }
    }
    
    @Override
    public <V, D> void recorridoEnProfundidad(
            IGraph<V, D> grafo,
            Comparable<V> sourceCriteria,
            Consumer<V> consumer) {
        Set<V> visitados = new HashSet<>();

        dfs(grafo, sourceCriteria, consumer, visitados);
    }

    private <V,D> void dfs(
            IGraph<V,D> grafo,
            Comparable<V> criterio,
            Consumer<V> consumer,
            Set<V> visitados) {

        V actual = grafo.buscarVertice(criterio);

        if(actual == null || visitados.contains(actual)){
            return;
        }

    }

        @Override
    public <V, D> void recorridoEnAmplitud(IGraph<V, D> grafo, Comparable<V> sourceCriteria, Consumer<V> consumer) {

    }

    @Override
    public <V, D> List<V> calcularClasificacionTopologica(IDirectedIGraph<V, D> grafo) {
        return List.of();
    }
}
