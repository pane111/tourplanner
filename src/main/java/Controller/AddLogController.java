package Controller;

import Model.TourLogDto;
import ViewModel.AddEditLogViewModel;
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


    private AddEditLogViewModel viewModel;

    public void initialize() {
        viewModel = new AddEditLogViewModel();
        dateField.textProperty().bindBidirectional(viewModel.getDate());
        timeTakenField.textProperty().bindBidirectional(viewModel.getTime());
        distanceField.textProperty().bindBidirectional(viewModel.getDistance());
        ratingField.textProperty().bindBidirectional(viewModel.getRating());
        difficultyField.textProperty().bindBidirectional(viewModel.getDifficulty());
        commentField.textProperty().bindBidirectional(viewModel.getComment());

    }

    @FXML
    private void onAddLog() {
        System.out.println("Add Log clicked");
        TourLogDto t = viewModel.createLog();
        if (t != null) {
            Mediator.getInstance().logList.add(t);
            Stage stage = (Stage) dateField.getScene().getWindow();
            stage.close();
        }
        else
        {
            errorField.setText("Please fill out all fields and ensure they are formatted as specified!");
        }
    }


    @FXML
    private void onCancel() {
        Stage stage = (Stage) dateField.getScene().getWindow();
        stage.close();
    }
}
