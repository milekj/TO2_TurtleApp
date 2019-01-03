package pl.edu.agh.to2.model;

import pl.edu.agh.to2.parsers.ExerciseParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ExerciseFileReader {
    private String filePath;

    public ExerciseFileReader(String filePath) {
        this.filePath = filePath;
    }

    public List<Exercise> readExercises() throws IllegalArgumentException {
        try {
            Path path = Paths.get(filePath);
            String text = Files.lines(path)
                    .collect(Collectors.joining("\n"));
            ExerciseParser parser = new ExerciseParser(text);
            return parser.getAllExercises();
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot open: " + filePath, e);
        }
    }
}
