package Controller;

import Model.TourDto;
import ViewModel.DetailsViewModel;
import com.fasterxml.jackson.annotation.JacksonInject;
import com.sun.tools.javac.Main;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;


public class DetailsController {

    @FXML
    private Text tourName;
    @FXML
    private Text tourFrom;
    @FXML
    private Text tourTo;
    @FXML
    private Text tourDistance;
    @FXML
    private Text tourTime;
    @FXML
    private Text tourDesc;
    @FXML
    private HBox mainDetailView;
    @FXML
    private StackPane logs;


    @Getter
    private DetailsViewModel viewModel;

    @Setter
    private HostServices hostServices;

    Logger logger = LogManager.getLogger(DetailsController.class);
    public void initialize() {
        viewModel = new DetailsViewModel();
        tourName.textProperty().bind(viewModel.getName());
        tourFrom.textProperty().bind(viewModel.getFrom());
        tourTo.textProperty().bind(viewModel.getTo());
        tourDistance.textProperty().bind(viewModel.getDistance());
        tourTime.textProperty().bind(viewModel.getEstimatedTime());
        tourDesc.textProperty().bind(viewModel.getDescription());
        logger.info(Mediator.getInstance().hostServices);
        Mediator.getInstance().details = this;
    }

    public void setTour(TourDto tourDto)
    {
        viewModel.setCurTour(tourDto);
    }
    public void showLogs()
    {
        logs.setVisible(true);
        mainDetailView.setVisible(false);
    }
    public void showDetails()
    {
        mainDetailView.setVisible(true);
        logs.setVisible(false);
    }

    public void onMapClick(ActionEvent actionEvent) {
        logger.info("Clicked on map");
        hostServices.showDocument(
                Objects.requireNonNull(getClass().getResource("/leafletpage.html")).toExternalForm()
        );
    }
}
