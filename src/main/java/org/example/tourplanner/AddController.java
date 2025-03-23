package org.example.tourplanner;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddController {

    @FXML
    private TextField nameField;

    @FXML
    private void onAdd() {
        System.out.println("Neue Tour hinzugefügt: " + nameField.getText());
    }
}
