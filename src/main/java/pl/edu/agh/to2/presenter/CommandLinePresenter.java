package pl.edu.agh.to2.presenter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

public class CommandLinePresenter {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Label commandsHistory;

    @FXML
    private TextField commandInput;

    @FXML
    private void onEnter() {
        String command = commandInput.getText();

        // TODO call parser with command argument here

        if (!commandsHistory.getText().equals("")) {
            command = '\n' + command;
        }

        commandsHistory.setText(commandsHistory.getText() + command);
        commandInput.clear();
        scrollPane.setVvalue(1);
    }
}
