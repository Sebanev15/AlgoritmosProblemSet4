package ucu.edu.aed.tda.grafo.impl;

import ucu.edu.aed.tda.grafo.model.edge.Edge;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

public class VertexJointPoint<V,D>{
    public V vertice;          // el vértice que representa
    public boolean visitado;   // si ya fue visitado
    public int numBp;
    public int numBajo;
    public HashSet<Edge<V, D>> adyacentes;

    public VertexJointPoint(V vertice, HashSet<Edge<V, D>> adyacentes) {
        this.vertice = vertice;
        this.visitado = false;
        this.numBp = 0;
        this.numBajo = 0;
        this.adyacentes = adyacentes;
    }

    public void puntosArticulacion(LinkedList<V> puntos, int[] count, Map<V, VertexJointPoint<V,D>> wrappers) {
        this.visitado = true;
        count[0]++;
        this.numBp = count[0];
        this.numBajo = count[0];
        LinkedList<VertexJointPoint<V,D>> hijos = new LinkedList<>();

        for (Edge<V,D> arista : adyacentes) {
            V adyacenteVertice = arista.target();
            VertexJointPoint<V,D> adyacente = wrappers.get(adyacenteVertice);

            if (!adyacente.visitado) {
                adyacente.puntosArticulacion(puntos, count, wrappers);
                hijos.add(adyacente);
                this.numBajo = Math.min(this.numBajo, adyacente.numBajo);
            } else {
                this.numBajo = Math.min(this.numBajo, adyacente.numBp);
            }
        }

        if (this.numBp > 1) {
            for (VertexJointPoint<V,D> hijo : hijos) {
                if (hijo.numBajo >= this.numBp) {
                    puntos.add(this.vertice);
                    break;
                }
            }
        } else {
            if (hijos.size() > 1) {
                puntos.add(this.vertice);
            }
        }
    }
}
