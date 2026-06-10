package ucu.edu.aed.tda.grafo.impl;

import java.util.*;
import java.util.function.Consumer;

import ucu.edu.aed.tda.grafo.IUndirectedGraph;
import ucu.edu.aed.tda.grafo.IUndirectedGraphAlgorithm;
import ucu.edu.aed.tda.grafo.model.IGraph;
import ucu.edu.aed.tda.grafo.model.edge.Edge;
import ucu.edu.aed.tda.grafo.model.edge.WeightedEdge;

public class UndirectedGraphAlgorithms implements IUndirectedGraphAlgorithm{

    @Override
    public <V, D extends WeightedEdge> IUndirectedGraph<V, D> kruskal(IUndirectedGraph<V, D> graph) {
        Set<Edge<V, D>> aristas = new HashSet<>();
        aristas.addAll(graph.aristas());

        UndirectedGraph<V, D> grafoAux = new UndirectedGraph<>();
        int n = graph.cantidadDeVertices() - 1;
        int i = 0;

        while (i < n) {
            var edge = searchAristaKruskal(aristas);
            var cabeza = edge.source();
            var destino = edge.target();
            
            aristas.remove(edge);
            grafoAux.agregarArista(cabeza, destino, edge.dato());

            if (grafoAux.tieneCiclos()){
                aristas.remove(edge);
            }
            else {
                i += 1;
            }
        }
        return grafoAux;
    }

    // método auxiliar para Kruskal; recibe un conjunto de aristas (más fácil que llamar a searchMinEdge)
    public <V, D extends WeightedEdge> Edge<V, D> searchAristaKruskal(Set<Edge<V, D>> aristas){
        var min = Double.MAX_VALUE;
        Edge<V, D> mejorArista = null;
        
        for (Edge<V,D> arista : aristas) {
            if (arista != null && arista.dato().getWeight() < min) {
                    min = arista.dato().getWeight();
                    mejorArista = arista;
                }
        }
        return mejorArista;
    }

    @Override
    public <V, D extends WeightedEdge> IUndirectedGraph<V, D> prim(IUndirectedGraph<V, D> graph, Comparable<V> source) {
        if (!graph.existeVertice(source)) {
            return null;
        }
        Set<V> vertices = new HashSet<>();
        Set<V> abarcador = new HashSet<>();
        UndirectedGraph<V, D> grafoAux = new UndirectedGraph<>();

        vertices.addAll(graph.vertices());
        V origen = graph.buscarVertice(source);

        abarcador.add(origen);
        vertices.remove(origen);
        int costo = 0;

        if (vertices.isEmpty()){
            return null;
        }
        else{
            while (!vertices.isEmpty()){
            var arista = searchMinEdge(graph, abarcador, vertices);
            var cabeza = arista.source();
            var destino = arista.target();
            
            grafoAux.agregarArista(cabeza, destino, arista.dato());

            vertices.remove(destino);
            abarcador.add(destino);
            costo += arista.dato().getWeight();
        }
        }
        return grafoAux;
    }

    @Override
    public <V, D extends WeightedEdge> Edge<V, D> searchMinEdge(IUndirectedGraph<V, D> graph, Collection<V> U,
            Collection<V> V) 
        {
        var min = Double.MAX_VALUE;
        Edge<V, D> mejorArista = null;
        for (V vU : U) {
            for (V vV : V) {
                var arista = graph.obtenerArista(vV, vU);
                if (arista != null && arista.dato().getWeight() < min) {
                    min = arista.dato().getWeight();
                    mejorArista = arista;
                }
            }
        }
        return mejorArista;
    }

    @Override
    public <V, D> void bea(IUndirectedGraph<V, D> graph, Consumer<V> consumer) {
        HashSet<V> visitados = new HashSet<>();
        Queue<V> cola = new LinkedList<>();

        for(V vertice: graph.vertices()){
            if(!visitados.contains(vertice)){
                cola.add(vertice);
                visitados.add(vertice);
                while(!cola.isEmpty()){
                    V actual = cola.poll();
                    consumer.accept(actual);

                    for(Edge<V,D> adyacenteArista : graph.adyacencias(graph.construirComparable(actual))){
                        V adyacenteVertice = adyacenteArista.target();
                        if(!visitados.contains(adyacenteVertice)){
                            cola.add(adyacenteVertice);
                            visitados.add(adyacenteVertice);
                        }
                    }
                }
            }
        }
    }

    public <V, D> List<V> puntosDeArticulacion(IGraph<V, D> grafo){
        Map<V, VertexJointPoint<V,D>> verticesPuntoArticulacion = new HashMap<>();
        for(V v : grafo.vertices()) {
            HashSet<Edge<V,D>> ady = new HashSet<>(grafo.adyacencias(grafo.construirComparable(v)));
            verticesPuntoArticulacion.put(v, new VertexJointPoint<>(v, ady));
        }
        LinkedList<V> puntos = new LinkedList<>();
        int[] count = {0};

        for (V v : grafo.vertices()) {
            VertexJointPoint<V, D> verticePuntoArticulacion = verticesPuntoArticulacion.get(v);
            if (!verticePuntoArticulacion.visitado) {
                verticePuntoArticulacion.puntosArticulacion(puntos, count, verticesPuntoArticulacion);
            }
        }
        return puntos;
    }
    public int numBacon(IUndirectedGraph<String, String> grafo , String actor){
        String destino= grafo.buscarVertice(grafo.construirComparable(actor));
        String kevin= grafo.buscarVertice(grafo.construirComparable("Kevin_Bacon"));

        if(destino==null || kevin== null){
            return -1; // no existe nadie
        }

        List<String> lista =new LinkedList<>();
        Map<String, Integer> distancias= new HashMap<>();
        Set<String> visitados= new HashSet<>();

        lista.add(kevin);
        distancias.put(kevin, 0);
        visitados.add(kevin);

         while (!lista.isEmpty()) {
            String actual = lista.remove(0);

            if (actual.equals(destino)) {
                return distancias.get(actual);   // num de bacon encontrado
            }

            for (Edge<String, String> e : grafo.adyacencias(grafo.construirComparable(actual))) {
                String vecino = actual.equals(e.source()) ? e.target() : e.source();

                if (!visitados.contains(vecino)) {
                    visitados.add(vecino);
                    distancias.put(vecino, distancias.get(actual) + 1);
                    lista.add(vecino);
                }
            }
        }

        return -1; // no hay camino a Kevin Bacon
    }
}
