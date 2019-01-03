package pl.edu.agh.to2.parsers;

import pl.edu.agh.to2.model.Board;
import pl.edu.agh.to2.model.Exercise;
import pl.edu.agh.to2.commands.Command;

import java.util.LinkedList;
import java.util.List;

public class ExerciseParser {
    private String exercisesText;
    private LinkedList<String> parseErrors;

    public ExerciseParser(String exercisesText) {
        this.exercisesText = exercisesText;
        parseErrors = new LinkedList<>();
    }

    public List<Exercise> getAllExercises() {
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

    public List<String> getParseErrors() {
        return parseErrors;
    }

    private Exercise parseSingleExercise(String exerciseText) {
        try {
            Board board = new Board();
            CommandParser parser = new CommandParser(exerciseText, board);
            List<Command> commands = parser.parseCommands();
            board.executeCommands(commands);
            return new Exercise(board.getVectors(), board.getCommandsNumber());
        } catch (ExceptionInInitializerError e) {
            throw new IllegalArgumentException("Passed text is not a valid exercise text", e);
        }
    }

    private String[] splitToSingleExercises() {
        String delimiter = "/";
        return exercisesText.split(delimiter);
    }


}
