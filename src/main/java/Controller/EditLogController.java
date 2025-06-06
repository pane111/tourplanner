package Controller;

import Model.TourLogDto;
import ViewModel.AddEditLogViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditLogController {
    @FXML
    private TextField dateField;
    @FXML private TextField timeTakenField;
    @FXML private TextField distanceField;
    @FXML private TextField ratingField;
    @FXML private TextField difficultyField;
    @FXML private TextArea commentField;
    @FXML private Label errorField;
    @FXML private Label idField;

    private AddEditLogViewModel viewModel;

    public void initialize() {
        Mediator.getInstance().editLog=this;
        viewModel = new AddEditLogViewModel();
        timeTakenField.textProperty().bindBidirectional(viewModel.getTime());
        distanceField.textProperty().bindBidirectional(viewModel.getDistance());
        ratingField.textProperty().bindBidirectional(viewModel.getRating());
        difficultyField.textProperty().bindBidirectional(viewModel.getDifficulty());
        commentField.textProperty().bindBidirectional(viewModel.getComment());
        dateField.textProperty().bindBidirectional(viewModel.getDate());
        idField.textProperty().bindBidirectional(viewModel.getId());
    }

    public void fillFields()
    {
        TourLogDto log = Mediator.getInstance().logList.getLastSelectedItem();
        dateField.setText(log.getDate());
        timeTakenField.setText(log.getTime());
        distanceField.setText(log.getDistance());
        ratingField.setText(log.getRating().toString());
        difficultyField.setText(log.getDifficulty().toString());
        commentField.setText(log.getComment());
        idField.setText(String.valueOf(log.getLogId()));

    }

    @FXML
    private void onSaveLog() {
        System.out.println("Save Log clicked");
        TourLogDto t = viewModel.createLog();
        if (t != null) {
            Mediator.getInstance().logList.updateLog(t);


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
