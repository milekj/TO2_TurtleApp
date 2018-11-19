package pl.edu.agh.to2.model;
import java.util.Set;

public class Exercise {
    private Set<Vector> vectors;

    public Exercise(Set<Vector> vectors) {
        this.vectors = vectors;
    }

    public boolean vectorsPass(Set<Vector> testedVectors) {
        return vectors.equals(testedVectors);
    }

    @Override
    public String toString() {
        return "Exercise: {" + vectors + '}';
    }
}
