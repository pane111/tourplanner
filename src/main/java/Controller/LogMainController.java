package Controller;

import Model.TourLog;
import ViewModel.LogListViewModel;
import ViewModel.LogMainViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.print.attribute.standard.Media;
import java.io.IOException;

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

    @FXML
    private void onAddLog() {
        try {

            System.out.println(Mediator.getInstance().selectedTourId);
            Integer sid = Mediator.getInstance().selectedTourId.get();
            if (sid!=0) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AddLogView.fxml"));
                Parent root = fxmlLoader.load();

                Stage stage = new Stage();
                stage.setTitle("Add New Log");
                stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icon.png")));
                stage.setScene(new Scene(root));
                stage.show();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onDelLog(ActionEvent actionEvent) {
        try {
            Mediator.getInstance().logList.removeLog(Mediator.getInstance().logList.getLastSelectedItem());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onEditLog(ActionEvent actionEvent) {
        try {

            TourLog l = Mediator.getInstance().logList.getLastSelectedItem();
            if (l!=null) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/EditLogView.fxml"));
                Parent root = fxmlLoader.load();

                Stage stage = new Stage();
                stage.setTitle("Edit Log");
                stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icon.png")));
                stage.setScene(new Scene(root));
                Mediator.getInstance().editLog.fillFields();
                stage.show();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
