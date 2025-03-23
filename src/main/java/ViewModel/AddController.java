package ViewModel;

import Model.Tour;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.print.attribute.standard.Media;
import java.io.IOException;

public class AddController extends Controller {

    public TextField toField;
    public TextField fromField;
    public TextField distField;
    public TextField durationField;
    public TextArea descriptionField;
    public Label errorField;
    @FXML
    private TextField nameField;

    @FXML
    private void onAdd() throws IOException {

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

            Mediator.getInstance().tpc.addTourToList(newTour);
            Stage stage = (Stage) toField.getScene().getWindow();
            stage.close();

            System.out.println("Tour added: " + nameField.getText());
        }


    }

    public void onCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) toField.getScene().getWindow();
        stage.close();
    }
}
