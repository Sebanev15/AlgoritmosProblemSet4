package ucu.edu.aed.tda.grafo.impl;

import ucu.edu.aed.tda.grafo.model.result.IDijkstraResult;

import java.util.List;

public class DijkstraResult<V> implements IDijkstraResult<V> {


    @Override
    public double getCost(V otherVertex) {
        return 0;
    }

    @Override
    public List getPath(V otherVertex) {
        return List.of();
    }
}
