package pl.edu.agh.to2.model;
import pl.edu.agh.to2.model.geometry.DisjointVectorsCollection;
import pl.edu.agh.to2.model.geometry.Vector;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import static pl.edu.agh.to2.model.ExerciseGrade.*;

public class Exercise {
    private Set<Vector> vectors;
    private ExerciseGrade grade;
    private int commandsNumber;

    public Exercise(List<Vector> vectors) {
        this();
        DisjointVectorsCollection disjointVectors = new DisjointVectorsCollection(vectors);
        this.vectors = disjointVectors.getVectorsSet();
    }

    private Exercise() {
        this.grade = UNSOLVED;
        commandsNumber = 0;
    }

    public boolean vectorsPass(List<Vector> testedVectors) {
        DisjointVectorsCollection disjointVectors = new DisjointVectorsCollection(testedVectors);
        Set<Vector> testedVectorsSet = disjointVectors.getVectorsSet();
        return vectors.equals(testedVectorsSet);
    }

    public void evaluate(List<Vector> testedVectors, int commandsNumber) {
        DisjointVectorsCollection disjointVectors = new DisjointVectorsCollection(testedVectors);
        Set<Vector> testedVectorsSet = disjointVectors.getVectorsSet();
        if (vectors.equals(testedVectorsSet)) {
            if(commandsNumber <= this.commandsNumber)
                grade = IDEAL;
            grade = SOLVED;
        }
        grade = UNSOLVED;
    }

    public Set<Vector> getVectors() {
        return vectors;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Exercise)) return false;
        Exercise that = (Exercise) o;
        return this.vectors.equals(that.vectors) &&
                this.commandsNumber == that.commandsNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vectors);
    }

    @Override
    public String toString() {
        return "Exercise: {" + vectors + '}';
    }
}
