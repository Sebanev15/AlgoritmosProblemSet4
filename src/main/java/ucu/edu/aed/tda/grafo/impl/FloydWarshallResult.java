package ucu.edu.aed.tda.grafo.impl;

import ucu.edu.aed.tda.grafo.model.result.IFloydWarshallResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class FloydWarshallResult<V> implements IFloydWarshallResult<V> {
    private final Map<V, Map<V, Double>> distancias;
    private final Map<V, Map<V, V>> siguiente;

    public FloydWarshallResult(Map<V, Map<V, Double>> distancias, Map<V, Map<V, V>> siguiente) {
        this.distancias = distancias;
        this.siguiente = siguiente;
    }

    @Override
    public List<V> getPath(V source, V target) {
        if (siguiente.get(source).get(target) == null) return Collections.emptyList();
        List<V> path = new ArrayList<>();
        path.add(source);
        while (!source.equals(target)) {
            source = siguiente.get(source).get(target);
            path.add(source);
        }
        return path;
    }

    @Override
    public double getCost(V source, V target) {
        return distancias.get(source).get(target);
    }

    @Override
    public boolean connected(V source, V target) {
        return distancias.get(source).get(target) != Double.POSITIVE_INFINITY;
    }
}
