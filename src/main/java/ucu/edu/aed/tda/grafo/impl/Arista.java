package ucu.edu.aed.tda.grafo.impl;

import ucu.edu.aed.tda.grafo.model.edge.Edge;

public class Arista<T, D> implements Edge<T, D> {

    private T source;
    private T target;
    private D dato;
    private boolean directed;

    // Constructor
    public Arista(T source, T target, D dato, boolean directed) {
        this.source = source;
        this.target = target;
        this.dato = dato;
        this.directed = directed;
    }

    // Constructor simplificado (asume que es dirigido)
    public Arista(T source, T target, D dato) {
        this(source, target, dato, true);
    }

    @Override
    public T source() {
        return source;
    }

    @Override
    public T target() {
        return target;
    }

    @Override
    public D dato() {
        return dato;
    }

    @Override
    public boolean directed() {
        return directed;
    }

    // Métodos adicionales útiles
    @Override
    public String toString() {
        return String.format("%s -> %s [%s]", source, target, dato);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Arista)) return false;
        Arista<?, ?> that = (Arista<?, ?>) o;
        return source.equals(that.source) &&
                target.equals(that.target);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(source, target);
    }
}
