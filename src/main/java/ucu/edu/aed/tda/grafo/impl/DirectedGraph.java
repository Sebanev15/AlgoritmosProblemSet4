package ucu.edu.aed.tda.grafo.impl;
import ucu.edu.aed.tda.grafo.model.edge.Edge;

import java.util.*;

import ucu.edu.aed.tda.grafo.IDirectedIGraph;
import ucu.edu.aed.tda.grafo.model.edge.DirectedEdge;


public class DirectedGraph <V,D> implements IDirectedIGraph <V,D> {
    private List<V> vertices;
    private List<Edge<V,D>> edges ;
    
    public DirectedGraph(){
        vertices =new ArrayList<>();
        edges =new ArrayList<>();
    }

    @Override
    public Set <V> successors(Comparable<V> criterio){
        Set<V> resultado= new HashSet<>();
        // Usamos hashset para ecitar repetidos y tener busquedas e inserciones
        // rapidas
        for (Edge<V,D> edge : edges ){
            if (criterio.compareTo(edge.source())==0){
                resultado.add(edge.target());
            }
        }
        return resultado;
    }

    @Override
    public Set<V> predecessors(Comparable<V> criterio){
        Set <V> resultado= new HashSet<>();

        for (Edge <V,D> edge : edges){
            //se verifica si el vertice de origen 
            //coincide con el criterio recibido
            if (criterio.compareTo(edge.target())==0){
                //si coincide el criterio
                //se agrega el vert al cjto de predecesores
                resultado.add(edge.source());
            }
        }
        return resultado;//cjto de predecesores 
    }
    @Override
    
}
