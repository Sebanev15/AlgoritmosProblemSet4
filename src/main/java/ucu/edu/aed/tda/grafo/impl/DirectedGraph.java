package ucu.edu.aed.tda.grafo.impl;
import ucu.edu.aed.tda.grafo.model.edge.Edge;

import java.util.*;

import ucu.edu.aed.tda.grafo.IDirectedIGraph;


public class DirectedGraph <V,D> implements IDirectedIGraph <V,D> {
    private List<V> vertices;
    private Map<V,Set<Arista<V,D>>> edges;
    
    public DirectedGraph(){
        vertices =new ArrayList<>();
        edges = new HashMap<>();
    }

    @Override
    public Set <V> successors(Comparable<V> criterio){
        Set<V> resultado= new HashSet<>();
        V source = buscarVertice(criterio);

        if(source == null || !vertices.contains(source)){
            return resultado;
        }
        // Usamos hashset para evitar repetidos y tener busquedas e inserciones
        // rapidas
        for (Set<Arista<V,D>> edges : edges.values() ){
            for(Arista<V,D> edge: edges){
                if(edge.source().equals(source)){
                    resultado.add(edge.target());
                }
            }
        }
        return resultado;
    }

    @Override
    public Set<V> predecessors(Comparable<V> criterio){
        Set<V> resultado = new HashSet<>();
        V target = buscarVertice(criterio);

        if(target==null || !vertices.contains(target)){
            return resultado;
        }

        for (Set<Arista<V,D>> edgeSet : edges.values()){
            for (Arista<V,D> edge : edgeSet){
                if(edge.target().equals(target)){
                    resultado.add(edge.source());
                }
            }
        }
        return resultado;
    }

    public D obtenerDatoArista(Comparable<V> sourceCriteria, Comparable<V> targetCriteria) {
        V source = buscarVertice(sourceCriteria);
        V target = buscarVertice(targetCriteria);
        if (!vertices.contains(source) || !vertices.contains(target)) {
            return null;
        }
        for (Arista<V,D> edge: edges.get(source)){
            if(edge.target().equals(target)){
                return edge.dato();
            }
        }
        return null;
    }
    public boolean agregarVertice(V vertex) {
        if (vertices.contains(vertex)) {
            return false;
        }
        vertices.add(vertex);
        edges.put(vertex, new HashSet<>());
        return true;
    }

    public V buscarVertice(Comparable<V> criterio) {
        for (V v : vertices) {
            if (criterio.compareTo(v) == 0) {
                return v;
            }
        }
        return null;
    }

    public boolean agregarArista(V source, V target, D dato) {
        if (!vertices.contains(source)) {
            this.agregarVertice(source);
        }
        if (!vertices.contains(target)){
            this.agregarVertice(target);
        }
        edges.get(source).add(new Arista<>(source, target, dato));
        return true;
    }

    public boolean eliminarArista(Comparable<V> source, Comparable<V> target) {
        V sourceVertice = buscarVertice(source);
        V targetVertice = buscarVertice(target);
        if (!vertices.contains(sourceVertice) || !vertices.contains(targetVertice)) {
            return false;
        }
        return edges.get(sourceVertice).removeIf(edge -> edge.target().equals(targetVertice));
    }

    public boolean removerVertice(Comparable<V> criteria) {
       V vertice = buscarVertice(criteria);
        if (!vertices.contains(vertice)) {
            return false;
        }
        V vertex = vertices.get(vertices.indexOf(vertice));
        vertices.remove(vertex);
        edges.remove(vertex);
        for (Set<Arista<V, D>> edgeSet : edges.values()) {
            edgeSet.removeIf(edge -> edge.target().equals(vertex));
        }
        return true;
    }

    public Set<V> vertices() {
        return new HashSet<>(vertices);
    }

    public Set<Edge<V, D>> aristas() {
        Set set = new HashSet<>();
        for (Set<Arista<V,D>> edgeSet : edges.values()) {
            set.addAll(edgeSet);
        }
        return set;
    }

    public boolean existeArista(Comparable<V> sourceCriteria, Comparable<V> targetCriteria) {
        V source = buscarVertice(sourceCriteria);
        V target = buscarVertice(targetCriteria);
        if (!vertices.contains(source) || !vertices.contains(target)) {
            return false;
        }
        for(Arista<V,D> edge: edges.get(source)){
            if(edge.target().equals(target)){
                return true;
            }
        }
        return false;
    }

    public List<Edge<V, D>> adyacencias(Comparable<V> verticeCriteria) {
        V vertice = buscarVertice(verticeCriteria);
        if (vertice == null) {
            return Collections.emptyList();
        }
        return new ArrayList<>(edges.get(vertice));
    }

    @Override
    public Edge<V, D> obtenerArista(Comparable<V> sourceCriteria, Comparable<V> targetCriteria) {
        V source = buscarVertice(sourceCriteria);
        V target = buscarVertice(targetCriteria);
        if (!vertices.contains(source) || !vertices.contains(target)) {
            return null;
        }
        for (Arista<V,D> edge: edges.get(source)){
            if(edge.target().equals(target)){
                return edge;
            }
        }
        return null;
    }

    public boolean esConexo() {
        return false;
    }

    public void vaciar() {
        edges.clear();
        vertices.clear();
    }

    public boolean tieneCiclos() {
        for (V vertice:vertices){
            if(tieneCiclosAux(vertice, new HashSet<>())){
                return true;
            }
        }
        return false;
    }

    private boolean tieneCiclosAux(V vertice, Set<V> visitados){
      //si el vertice ya esta en el camino actual
      // se ecnontro un ciclo.
        if(visitados.contains(vertice))
        return true;
      visitados.add(vertice);
      // exploramos los vertices asyacentes
      for(Arista<V,D> edge: edges.get(vertice)){
        // si algun vecino conduce a un ciclo
        //extedemos la busqueda a ese vecino
        if(tieneCiclosAux(edge.target(), visitados)){
          return true;
        }
      }
      //terminamos de visitar el vertice 
      // y lo sacamos del conjunto de visitados para permitir otras rutas
      visitados.remove(vertice);
      return false;
    }

    
}
