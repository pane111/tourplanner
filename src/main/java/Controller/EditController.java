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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class EditController {

    public TextField toField;
    public TextField fromField;
    public TextArea descriptionField;
    public Label errorField;
    public TextField nameField;
    public Label idField;

    @Getter
    private AddEditViewModel viewModel;
    Logger logger = LogManager.getLogger(EditController.class);
    public void initialize() {
        Mediator.getInstance().edit=this;
        viewModel = new AddEditViewModel();
        nameField.textProperty().bindBidirectional(viewModel.getName());
        fromField.textProperty().bindBidirectional(viewModel.getFrom());
        toField.textProperty().bindBidirectional(viewModel.getTo());
        descriptionField.textProperty().bindBidirectional(viewModel.getDescription());
        idField.textProperty().bindBidirectional(viewModel.getId());


    }
    public void fillFields(TourDto tourDto) {
        nameField.setText(tourDto.getName());
        fromField.setText(tourDto.getFrom());
        toField.setText(tourDto.getTo());
        descriptionField.setText(tourDto.getDescription());
        descriptionField.setText(tourDto.getDescription());
        idField.setText(tourDto.getId().toString());
    }

    @FXML
    private void onSave() throws IOException {

        TourDto t = viewModel.createTourWithId();

        if (t!=null)
        {

            /*
            TourDto orsTour = Mediator.getInstance().tourService.createTour(t);
            orsTour.setId(t.getId());*/
            logger.info("Saving tour with id " + t.getId());
            Mediator.getInstance().tourService.updateTour(t);
            Mediator.getInstance().list.refresh();
            Stage stage = (Stage) toField.getScene().getWindow();
            stage.close();

            System.out.println("Tour added: " + nameField.getText());
        }
        else
        {
            errorField.setText("Please make sure you have filled out all fields!");
        }
    }

    public void onCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) toField.getScene().getWindow();
        stage.close();
    }
}
