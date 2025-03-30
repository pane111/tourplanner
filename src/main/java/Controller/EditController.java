package Controller;

import Model.Tour;
import ViewModel.AddEditViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.IOException;

public class EditController {

    public TextField toField;
    public TextField fromField;
    public TextField distField;
    public TextField durationField;
    public TextArea descriptionField;
    public Label errorField;
    public TextField nameField;

    @Getter
    private AddEditViewModel viewModel;

    public void initialize() {
        Mediator.getInstance().edit=this;
        viewModel = new AddEditViewModel();
        nameField.textProperty().bindBidirectional(viewModel.getName());
        fromField.textProperty().bindBidirectional(viewModel.getFrom());
        toField.textProperty().bindBidirectional(viewModel.getTo());
        distField.textProperty().bindBidirectional(viewModel.getDistance());
        descriptionField.textProperty().bindBidirectional(viewModel.getDescription());
        durationField.textProperty().bindBidirectional(viewModel.getEstimatedTime());


    }
    public void fillFields(Tour tour) {
        nameField.setText(tour.getName());
        fromField.setText(tour.getFrom());
        toField.setText(tour.getTo());
        descriptionField.setText(tour.getDescription());
        distField.setText(tour.getDistance().toString());
        durationField.setText(tour.getEstimatedTime());
        descriptionField.setText(tour.getDescription());
    }

    @FXML
    private void onSave() throws IOException {

        Tour t = viewModel.createTour();
        if (t!=null)
        {
            Mediator.getInstance().list.removeTour(Mediator.getInstance().listController.getLastSelectedItem());
            Mediator.getInstance().list.addTour(t);
            Stage stage = (Stage) toField.getScene().getWindow();
            stage.close();

            System.out.println("Tour added: " + nameField.getText());
        }
        else
        {
            errorField.setText("Please make sure you have filled out all fields and entered a valid distance!");
        }
    }

    public void onCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) toField.getScene().getWindow();
        stage.close();
    }
}
