package ViewModel;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class EditController {

    @FXML
    private TextField nameField;

    @FXML
    private void onSave() {
        System.out.println("Gespeichert: " + nameField.getText());
    }

}
