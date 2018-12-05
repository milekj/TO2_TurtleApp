package pl.edu.agh.to2.parser;

import pl.edu.agh.to2.model.Board;
import pl.edu.agh.to2.model.Exercise;
import pl.edu.agh.to2.model.commands.Command;

import java.util.LinkedList;
import java.util.List;

public class ExerciseParser {
    private String exercisesText;
    private LinkedList<String> parseErrors;

    public ExerciseParser(String exercisesText) {
        this.exercisesText = exercisesText;
        parseErrors = new LinkedList<>();
    }

    public LinkedList<Exercise> getAllExercises() {
        LinkedList<Exercise> result = new LinkedList<>();
        String[] singleExerciseTexts = splitToSingleExercises();
        for(String s: singleExerciseTexts) {
            try {
                Exercise e = parseSingleExercise(s);
                result.add(e);
            } catch (IllegalArgumentException exception) {
                parseErrors.add(s);
            }
        }
        return result;
    }

    public LinkedList<String> getParseErrors() {
        return parseErrors;
    }

    private Exercise parseSingleExercise(String exerciseText) {
        try {
            CommandParser parser = new CommandParser(exerciseText);
            Board board = new Board();
            List<Command> commands = parser.parseCommands();
            board.executeCommands(commands);
            return new Exercise(board.getVectors(), commands.size());
        } catch (ExceptionInInitializerError e) {
            throw new IllegalArgumentException("Passed text is not a valid exercise text", e);
        }
    }

    private String[] splitToSingleExercises() {
        String delimiter = "/";
        return exercisesText.split(delimiter);
    }


}
