package ViewModel;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddController {

    public TextField toField;
    public TextField fromField;
    public TextField distField;
    public TextField durationField;
    public TextArea descriptionField;
    @FXML
    private TextField nameField;

    @FXML
    private void onAdd() {
        System.out.println("Tour added: " + nameField.getText());
    }
}
