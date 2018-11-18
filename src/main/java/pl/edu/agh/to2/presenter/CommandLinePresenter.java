package pl.edu.agh.to2.presenter;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import pl.edu.agh.to2.parser.Command;
import pl.edu.agh.to2.parser.CommandParser;

import java.util.List;

public class CommandLinePresenter {

    @FXML
    private TextArea commandsInput;

    @FXML
    private void onReset() {
        commandsInput.clear();
    }

    @FXML
    private void onSubmit() {
        List<Command> commands = new CommandParser(commandsInput.getText()).getCommands();
        // TODO call BoardPresenter.handleCommands somehow
    }
}
