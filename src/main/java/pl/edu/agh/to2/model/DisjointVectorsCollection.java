package pl.edu.agh.to2.model;

import java.util.*;

public class DisjointVectorsCollection {
    private List<Vector> vectors;

    public DisjointVectorsCollection(List<Vector> vectors) {
        this();
        addAll(vectors);
    }

    public DisjointVectorsCollection() {
        vectors = new LinkedList<>();
    }

    public void add(Vector vectorToAdd) {
        Vector current = vectorToAdd;
        while (current != null) {
            current = mergeWithAll(current);
        }
    }

    private Vector mergeWithAll(Vector current) {
        Iterator<Vector> it = vectors.iterator();
        while (it.hasNext()) {
            Vector vector = it.next();
            Vector merged = vector.mergeIfOverlapping(current);
            if (merged == null)
                continue;
            it.remove();
            if (!merged.equals(current))
                return merged;
        }
        vectors.add(current);
        return null;
    }

    public void addAll(List<Vector> vectors) {
        for(Vector v : vectors)
            add(v);
    }

    public Set<Vector> getVectorsSet() {
        return new HashSet<>(vectors);
    }
}
