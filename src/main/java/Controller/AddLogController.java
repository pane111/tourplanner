package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddLogController {

    @FXML private TextField dateField;
    @FXML private TextField timeTakenField;
    @FXML private TextField distanceField;
    @FXML private TextField ratingField;
    @FXML private TextField difficultyField;
    @FXML private TextArea commentField;
    @FXML private Label errorField;

    @FXML
    private void onAddLog() {
        System.out.println("Add Log clicked");
        Stage stage = (Stage) dateField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onCancel() {
        Stage stage = (Stage) dateField.getScene().getWindow();
        stage.close();
    }
}
