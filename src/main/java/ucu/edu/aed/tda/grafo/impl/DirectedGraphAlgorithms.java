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
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.function.Consumer;

public class DirectedGraphAlgorithms implements IDirectedGraphAlgorithms {
    @Override
    public <V, D extends WeightedEdge> IDijkstraResult<V> dijkstra(Comparable<V> source, IDirectedIGraph<V, D> grafo) {
        if (!grafo.existeVertice(source)) {
            return null;
        }
        Map<V, Double> costos = new HashMap<>();
        Map<V, V> predecesores = new HashMap<>();
        Set<V> visitados = new HashSet<>();

        V origen = grafo.buscarVertice(source);

        visitados.add(origen);

        for(V vertice: grafo.vertices()){
            Comparable<V> verticeComparable = grafo.construirComparable(vertice);

            if(grafo.existeArista(source, verticeComparable)){
                Edge<V,D> arista = grafo.obtenerArista(source, verticeComparable);
                costos.put(vertice, arista.dato().getWeight());
                predecesores.put(vertice, origen);
            }
            else{
                costos.put(vertice, Double.POSITIVE_INFINITY);
            }
        }
        costos.put(origen, 0.0);

        while (visitados.size() < grafo.vertices().size()) {
            V verticeMenorCosto = null;
            double menorCosto = Double.POSITIVE_INFINITY;

            for (V vertice : grafo.vertices()) {
                if (!visitados.contains(vertice)) {
                    Double costo = costos.getOrDefault(vertice, Double.POSITIVE_INFINITY);
                    if (costo < menorCosto) {
                        menorCosto = costo;
                        verticeMenorCosto = vertice;
                    }
                }
            }

            if (verticeMenorCosto == null) {
                break;
            }

            visitados.add(verticeMenorCosto);

            Comparable<V> verticeMenorCostoComparable = grafo.construirComparable(verticeMenorCosto);
            for (V vertice: grafo.vertices()) {
                if(!visitados.contains(vertice)){
                    Comparable<V> verticeComparable = grafo.construirComparable(vertice);
                    if(grafo.existeArista(verticeMenorCostoComparable, verticeComparable)){
                        Edge<V,D> arista = grafo.obtenerArista(verticeMenorCostoComparable, verticeComparable);
                        double nuevoCosto = costos.get(verticeMenorCosto) + arista.dato().getWeight();
                        if (nuevoCosto < costos.get(vertice)) {
                            costos.put(vertice, nuevoCosto);
                            predecesores.put(vertice, verticeMenorCosto);
                        }
                    }
                }
            }
        }
        return new DijkstraResult<>(origen, costos, predecesores);
    }


    // FLOYD
    @Override
    public <V, D extends WeightedEdge> IFloydWarshallResult<V> floyd(IDirectedIGraph<V, D> grafo) {
    List<V> vertices = new ArrayList<>(grafo.vertices());

    Map<V, Map<V, Double>> dist = new HashMap<>();
    Map<V, Map<V, V>> next = new HashMap<>();

    // Inicialización
    for (V u : vertices) {
        dist.put(u, new HashMap<>());
        next.put(u, new HashMap<>());
        for (V v : vertices) {
            if (u.equals(v)) {
                dist.get(u).put(v, 0.0);
            } else {
                Edge<V, D> e = grafo.obtenerArista(u, v);
                if (e != null) {
                    dist.get(u).put(v, e.dato().getWeight());
                    next.get(u).put(v, v);
                } else {
                    dist.get(u).put(v, Double.POSITIVE_INFINITY);
                    next.get(u).put(v, null);
                }
            }
        }
    }

    // O(n3)
    for (V k : vertices) {
        for (V i : vertices) {
            for (V j : vertices) {
                double alt = dist.get(i).get(k) + dist.get(k).get(j);
                if (alt < dist.get(i).get(j)) {
                    dist.get(i).put(j, alt);
                    next.get(i).put(j, next.get(i).get(k));
                }
            }
        }
    }
    return new FloydWarshallResult<>(dist, next);
    }

