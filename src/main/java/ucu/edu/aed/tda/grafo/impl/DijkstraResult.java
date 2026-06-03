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
        V predecesor = predecesores.get(otherVertex);
        List<V> resultado = new ArrayList<>();
        if (predecesor == null) {
           return null;
        }
        resultado.addFirst(otherVertex);
        while(predecesor!=origen){
            resultado.addFirst(predecesor);
            predecesor=predecesores.get(predecesor);
        }
        resultado.addFirst(origen);
        return resultado;
    }
}
