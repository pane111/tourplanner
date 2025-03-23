module org.example.tourplanner {
    requires javafx.controls;
    requires javafx.fxml;

    opens app to javafx.fxml;
    opens org.example.tourplanner to javafx.fxml;

    exports app;
    exports org.example.tourplanner;
}
