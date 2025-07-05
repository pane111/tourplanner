package Controller;

import Model.TourDto;
import ViewModel.DetailsViewModel;
import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.tools.javac.Main;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
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

    @FXML
    private Button mapButton;


    @Getter
    private DetailsViewModel viewModel;

    @Setter
    private HostServices hostServices;

    @Getter
    private TourDto tour;

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
        tour = tourDto;
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
    public void enableMapButton()
    {
        mapButton.setDisable(false);
    }
    public void disableMapButton()
    {
        mapButton.setDisable(true);
    }
    public void unselect()
    {
        viewModel.unset();
        disableMapButton();
    }

    public void onMapClick(ActionEvent actionEvent) {
        logger.info("Clicked on map");
        logger.info("Selected tour: " + tour + " with ID " + tour.getId());
        TourDto t = Mediator.getInstance().tourService.fetchTour(tour.getId());
        logger.info("Fetched tour: " + t + " start coordinates: "+t.getFromCoord().orsFormat()+" end coordinates: "+t.getToCoord().orsFormat());


        try {
            ObjectMapper mapper = new ObjectMapper();

            //We retrieve the geojson using ORS, then save it to a temporary file
            JsonNode jn = Mediator.getInstance().tourService.setMapData(t);
            String dirJson = "var directions = " +mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jn) + ";";
            //then we create a temporary html page
            String htmlPage = """
                    <!DOCTYPE html>
                    <html lang="en">
                    <head>
                        <meta charset="UTF-8">
                        <title>Leaflet Map</title>
                        <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css"
                              integrity="sha256-p4NxAoJBhIIN+hmNHrzRCf9tD/miZyoHS5obTRR9BMY="
                              crossorigin=""/>
                        <script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"
                                integrity="sha256-20nQCchB9co0qIjJZRGuk2/Z9VM+kNiyxNV1lvTlZBo="
                                crossorigin=""></script>
                    </head>
                    <body style="margin:0; padding:0;">
                    <div id="map" style="height:100vh;"></div>
                    <script>
                    %s
                    
                    var map = L.map('map');
                    var bbox = directions.bbox;
                    map.fitBounds([[bbox[1], bbox[0]], [bbox[3], bbox[2]]]);
                    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                        attribution: '2024 © FH Technikum Wien'
                    }).addTo(map);
                    L.geoJSON(directions).addTo(map);
                    </script>
                    </body>
                    </html>
                    """.formatted(dirJson);


            Path tempHtml = Files.createTempFile("map-", ".html");

            Files.writeString(tempHtml, htmlPage);

            //and then we open the page
            hostServices.showDocument(tempHtml.toUri().toString());
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }

    }
}
