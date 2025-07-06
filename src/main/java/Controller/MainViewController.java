package Controller;

import Model.SendableTour;
import Model.TourDto;
import com.opencsv.bean.CsvToBeanBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class MainViewController {

    @FXML private Button logButton;
    @FXML private Button detailButton;

    Logger logger = LogManager.getLogger(MainViewController.class);
    private Integer curView=0; //0 = Details, 1 = Logs

    public void initialize() {
        ListController listController = Mediator.getInstance().listController;
        DetailsController detailsController = Mediator.getInstance().details;

        listController.tourListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            detailsController.setTour(newValue);
            if (newValue != null) {
                Mediator.getInstance().selectedTourId.setValue(newValue.getId());
            }

        });

    }

    public void onAdd(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/add-view.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icon.png")));
            stage.setTitle("Add New Tour");
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/modalStyle.css").toExternalForm());
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void onDelete(ActionEvent actionEvent) {
        Mediator.getInstance().tourService.deleteTour(Mediator.getInstance().listController.getLastSelectedItem());
        Mediator.getInstance().list.refresh();
        Mediator.getInstance().details.unselect();
    }

    public void onEdit(ActionEvent actionEvent) {
        try {
            TourDto selectedTourDto = Mediator.getInstance().listController.getLastSelectedItem();
            if (selectedTourDto != null) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edit-view.fxml"));
                Parent root = fxmlLoader.load();

                Stage stage = new Stage();
                stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icon.png")));
                stage.setTitle("Edit Tour");
                Scene scene = new Scene(root);
                scene.getStylesheets().add(getClass().getResource("/modalStyle.css").toExternalForm());
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                Mediator.getInstance().edit.fillFields(selectedTourDto);
                stage.show();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayDetails(ActionEvent actionEvent) {
        if (curView!=0)
        {
            curView=0;
            Mediator.getInstance().details.showDetails();
        }
    }

    public void displayLogs(ActionEvent actionEvent) {
        if (curView!=1 && Mediator.getInstance().listController.getLastSelectedItem()!=null)
        {
            curView=1;
            Mediator.getInstance().details.showLogs();
        }
    }

    public void onImport(ActionEvent actionEvent) {

        Window window = ((Node) actionEvent.getSource()).getScene().getWindow();
        File selectedFile = openFile(window);
        if (selectedFile!=null) {
            logger.info("Imported file: " + selectedFile.getAbsolutePath());

            try (Reader reader = new FileReader(selectedFile)) {
                List<SendableTour> t = new CsvToBeanBuilder<SendableTour>(reader)
                        .withType(SendableTour.class).withIgnoreLeadingWhiteSpace(true).build().parse();
                if (!t.isEmpty()) {
                    logger.info("Imported tour: " + t.getFirst().getName());
                    TourDto newTour = new TourDto(t.getFirst());
                    Mediator.getInstance().tourService.createTour(newTour);
                    Mediator.getInstance().list.refresh();

                }
                else
                {
                    logger.error("Imported tour is empty");
                }

            } catch (Exception e) {
                logger.error(e);
                throw new RuntimeException(e);
            }
        }
        else
        {
            logger.info("Cancelled selection or imported file is null");
        }

    }

    public static final String TARGET_DIR = "target";
    private File openFile(Window curWindow) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open CSV file");

        File defaultDir = new File(TARGET_DIR);
        if (defaultDir.exists() && defaultDir.isDirectory()) {
            fileChooser.setInitialDirectory(defaultDir);
        }

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV file", "*.csv"));
        return fileChooser.showOpenDialog(curWindow);
    }


}
