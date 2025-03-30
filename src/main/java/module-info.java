module org.example.tourplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.desktop;


    opens app to javafx.fxml;
    exports app;
    opens ViewModel to javafx.fxml;
    exports ViewModel;
    exports Controller;
    opens Controller to javafx.fxml;
}