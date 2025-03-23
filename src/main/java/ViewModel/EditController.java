package ViewModel;

import Model.Tour;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditController extends Controller {

    public TextField toField;
    public TextField fromField;
    public TextField distField;
    public TextField durationField;
    public TextArea descriptionField;
    @FXML
    private TextField nameField;
    public Label errorField;


    public void fillFields(Tour tour)
    {
        nameField.setText(tour.getName());
        fromField.setText(tour.getFrom());
        toField.setText(tour.getTo());
        distField.setText(tour.getDistance().toString());
        descriptionField.setText(tour.getDescription());
        durationField.setText(tour.getEstimatedTime());

    }

    @FXML
    private void onSave() {
        if (toField.getText().equals("") || fromField.getText().equals("") || distField.getText().equals("") || durationField.getText().equals("")) {
            errorField.setText("Please fill out all the fields!");
        }
        else
        {
            try {
                Double.parseDouble(distField.getText());
            }
            catch (NumberFormatException e) {
                errorField.setText("Please enter a valid number!");
            }
            Tour newTour = new Tour(nameField.getText(),fromField.getText(),toField.getText(),
                    Double.valueOf(distField.getText()), durationField.getText(),descriptionField.getText(),"0"
            );

            Mediator.getInstance().tpc.removeLastSelectedTour();
            Mediator.getInstance().tpc.addTourToList(newTour);
            Stage stage = (Stage) toField.getScene().getWindow();
            stage.close();

            System.out.println("Tour added: " + nameField.getText());
        }
    }

    @FXML
    void initialize() {
        Mediator.getInstance().edit=this;
    }

    public void onCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) toField.getScene().getWindow();
        stage.close();
    }
}
