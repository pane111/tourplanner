package Controller;

import Model.TourDto;
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
    public Label idField;

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
        idField.textProperty().bindBidirectional(viewModel.getId());


    }
    public void fillFields(TourDto tourDto) {
        nameField.setText(tourDto.getName());
        fromField.setText(tourDto.getFrom());
        toField.setText(tourDto.getTo());
        descriptionField.setText(tourDto.getDescription());
        distField.setText(tourDto.getDistance().toString());
        durationField.setText(tourDto.getEstimatedTime());
        descriptionField.setText(tourDto.getDescription());
        idField.setText(tourDto.getId().toString());
    }

    @FXML
    private void onSave() throws IOException {

        TourDto t = viewModel.createTour();
        if (t!=null)
        {
            Mediator.getInstance().tourService.updateTour(t);
            Mediator.getInstance().list.refresh();
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
