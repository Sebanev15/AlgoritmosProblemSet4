package ucu.edu.aed.tda.grafo.impl;
import ucu.edu.aed.tda.grafo.model.edge.Edge;

import java.util.*;

import ucu.edu.aed.tda.grafo.IDirectedIGraph;


public class DirectedGraph <V,D> implements IDirectedIGraph <V,D>{
    private List<V> vertices;
    private Map<V,Set<Arista<V,D>>> edges;
    
    public DirectedGraph(){
        vertices =new ArrayList<>();
        edges = new HashMap<>();
    }

    @Override
    public Set <V> successors(Comparable<V> criterio){
        Set<V> resultado= new HashSet<>();
        // Usamos hashset para ecitar repetidos y tener busquedas e inserciones
        // rapidas
        for (Set<Arista<V,D>> edges : edges.values() ){
            for(Arista<V,D> edge: edges){
                if(edge.source().equals(criterio)){
                    resultado.add(edge.target());
                }
            }
        }
        return resultado;
    }

    @Override
    public Set<V> predecessors(Comparable<V> criterio){
        Set <V> resultado= new HashSet<>();

        for (Set<Arista<V,D>> edges : edges.values() ){
            for(Arista<V,D> edge: edges){
                if(edge.source().equals(criterio)){
                    resultado.add(edge.target());
                }
            }
        }
        return resultado;
    }

    public boolean agregarVertice(V vertex) {
        if (vertices.contains(vertex)) {
            return false;
        }
        if(edges.containsKey(vertex)){
            return false;
        }
        vertices.add(vertex);
        edges.put(vertex, new HashSet<>());
        return true;
    }

    public V buscarVertice(Comparable<V> criterio) {
        if(vertices.contains(criterio)){
            return vertices.get(vertices.indexOf(criterio));
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
        if (!vertices.contains(source) || !vertices.contains(target)) {
            return false;
        }
        return edges.get(source).removeIf(edge -> edge.target().equals(target));
    }

    public boolean removerVertice(Comparable<V> criteria) {
        if (!vertices.contains(criteria)) {
            return false;
        }
        V vertex = vertices.get(vertices.indexOf(criteria));
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
        if (!vertices.contains(sourceCriteria) || !vertices.contains(targetCriteria)) {
            return false;
        }
        for(Arista<V,D> edge: edges.get(sourceCriteria)){
            if(edge.target().equals(targetCriteria)){
                return true;
            }
        }
        return false;
    }

    public List<Edge<V, D>> adyacencias(Comparable<V> verticeCriteria) {
        return List.of();
    }

    public Edge<V, D> obtenerArista(Comparable<V> sourceCriteria, Comparable<V> targetCriteria) {
        for (Arista<V,D> edge: edges.get(sourceCriteria)){
            if(edge.target().equals(targetCriteria)){
                return edge;
            }
        }
        return null;
    }

    public boolean esConexo() {
        return false;
    }

    public void vaciar() {
        this.edges = new HashMap<>();
        this.vertices = new ArrayList<>();
    }

    public boolean tieneCiclos() {
        return false;
    }

    
}
