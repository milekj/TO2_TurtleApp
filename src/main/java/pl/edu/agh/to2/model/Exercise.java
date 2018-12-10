package pl.edu.agh.to2.model;
import pl.edu.agh.to2.model.geometry.DisjointVectorsCollection;
import pl.edu.agh.to2.model.geometry.Utilities;
import pl.edu.agh.to2.model.geometry.Vector;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static pl.edu.agh.to2.model.ExerciseGrade.*;

public class Exercise implements Serializable {
    private Set<Vector> vectors;
    private ExerciseGrade grade;
    private int commandsNumber;

    public Exercise(List<Vector> vectors, int commandsNumber) {
        this.grade = UNSOLVED;
        DisjointVectorsCollection disjointVectors = new DisjointVectorsCollection(vectors);
        this.vectors = disjointVectors.getVectorsSet();
        this.commandsNumber = commandsNumber;
    }

    public ExerciseGrade evaluate(List<Vector> testedVectors, int commandsNumber) {
        ExerciseGrade newGrade;
        DisjointVectorsCollection disjointVectors = new DisjointVectorsCollection(testedVectors);
        Set<Vector> testedVectorsSet = disjointVectors.getVectorsSet();
        if (vectors.equals(testedVectorsSet)) {
            if(commandsNumber <= this.commandsNumber)
                newGrade = IDEAL;
            else
                newGrade = SOLVED;
        }
        else
            newGrade = UNSOLVED;

        if (newGrade.compareTo(grade) > 0) {
            grade = newGrade;
        }

        return grade;
    }

    public Set<Vector> getVectors() {
        return vectors;
    }

    public void clearGrade() {
        grade = UNSOLVED;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Exercise)) return false;
        Exercise that = (Exercise) o;
        return this.vectors.equals(that.vectors) &&
                this.commandsNumber == that.commandsNumber &&
                this.grade == that.grade;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vectors);
    }

    @Override
    public String toString() {
        return "Exercise: {" + vectors + ", " + grade + ", " + commandsNumber + "}";
    }
}
