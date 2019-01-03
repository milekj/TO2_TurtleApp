package pl.edu.agh.to2.persistence;

import pl.edu.agh.to2.model.ExerciseFileReader;
import pl.edu.agh.to2.model.ExercisesManager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ExerciseMangerSerializer {
    private static String savedExercisesFilename = "ex.dat";
    private static String newExercisesFilename = "ex.txt";

    public static ExercisesManager getExerciseManager() {
        ExercisesManager manager;
        ObjectInputStream ois = null;
        try {
            FileInputStream fin = new FileInputStream(savedExercisesFilename);
            ois = new ObjectInputStream(fin);
            manager = (ExercisesManager) ois.readObject();
            ois.close();
        } catch (Exception e) {
            ExerciseFileReader reader = new ExerciseFileReader(newExercisesFilename);
            manager = new ExercisesManager(reader.readExercises());
        }
        return manager;
    }

    public static void saveExcercises(ExercisesManager manager) {
        ObjectOutputStream oos = null;
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream(savedExercisesFilename, false);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(manager);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
