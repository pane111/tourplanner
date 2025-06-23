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

public class AddController {

    public TextField toField;
    public TextField fromField;
    public TextArea descriptionField;
    public Label errorField;
    public TextField nameField;

    @Getter
    private AddEditViewModel viewModel;

    public void initialize() {
        viewModel = new AddEditViewModel();
        Mediator.getInstance().addEdit=viewModel;
        nameField.textProperty().bindBidirectional(viewModel.getName());
        fromField.textProperty().bindBidirectional(viewModel.getFrom());
        toField.textProperty().bindBidirectional(viewModel.getTo());
        descriptionField.textProperty().bindBidirectional(viewModel.getDescription());
    }

    @FXML
    private void onAdd() throws IOException {

        TourDto t = viewModel.createTour();
        if (t!=null)
        {
            Mediator.getInstance().tourService.createTour(t);
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
