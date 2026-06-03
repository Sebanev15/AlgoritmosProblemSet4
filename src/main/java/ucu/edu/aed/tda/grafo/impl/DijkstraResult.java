package ucu.edu.aed.tda.grafo.impl;

import ucu.edu.aed.tda.grafo.model.result.IDijkstraResult;

import java.util.*;

public class DijkstraResult<V> implements IDijkstraResult<V> {
    private final V origen;
    private final Map<V, Double> costos;
    private final Map<V, V> predecesores;

    public DijkstraResult(V origen, Map<V, Double> costos, Map<V,V> predecesores) {
        this.origen = origen;
        this.costos = costos;
        this.predecesores = predecesores;
    }

    @Override
    public double getCost(V otherVertex) {
        return costos.get(otherVertex); // Si no hay camino, se puede considerar un costo infinito
    }

    @Override
    public List<V> getPath(V otherVertex) {
        if(!predecesores.containsKey(otherVertex) && !origen.equals(otherVertex)){
            return null;
        }
        List<V> resultado = new ArrayList<>();
        V actual = otherVertex;
        while(actual!=null && !actual.equals(origen)){
            resultado.add(0, actual);
            actual=predecesores.get(actual);
        }
        resultado.add(0, origen);
        return resultado;
    }
}
