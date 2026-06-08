package ucu.edu.aed.tda.grafo.impl;

import ucu.edu.aed.tda.grafo.IUndirectedGraph;
import ucu.edu.aed.tda.grafo.model.edge.Edge;
import ucu.edu.aed.tda.grafo.model.edge.UndirectedEdge;
import java.util.*;

public class UndirectedGraph<V,D> implements IUndirectedGraph<V,D>{
    private final Set<V> vertices;
    private final Set<Edge<V,D>> aristas; //conjunto de aristas y vertices
    
    public UndirectedGraph(){ // creamos un grafo vacio
        vertices= new HashSet<>();
        aristas=new HashSet<>();
    }

    public UndirectedGraph(Collection<V> vertices, Collection<Edge<V,D>> aristas){
        this(); // inicializa las estructuras vacias 
        if (vertices!=null) {// agrega vertices que recibe
            this.vertices.addAll(vertices);
        }
        if (aristas!=null){ // idem vertices
            this.aristas.addAll(aristas);
        }
    }
    @Override
    public boolean agregarVertice(V vertex) {
        return vertices.add(vertex);
    }

    @Override
    public V buscarVertice(Comparable<V> criterio) {
        for(V vertex : vertices){ //buscamos que el vertice cumpla con el criterio recibido
            if(criterio.compareTo(vertex)==0){
                return vertex;
            }
        }
        return null;
    }

    @Override
    public boolean agregarArista(V source, V target, D dato) {
        // arista conecta con ambos vertices
        boolean added= aristas.add(new UndirectedEdge<>(source,target,dato));
        if (added) { // si se agrego la arista, nos aseguramos que existan los extremos de esta 
            vertices.add(source);
            vertices.add(target);
        }
        return added;
    }

    @Override
    public boolean eliminarArista(Comparable<V> source, Comparable<V> target) {
      
        for(Edge<V,D> arista:aristas){ // buscamos la arista sin importar el orden de los vertices
        V origen= arista.source();
        V destino= arista.target();

        boolean cond=(source.compareTo(origen) ==0 && target.compareTo(destino)==0 || (source.compareTo(destino)==0 && target.compareTo(origen)==0));
        if(cond){
            return aristas.remove(arista);
        }
      }
      return false;
    }

    @Override
    public boolean removerVertice(Comparable<V> criteria) {
        Set<V> verticesARemover= new HashSet<>();
        Set<Edge<V,D>> aristaARemover= new HashSet<>();

        for (V vertex: vertices){
            if(criteria.compareTo(vertex)==0){
                verticesARemover.add(vertex);
            }
        }
        for(Edge<V,D> arista: aristas){
            V org= arista.source();
            V dst= arista.target();
            boolean condition= verticesARemover.contains(org) || verticesARemover.contains(dst);
            if (condition) {
                aristaARemover.add(arista);
            }
            
        }
        boolean b1= vertices.removeAll(verticesARemover);
        boolean b2= aristas.removeAll(aristaARemover);

        return b1 || b2;
    }

    @Override
    public Set<V> vertices() {
        return new HashSet<>(vertices);
    }

    @Override
    public Set<Edge<V,D>> aristas() {
        return new HashSet<>(aristas);
    }

    @Override
    public boolean existeArista(Comparable<V> sourceCriteria, Comparable<V> targetCriteria) {
        for(Edge<V,D> arista:aristas){
            V org= arista.source();
            V dst= arista.target();
            boolean condition= (sourceCriteria.compareTo(org)==0 && targetCriteria.compareTo(dst)==0 || (sourceCriteria.compareTo(dst)==0 && targetCriteria.compareTo(org)==0));
            if (condition){
                return true;
            }
        }
        return false;
    }

    @Override
    public Edge<V, D> obtenerArista(Comparable<V> sourceCriteria, Comparable<V> targetCriteria) {
        for(Edge<V,D> arista:aristas){
            V org= arista.source();
            V dst= arista.target();
            boolean condition= (sourceCriteria.compareTo(org)==0 && targetCriteria.compareTo(dst)==0 || (sourceCriteria.compareTo(dst)==0 && targetCriteria.compareTo(org)==0));
            if (condition){
                return arista;
            }
        }
        return null;
    }

    @Override
    public List<Edge<V,D>> adyacencias(Comparable<V> verticeCriteria) {
        List<Edge<V,D>> ady= new LinkedList<>();
        for(Edge<V,D> arista:aristas){
            V org= arista.source();
            V dst= arista.target();
            if (verticeCriteria.compareTo(org)==0 || verticeCriteria.compareTo(dst)==0) {
                ady.add(arista);
                
            }
        }
        return ady;
    }

    @Override
    public boolean esConexo() {
       int size= vertices.size();
       if(size==0){
        return false;
       }
       Set<V> visitados= new HashSet<>();
       for(V vertex: vertices){
        esConexoAux(vertex,visitados);
        if (size!= visitados.size()) {
            return false; 
            }
        }
        return true;
    }

    private void esConexoAux(V vertice, Set<V> visitados){
        if (visitados.contains(vertice)) {
            return;
        }
        visitados.add(vertice);
        for(Edge<V,D> arista:adyacencias((construirComparable(vertice)))){
            esConexoAux(arista.target(), visitados);
        }
    }
       
    

    @Override
    public void vaciar() {
        vertices.clear();
        aristas.clear();
    }

    @Override
    public boolean tieneCiclos() {
        Set<V> visitados= new HashSet<>();
        for(V vertex: vertices){
            if (!visitados.contains(vertex)) {
                if (tieneCiclosAux(vertex,visitados,null)) {
                    return true;
                }
            
            }
        }
        return false;
    }

    public boolean tieneCiclosAux(V nodo, Set<V> visitados, V padre){
        visitados.add(nodo);
        for(Edge<V,D> edge: adyacencias(construirComparable(nodo))){
            V vecino;
            if (edge.source().equals(nodo)) {
                vecino= edge.target();
            }
            else{
                vecino=edge.source();
            }
            if (!visitados.contains(vecino)) {
                if (tieneCiclosAux(vecino, visitados, nodo)) {
                    return true;
                }
            }else if (!vecino.equals(padre)) {
                return true;
            }
        }
        return false;
    }
    
}
