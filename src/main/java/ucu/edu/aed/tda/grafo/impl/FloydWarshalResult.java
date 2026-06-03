package ucu.edu.aed.tda.grafo.impl;

import ucu.edu.aed.tda.grafo.model.result.IFloydWarshallResult;

import java.util.List;

public class FloydWarshalResult<V> implements IFloydWarshallResult<V> {
    @Override
    public List<V> getPath(V source, V target) {
        return List.of();
    }

    @Override
    public double getCost(V source, V target) {
        return 0;
    }

    @Override
    public boolean connected(V source, V target) {
        return false;
    }
}
