package Controller;

import Model.TourLog;
import ViewModel.LogListViewModel;
import ViewModel.LogMainViewModel;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import javax.print.attribute.standard.Media;

public class LogMainController {

    private LogMainViewModel viewModel;

    @FXML
    private Text logDate;
    @FXML
    private Text logComment;
    @FXML
    private Text logDifficulty;
    @FXML
    private Text logTime;
    @FXML
    private Text logDistance;
    @FXML
    private Text logRating;



    public void initialize() {
        Mediator.getInstance().log=this;
        viewModel = new LogMainViewModel();
        viewModel.getDate().bindBidirectional(logDate.textProperty());
        viewModel.getTime().bindBidirectional(logTime.textProperty());
        viewModel.getDistance().bindBidirectional(logDistance.textProperty());
        viewModel.getRating().bindBidirectional(logRating.textProperty());
        viewModel.getComment().bindBidirectional(logComment.textProperty());
        viewModel.getDifficulty().bindBidirectional(logDifficulty.textProperty());


    }
    public void setLog(TourLog log)
    {
        viewModel.setCurLog(log);
    }
}
