package pl.edu.agh.to2.parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import pl.edu.agh.to2.model.Board;
import pl.edu.agh.to2.model.Exercise;
import pl.edu.agh.to2.commands.Command;

import java.util.LinkedList;
import java.util.List;

public class ExerciseParser {
    private JSONArray exercisesJSON;
    private LinkedList<String> parseErrors;

    public ExerciseParser(String exercisesText) {
        try {
            exercisesJSON = new JSONArray(exercisesText);
            parseErrors = new LinkedList<>();
        } catch (JSONException e) {
            throw new IllegalArgumentException("Passed string is not a valid JSON array");
        }
    }

    public List<Exercise> getAllExercises() {
        LinkedList<Exercise> result = new LinkedList<>();
        for(int i = 0; i < exercisesJSON.length(); i++) {
            try {
                JSONObject singleExerciseJSON = exercisesJSON.getJSONObject(i);
                String commandsText = singleExerciseJSON.getString("commands");
                String description = singleExerciseJSON.getString("description");
                Exercise e = parseSingleExercise(commandsText, description);
                result.add(e);
            } catch (IllegalArgumentException | JSONException e) {
                parseErrors.add(e.getMessage());
            }
        }
        return result;
    }

    public List<String> getParseErrors() {
        return parseErrors;
    }

    private Exercise parseSingleExercise(String commandsText, String description) {
        try {
            Board board = new Board();
            CommandParser parser = new CommandParser(commandsText, board);
            List<Command> commands = parser.parseCommands();
            board.executeCommands(commands);
            return new Exercise(board.getVectors(), description, board.getCommandsNumber());
        } catch (ExceptionInInitializerError e) {
            throw new IllegalArgumentException("Passed text is not a valid commands text", e);
        }
    }
}
