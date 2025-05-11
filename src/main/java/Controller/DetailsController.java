package Controller;

import Model.TourDto;
import ViewModel.DetailsViewModel;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import lombok.Getter;

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

    public void initialize() {
        viewModel = new DetailsViewModel();
        tourName.textProperty().bind(viewModel.getName());
        tourFrom.textProperty().bind(viewModel.getFrom());
        tourTo.textProperty().bind(viewModel.getTo());
        tourDistance.textProperty().bind(viewModel.getDistance().asString());
        tourTime.textProperty().bind(viewModel.getEstimatedTime());
        tourDesc.textProperty().bind(viewModel.getDescription());

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

}