    @Override
    public <V, D extends WeightedEdge> IFloydWarshallResult<V> warshall(IDirectedIGraph<V, D> grafo) {
        List<V> vertices = new ArrayList<>(grafo.vertices());
        int n = vertices.size();

        Map<V, Map<V, Boolean>> alcanzable = new HashMap<>();

        // Inicialización
        for (V u : vertices) {
            alcanzable.put(u, new HashMap<>());
            for (V v : vertices) {
                if (u.equals(v)) {
                    alcanzable.get(u).put(v, true);
                }
                else {
                    Comparable<V> compU = grafo.construirComparable(u);
                    Comparable<V> compV = grafo.construirComparable(v);
                    alcanzable.get(u).put(v, grafo.existeArista(compU, compV));
                }
            }
        }

        // O(n3)
        for (V k : vertices) {
            for (V i : vertices) {
                for (V j : vertices) {
                    if (alcanzable.get(i).get(k) && alcanzable.get(k).get(j)) {
                        alcanzable.get(i).put(j, true);
                    }
                }
            }
        }

        return (IFloydWarshallResult<V>) alcanzable;
    }


    @Override //TODO
    public <V, D extends WeightedEdge> V obtenerCentroGrafo(IDirectedIGraph<V, D> grafo) {
        return null;
    }

    @Override //TODO
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
        obtenerTodosLosCaminosAux(origen, destino, grafo, caminos, new ArrayList<>(), new HashSet<>(), 0);
        return caminos;
    }

    private <V,D extends WeightedEdge> void obtenerTodosLosCaminosAux( V actual, V destino, IGraph<V,D> grafo, List<Path<V>> caminos, 
        List<V> caminoActual, Set<V> visitados, double costoActual){
        caminoActual.add(actual);//agrego el V actual al camino
        visitados.add(actual); // lo marco como visitado

            if(actual.equals(destino)){ // si se llegó a destino se guarda el camino
            caminos.add(new Path<>(new ArrayList<>(caminoActual), costoActual));
            }
            else{ // recorremos las aristas que llevan a los adyacentes
                for(Edge<V,D> arista: grafo.adyacencias(grafo.construirComparable(actual))){
                V vecino=arista.target();
                    if (!visitados.contains(vecino)){
                        // esto para evitar vecinos
                        obtenerTodosLosCaminosAux(vecino, destino, grafo, caminos, caminoActual, visitados, costoActual + arista.dato().getWeight());
                    }
                }
            }
            visitados.remove(actual);
            caminoActual.remove(caminoActual.size()-1);
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
        visitados.add(actual);
        consumer.accept(actual);

        for (Edge<V,D> arista : grafo.adyacencias(criterio)) {
            dfs(grafo, grafo.construirComparable(arista.target()), consumer, visitados);
        }
    }

    @Override //No corresponde a grafos dirigidos
    public <V, D> void recorridoEnAmplitud(IGraph<V, D> grafo, Comparable<V> sourceCriteria, Consumer<V> consumer) {

    }

    @Override
    public <V, D> List<V> calcularClasificacionTopologica(IDirectedIGraph<V, D> grafo) {
        Map<V, Integer> gradoEntrada = new HashMap<>();

        for (V vertice : grafo.vertices()) {
            gradoEntrada.put(vertice, grafo.gradoDeEntrada(grafo.construirComparable(vertice)));
        }

        Queue<V> cola = new LinkedList<>();

        for (Map.Entry<V, Integer> entry : gradoEntrada.entrySet()) {

            if (entry.getValue() == 0) {
                cola.add(entry.getKey());
            }
        }

        List<V> resultado = new ArrayList<>();
        while (!cola.isEmpty()) {
            V actual = cola.poll();
            resultado.add(actual);
            for (V sucesor : grafo.successors(grafo.construirComparable(actual))) {
                int nuevoGrado = gradoEntrada.get(sucesor) - 1;
                gradoEntrada.put(sucesor, nuevoGrado);

                if (nuevoGrado == 0) {
                    cola.add(sucesor);
                }
            }
        }

        if (resultado.size() != grafo.vertices().size()) {
            throw new IllegalStateException("El grafo contiene un ciclo, no es posible calcular la clasificación topológica.");
        }

        return resultado;
    }
}
