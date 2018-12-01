package pl.edu.agh.to2.presenter;

import javafx.fxml.FXML;
import pl.edu.agh.to2.model.Board;

public class MainPresenter {
    @FXML
    private BoardPresenter boardController;

    @FXML
    private CommandLinePresenter commandLineController;

    public void setBoard(Board board) {
        boardController.setBoard(board);
        commandLineController.setBoard(board);
    }

}
