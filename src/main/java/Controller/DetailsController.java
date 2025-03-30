package Controller;

import Model.Tour;
import ViewModel.DetailsViewModel;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import lombok.Getter;

import javax.print.attribute.standard.Media;

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

    public void setTour(Tour tour)
    {
        viewModel.setCurTour(tour);
    }

}
