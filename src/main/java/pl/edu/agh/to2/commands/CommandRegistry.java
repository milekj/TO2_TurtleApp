package pl.edu.agh.to2.commands;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class CommandRegistry {
    private ObservableList<Command> commandsStack;

    public CommandRegistry() {
        commandsStack = FXCollections.observableArrayList();
    }

    public void storeAndExecute(List<Command> commands) {
        for (Command c : commands) {
            c.execute();
            commandsStack.add(c);
        }
    }

    public ObservableList<Command> getCommandsStack() {
        return commandsStack;
    }

    public int getCommandsNumber() {
        int commandsNumber = 0;
        for (Command c : commandsStack) {
            commandsNumber += c.getCommandsNumber();
        }
        return commandsNumber;
    }
}
