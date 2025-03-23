package org.example.tourplanner;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class EditController {

    @FXML
    private TextField nameField;

    @FXML
    private void onSave() {
        System.out.println("Gespeichert: " + nameField.getText());
    }

}
