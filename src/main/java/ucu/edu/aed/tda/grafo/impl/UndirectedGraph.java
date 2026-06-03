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
    public boolean removerVertice(Comparable criteria) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removerVertice'");
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
    public boolean existeArista(Comparable sourceCriteria, Comparable targetCriteria) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existeArista'");
    }

    @Override
    public Edge obtenerArista(Comparable sourceCriteria, Comparable targetCriteria) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerArista'");
    }

    @Override
    public List adyacencias(Comparable verticeCriteria) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'adyacencias'");
    }

    @Override
    public boolean esConexo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'esConexo'");
    }

    @Override
    public void vaciar() {
        vertices.clear();
        aristas.clear();
    }

    @Override
    public boolean tieneCiclos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tieneCiclos'");
    }
    
}
